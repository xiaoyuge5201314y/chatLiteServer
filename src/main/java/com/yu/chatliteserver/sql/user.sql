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

-- CREATE TABLE user_detail (
--     name VARCHAR(50) NOT NULL,
--     age INT,
--     sex VARCHAR(10),
--     email VARCHAR(100),
--     address VARCHAR(200),
--     phone VARCHAR(20),
--     create_time timestamp    not null,
--     update_time timestamp    not null,  
-- );


CREATE TABLE tb_user_chat (
    all_tokens int not null,
    available_tokens int not null,
    user_id varchar(255) not null,
    create_time timestamp    not null,
    update_time timestamp    not null
);