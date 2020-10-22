drop table if exists users.customers;
create table users.customers (
id                    bigserial,
email                 varchar(50) UNIQUE,
first_name            varchar(50),
last_name             varchar(50),

PRIMARY KEY (id)
);

drop table if exists users.roles;
create table users.roles (
id                    serial,
name                  varchar(50) not null,

PRIMARY KEY (id)
);

drop table if exists users.users;
create table users.users (
id                    bigserial,
user_type             varchar(10) not null,
user_id               bigint not null,
username              varchar(30) not null UNIQUE,
password              varchar(80) not null,
role_id               int not null,
enable                boolean,

PRIMARY KEY (id),
constraint fk_role_id foreign key (role_id) references users.roles (id)
);




insert into users.roles (name)
values
('CUSTOMER'), ('RESTAURANT_MANAGER'), ('RESTAURANT_ADMIN');

insert into users.customers (email, first_name, last_name)
values
('customer@gmail.com', 'customer','customer');


insert into users.users (id, user_type, user_id, username, password, role_id, enable)
values
(1, 'C', 1, '100', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '1', 'true'),
(2, 'R', 304, '1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '2', 'true'),
(3, 'R', 405, '2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '3', 'true');
