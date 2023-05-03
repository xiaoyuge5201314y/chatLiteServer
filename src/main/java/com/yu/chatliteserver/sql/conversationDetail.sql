    create table tb_chat_conversation_detail
    (
        id varchar(255) primary key,
        create_time timestamp    not null,
        update_time timestamp    not null,
        messages varchar(255) not null,
        conversation_id   text(255) not null,

        version int(255) not null
    );

