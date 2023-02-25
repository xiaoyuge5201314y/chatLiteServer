#ai模型表
create table tb_ai_model
(
    id    varchar(255) not null primary key,
    name varchar(255) not null, #模型名称
    type varchar(255) not null, #模型类型
    price double not null, #模型价格


    create_time timestamp not null
);

