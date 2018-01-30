create database pranadesha;

create user 'pranadesha'@'localhost' identified by 'pranadesha';

grant select, insert, update, delete, create, drop, index on pranadesha.* to 'pranadesha'@'localhost';

use pranadesha;

drop table cm_person;
drop table cm_natural_person;
drop table cm_user;
drop table cm_user_role;
drop table cm_user_locale;
drop table cm_user_timezone;
drop table cm_person_phone;
drop table um_course;
drop table um_workshop;
drop table um_class_schedule;
drop table um_class_attendee;
drop table um_class_attendance;
drop table um_class_delivery;
drop table am_product;
drop table am_inventory;
drop table am_product_image;
drop table am_product_price;

/*
cm: communication module
am: accounting module
um: university module
*/

create table cm_person (
  person_id integer unsigned not null auto_increment
  , person_type char(1)
  , street_address char(50)
  , city char(50)
  , province char(10)
  , zip_code char(15)
  , constraint pk_person primary key (person_id)
) engine MyISAM;

create table cm_natural_person (
  person_id integer unsigned not null
  , first_name char(20)
  , middle_name char(20)
  , last_name char(20)
  , birthday_date date
  , mail char(50)
  , mobile char(20)
  , constraint pk_natural_person primary key (person_id)
) engine MyISAM;

create index ix_cm_natural_person_1 on cm_natural_person (mail);

create table cm_user_locale (
  locale_id char(5)
  , constraint pk_cm_user_locale primary key (locale_id)
) engine MyISAM;

create table cm_user_timezone (
  timezone_id char(50)
  , constraint pk_cm_user_timezone primary key (timezone_id)
);

create table cm_user (
  user_name char(50)
  , user_password char(20)
  , locale_id char(5)
  , timezone_id char(50)
  , constraint pk_user primary key (user_name)
) engine MyISAM;

create table cm_user_role (
  user_name char(50)
  , user_role char(20)
  , constraint pk_user_role primary key (user_name, user_role)
) engine MyISAM;

create table cm_person_phone (
  person_phone_id integer unsigned not null auto_increment
  , person_id integer unsigned not null
  , phone_number char(20)
  , phone_region_code char(3)
  , constraint pk_person_phone primary key (person_phone_id)
) engine MyISAM;

create table um_course (
  course_id integer unsigned not null auto_increment
  , course_name char(33)
  , prerequisite_course_id integer unsigned
  , constraint pk_course primary key (course_id)
) engine MyISAM;

create table um_workshop (
  workshop_id integer unsigned not null auto_increment
  , course_id integer unsigned
  , workshop_description char(50)
  , start_date date
  , end_date date
  , street char(50)
  , neighbourhood char(50)
  , zip char(14)
  , phone char(20)
  , constraint pk_workshop primary key (workshop_id)
) engine MyISAM;

create table um_class_schedule (
  class_schedule_id integer unsigned not null auto_increment
  , class_date date
  , constraint pk_class_schedule primary key (class_schedule_id)
) engine MyISAM;

create table um_class_attendee (
  class_attendee_id integer unsigned not null auto_increment
  , person_id integer unsigned
  , attendance_count integer unsigned
  , attendee_grade numeric(5,2)
  , comments text
  , constraint pk_class_attendee primary key (class_attendee_id)
) engine MyISAM;

create table um_class_attendance (
  class_attendance_id integer unsigned not null auto_increment
  , class_date date
  , class_attendee_id integer
  , attendance_ind char(1)
  , constraint pk_class_attendance primary key (class_attendance_id)
) engine MyISAM;

create table um_class_delivery (
  class_delivery_id integer unsigned not null auto_increment
  , class_delivery_date date
  , class_attendee_id integer unsigned
  , delivery_grade numeric(5,2)
  , comments text
  , constraint pk_class_delivery primary key (class_delivery_id)
) engine MyISAM;

create table am_product (
  product_id integer unsigned not null auto_increment
  , description char(50)
  , cover_image_id integer unsigned
  , price_id integer unsigned
  , constraint pk_product primary key (product_id)
) engine MyISAM;

create table am_product_price (
  price_id integer unsigned not null auto_increment
  , product_id integer unsigned not null
  , price numeric(20,2)
  , start_date date
  , end_date date
  , last_user_id char(20)
  , last_update_date datetime
  , constraint pk_product_price primary key (price_id)
) engine MyISAM;

create index ix_am_product_price_1 on am_product_price (product_id);

create table am_inventory (
  inventory_id integer unsigned not null auto_increment
  , product_id integer unsigned
  , input_date date
  , original_quantity integer unsigned
  , actual_quantity integer unsigned
  , original_amount numeric(14,2)
  , actual_amount numeric(14,2)
  , constraint pk_inventory primary key (inventory_id)
) engine MyISAM;

create index ix_am_inventory_1 on am_inventory (product_id);

create table am_product_image (
  image_id integer unsigned not null auto_increment
  , product_id integer unsigned not null
  , image_type char(25) not null
  , image_data blob not null -- max 65k
  , image_name char(50) not null
  , product_cover char(1) not null
  , constraint pk_product_image primary key (image_id)
) engine MyISAM;

create index ix_am_product_image_1 on am_product_image (product_id);

INSERT INTO `pranadesha`.`cm_person`
(`person_id`,
`person_type`,
`street_address`,
`city`,
`province`,
`zip_code`)
VALUES
(null, null, 'Av. Prof. Vespasiano Veiga, 65', 'Araraquara', 'SP', '14802-550');

INSERT INTO `pranadesha`.`cm_natural_person`
(`person_id`,
`first_name`,
`middle_name`,
`last_name`,
`birthday_date`,
`mail`,
`mobile`)
VALUES
(last_insert_id(), 'Vinícius', 'Almeida', 'Paiva', null, 'vinicius.vs@gmail.com', '16 9 8127-9540');

INSERT INTO `pranadesha`.`cm_user`
(`user_name`,
`user_password`
,`locale_id`
,`timezone_id`)
VALUES
('vinicius.vs@gmail.com', 'menininha', 'pt_BR', '');

INSERT INTO `pranadesha`.`cm_user_role`
(`user_name`,
`user_role`)
VALUES
('vinicius.vs@gmail.com', 'USERS');


INSERT INTO `pranadesha`.`cm_user_locale`
(`locale_id`)
VALUES
('pt_BR'),('en_US');


INSERT INTO `pranadesha`.`cm_user_timezone`
(`timezone_id`)
VALUES
('America/Sao_Paulo');

commit;
