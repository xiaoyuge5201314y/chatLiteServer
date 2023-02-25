create table tb_chat_message
(
    id varchar(255) primary key,
    create_time timestamp    not null,
    update_time timestamp    not null,

    prompt     longtext     not null, #上下文
    user_id   varchar(255) not null, #用户id
    ai_model_id   varchar(255) not null, #ai模型id

    version int(255) not null
);

