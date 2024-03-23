create table if not exists `person`
(
    `id` bigint(20) not null auto_increment,
    `first_name` varchar(80)  not null,
    `last_name`  varchar(80)  not null,
    `address`    varchar(100) not null,
    `gender`     varchar(7)   not null,
    primary key (`id`)
);
