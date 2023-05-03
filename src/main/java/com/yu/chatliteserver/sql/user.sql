create table tb_user
(
    id          bigint auto_increment
        primary key,
    username    varchar(255) not null,
    password    varchar(255) not null,
    email    varchar(255) not null,
    create_time timestamp    not null,
    update_time timestamp    not null,
    vip int not null,
    version int(255) not null
);

