drop table if exists customers;
create table customers (
  id                    bigserial,
  phone                 VARCHAR(30) not null UNIQUE,
  password              VARCHAR(80) not null,
  enable                boolean,
  email                 VARCHAR(50) UNIQUE,
  first_name            VARCHAR(50),
  last_name             VARCHAR(50),
  PRIMARY KEY (id)
);

drop table if exists roles;
create table roles (
  id                    serial,
  name                  VARCHAR(50) not null,
  primary key (id)
);

drop table if exists customers_roles;
create table customers_roles (
  customer_id             BIGINT NOT NULL,
  role_id                 INT NOT NULL,
  primary key (customer_id, role_id),
  FOREIGN KEY (customer_id)
  REFERENCES customers (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

insert into roles (name)
values
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

insert into customers (phone, password, enable, first_name, last_name, email)
values
('2','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'true', 'admin','admin','admin@gmail.com'),
('100','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'true', 'customer','customer','customer@gmail.com');


insert into customers_roles (customer_id, role_id)
values
(1, 1),
(1, 2),
(1, 3),
(2, 1);
