create table tb_chat_prompt
(
    id varchar(255) primary key,
    create_time timestamp    not null,
    prompt_role  varchar(255) not null,
    content     longtext     not null,
    ord int(255) not null,
    message_id varchar(255) not null
);

