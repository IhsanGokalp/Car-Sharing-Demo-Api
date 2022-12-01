package com.example.demo.Endpoint.Impl;

import com.example.demo.Config.Checks;
import com.example.demo.Dto.ConfirmationCreateDto;
import com.example.demo.Dto.ConfirmationDto;
import com.example.demo.Dto.CustomerRideCreateDto;
import com.example.demo.Dto.CustomerRideUpdateDto;
import com.example.demo.Dto.Error.CoordinateParseException;
import com.example.demo.Dto.Error.PointParseException;
import com.example.demo.Endpoint.CustomerRideEndpoint;
import com.example.demo.Endpoint.UserEndpoint;
import com.example.demo.Entity.Confirmation;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.RiderRide;
import com.example.demo.Service.ConfirmationService;
import com.example.demo.Service.CustomerRideService;
//import org.modelmapper.*;
import com.example.demo.Service.RiderRideService;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRideEndpointImpl implements CustomerRideEndpoint {

    private final CustomerRideService customerRideService;
    private final UserEndpoint userEndpoint;
    private final ModelMapper modelMapper;
    private final RiderRideService riderRideService;
    private final ConfirmationService confirmationService;

    public CustomerRideEndpointImpl(CustomerRideService customerRideService,
                                    UserEndpointImpl userEndpoint, ModelMapper modelMapper, RiderRideService riderRideService, ConfirmationService confirmationService) {
        this.customerRideService = customerRideService;
        this.userEndpoint = userEndpoint;
        this.modelMapper = modelMapper;
        this.riderRideService = riderRideService;
        this.confirmationService = confirmationService;
    }

    @Override
    public CustomerRide findById(Long id) {
        return Checks.checkEntityExists(customerRideService.findById(id),
                "No customer ride found by id " + id);
    }

    @Override
    public CustomerRide save(CustomerRideCreateDto customerRideCreateDto) {
        CustomerRide customerRide = modelMapper.map(customerRideCreateDto, CustomerRide.class);
        customerRide.setCustomer(userEndpoint.findById(customerRideCreateDto.getCustomer_id()));
        try {
            customerRide.setFrom((Point) Checks.wktToGeometry("Point (" + customerRideCreateDto.getFrom().
                    substring(0,customerRideCreateDto.getFrom().indexOf(","))+ " " +
                    customerRideCreateDto.getFrom().
                            substring(customerRideCreateDto.getFrom().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new CoordinateParseException("Cordinates given on wrong order on field from");
        }
        try {
            customerRide.setTo((Point) Checks.wktToGeometry("Point (" + customerRideCreateDto.getTo().
                    substring(0,customerRideCreateDto.getTo().indexOf(",")) + " " +
                    customerRideCreateDto.getTo().
                            substring(customerRideCreateDto.getTo().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new CoordinateParseException("Cordinates given on wrong order on field to");
        }
        return customerRideService.save(customerRide);
    }

    @Override
    public CustomerRide update(Long id, CustomerRideUpdateDto customerRideupdateDto) {
        CustomerRide customerRide =
                Checks.checkEntityExists(customerRideService.
                                findById(id), "No customer ride with id "+ id);
        try {
            customerRide.setFrom((Point) Checks.wktToGeometry("Point (" + customerRideupdateDto.getFrom().
                    substring(0,customerRideupdateDto.getFrom().indexOf(","))+ " " +
                    customerRideupdateDto.getFrom().
                            substring(customerRideupdateDto.getFrom().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new CoordinateParseException("Cordinates given on wrong order on field from");
        }
        try {
            customerRide.setTo((Point) Checks.wktToGeometry("Point (" + customerRideupdateDto.getTo().
                    substring(0,customerRideupdateDto.getTo().indexOf(",")) + " " +
                    customerRideupdateDto.getTo().
                            substring(customerRideupdateDto.getTo().indexOf(" ")) + ")"));
        } catch (ParseException e) {
            throw new PointParseException(e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new CoordinateParseException("Cordinates given on wrong order on field to");
        }
        modelMapper.map(customerRideupdateDto, customerRide);
        return customerRideService.save(customerRide);
    }

    @Override
    public void delete(Long id) {
        confirmationService.findAllByCustomerRide(findById(id)).
                stream().forEach(x-> confirmationService.delete(x));
        customerRideService.delete(findById(id));
    }

    @Override
    public ConfirmationDto request(Long id, ConfirmationCreateDto confirmationCreateDto) {
        Confirmation confirmation =new Confirmation();
        confirmation.setRiderRide(Checks.checkEntityExists(
                riderRideService.findById(
                        confirmationCreateDto.getRiderRideId()),
                "No Rider Ride found by id " + confirmationCreateDto.getRiderRideId()));
        confirmation.setCustomerRide(findById(id));
        return modelMapper.map(confirmationService.requestSave(confirmation), ConfirmationDto.class);
    }

    @Override
    public List<RiderRide> search(Long id, Double distance, Integer r) {
        CustomerRide customerRide = findById(id);
        return riderRideService.findNearWithinDistance
                (customerRide.getFrom(), customerRide.getTo(),
                        distance, distance, r.intValue()*10);
    }

    @Override
    public void deleteRequest(Long id) {
        confirmationService.delete(confirmationService.findById(id));
    }
}
