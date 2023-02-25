#ai模型配置表
create table tb_ai_model_config
(
    id    varchar(255) not null primary key,
    name varchar(255) not null, #模型名称
    type varchar(255) not null, #模型类型
    price double not null, #模型价格,
    user_id varchar(255) not null ,#用户id
    description  varchar(255) not null, #模型描述
    model_id varchar(255) not null, #模型表id
    useCount  varchar(255) not null, #模型使用次数
    pLength  double not null, #参数长度
    temperature double not null, #参数温度
    max_tokens  double not null, #最大token数

    create_time timestamp not null
);

