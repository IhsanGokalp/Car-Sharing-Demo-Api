create table confirmations(
    id bigserial primary key,
    rider_ride_id bigint references rider_rides(id),
    customer_ride_id bigint references customer_rides(id),
    confirmed boolean default false
);

alter table customer_rides
    add column rider_ride_id references rider_rides(id);

alter table cars add column available_seats int;