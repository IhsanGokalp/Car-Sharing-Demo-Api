package com.example.demo.Endpoint.Impl;

import com.example.demo.Config.Checks;
import com.example.demo.Dto.ConfirmationDto;
import com.example.demo.Dto.ConfirmationResponseDto;
import com.example.demo.Dto.Error.*;
import com.example.demo.Dto.RiderRideCreateDto;
import com.example.demo.Dto.RiderRideUpdateDto;
import com.example.demo.Endpoint.RiderRideEndpoint;
import com.example.demo.Endpoint.UserEndpoint;
import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Service.CarService;
import com.example.demo.Service.ConfirmationService;
import com.example.demo.Service.CustomerRideService;
import com.example.demo.Service.RiderRideService;
import com.vividsolutions.jts.io.ParseException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderRideEndpointImpl implements RiderRideEndpoint {

    private final RiderRideService riderRideService;
    private final ModelMapper modelMapper;
    private final UserEndpoint userService;
    private final ConfirmationService confirmationService;
    private final CustomerRideService customerRideService;
    private final CarService carService;
    private static final Logger logger = LoggerFactory.getLogger(RiderRideEndpointImpl.class);
    public RiderRideEndpointImpl(RiderRideService riderRideService,
                                 ModelMapper modelMapper, UserEndpoint userService,
                                 ConfirmationService confirmationService,
                                 CustomerRideService customerRideService, CarService carService) {
        this.riderRideService = riderRideService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.confirmationService = confirmationService;
        this.customerRideService = customerRideService;
        this.carService = carService;
    }

    @Override
    public RiderRide findById(Long id) {
        return Checks.checkEntityExists(riderRideService.findById(id),
                "No rider ride found by id " + id);
    }

    @Override
    public RiderRide save(RiderRideCreateDto riderRideEndpoint) {

        RiderRide ride = modelMapper.map(riderRideEndpoint, RiderRide.class);
        /*if(carService.findById(riderRideEndpoint.getCar_id()) == null) {
            throw new InvalidCarException("No car exists by id " + riderRideEndpoint.getCar_id());
        }*/
        ride.setCar(carService.findById(riderRideEndpoint.getCar_id()));
        try {
            ride.setFrom((Point) Checks.wktToGeometry("Point (" + riderRideEndpoint.getFrom().
                    substring(0,riderRideEndpoint.getFrom().indexOf(","))+ " " +
                    riderRideEndpoint.getFrom().
                            substring(riderRideEndpoint.getFrom().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        }
        try {
            ride.setTo((Point) Checks.wktToGeometry("Point (" + riderRideEndpoint.getTo().
                    substring(0,riderRideEndpoint.getTo().indexOf(",")) + " " +
                    riderRideEndpoint.getTo().
                            substring(riderRideEndpoint.getTo().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        }

        ride.setRider(userService.findById(riderRideEndpoint.getUser_id()));
        logger.info(ride.toString());
        return riderRideService.save(ride);
    }

    @Override
    public RiderRide update(Long id, RiderRideUpdateDto riderRideUpdateDto) {
        RiderRide ride = Checks.checkEntityExists(riderRideService.findById(id),
                "No Rider Ride with id " + id);
        modelMapper.map(riderRideUpdateDto, ride);
        try {
            ride.setFrom((Point) Checks.wktToGeometry("Point (" + riderRideUpdateDto.getFrom().
                    substring(0,riderRideUpdateDto.getFrom().indexOf(","))+ " " +
                    riderRideUpdateDto.getFrom().
                            substring(riderRideUpdateDto.getFrom().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new CoordinateParseException("Cordinates given on wrong order on field from");
        }

        try {
            ride.setTo((Point) Checks.wktToGeometry("Point (" + riderRideUpdateDto.getTo().
                    substring(0,riderRideUpdateDto.getTo().indexOf(",")) + " " +
                    riderRideUpdateDto.getTo().
                            substring(riderRideUpdateDto.getTo().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new CoordinateParseException("Cordinates given on wrong order on field to");
        }

        return riderRideService.save(ride);
    }

    @Override
    public void delete(Long id) {
        riderRideService.delete(findById(id));
    }

    @Override
    public ConfirmationDto response(Long id,
                                    ConfirmationResponseDto confirmationResponseDto) {
        CustomerRide customerRide = Checks.checkEntityExists(customerRideService.
                        findById(confirmationResponseDto.getConfirmationRequestId()),
                "No Customer Ride found by id " + confirmationResponseDto.
                        getConfirmationRequestId());
        RiderRide ride = findById(id);
        if (ride.getAvailable() < customerRide.getAmount()) {
            throw new OverAmountCustomerException(
                    "More Amount requested than Exists in id " + customerRide.getId());
        }

        if (confirmationResponseDto.getConfirmed()) {
            ride.setOccupied(ride.getOccupied() + customerRide.getAmount());
            ride.setAvailable(ride.getAvailable() - customerRide.getAmount());
        } else {
            ride.setOccupied(ride.getOccupied() - customerRide.getAmount());
            ride.setAvailable(ride.getAvailable() + customerRide.getAmount());
        }

        Confirmation confirmation = confirmationService.
                findByRiderRideAndCustomerRide(ride, customerRide);
        confirmation.setApproved(confirmationResponseDto.getConfirmed());
        return modelMapper.map(confirmationService.requestSave(confirmation),
                ConfirmationDto.class);
    }

    @Override
    public List<Confirmation> findRequestsById(Long id) {
        return confirmationService.findByRiderId(findById(id));
    }
}
