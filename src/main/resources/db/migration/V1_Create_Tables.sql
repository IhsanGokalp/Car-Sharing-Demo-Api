CREATE table users (
   id bigserial primary key,
   firstname varchar,
   lastname varchar,
   dob date
);
create table contacts(
     id bigserial primary key,
     phone_number varchar,
     email varchar,
     user_id bigint references users(id) unique
);

create table cars(
     id bigserial primary key,
     licence varchar unique,
     model varchar,
     make varchar,
     user_id bigint references users(id)
);

create table customer_rides(
   id bigserial primary key,
   from_ geography(POINT, 4326),
   to_ geography(POINT, 4326),
   date_ timestamp,
   amount int,
   user_id bigint references users(id)
);

create table rider_rides(
    id bigserial primary key,
    from_ geography(POINT, 4326),
    to_ geography(POINT, 4326),
    date_ timestamp,
    available int,
    occupied int,
    rider_id bigint references users(id),
    car_id bigint references cars(id)
);
