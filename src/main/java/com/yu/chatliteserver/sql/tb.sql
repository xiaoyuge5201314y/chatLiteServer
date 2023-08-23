-- ai模型配置表
create table tb_ai_model_config (
    id varchar(255) not null primary key,
    name varchar(255) not null,
    -- 模型名称
    type varchar(255) not null,
    -- 模型类型
    price double not null,
    -- 模型价格,
    user_id varchar(255) not null,
    -- 用户id
    description varchar(255) not null,
    -- 模型描述
    model_id varchar(255) not null,
    -- 模型表id
    useCount varchar(255) not null,
    -- 模型使用次数
    pLength double not null,
    -- 参数长度
    temperature double not null,
    -- 参数温度
    max_tokens double not null,
    -- 最大token数
    ‰ create_time timestamp not null
);

-- ai模型表
create table tb_ai_model (
    id varchar(255) not null primary key,
    name varchar(255) not null,
    -- 模型名称
    type varchar(255) not null,
    -- 模型类型
    price double not null,
    -- 模型价格
    create_time timestamp not null
);

create table tb_chat_conversation_detail (
    id varchar(255) primary key,
    create_time timestamp not null,
    update_time timestamp not null,
    messages varchar(255) not null,
    conversation_id text(255) not null,
    version int(255) not null
);

create table tb_user (
    id bigint auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    create_time timestamp not null,
    update_time timestamp not null,
    vip int not null,
    version int(255) not null
);

CREATE TABLE tb_user_chat (
    all_tokens int not null,
    available_tokens int not null,
    user_id varchar(255) not null,
    create_time timestamp not null,
    update_time timestamp not null
);

-- chatgpt会话表
create table tb_chat_conversation (
    id varchar(255) primary key,
    create_time timestamp not null,
    update_time timestamp not null,
    -- 用户id
    user_id varchar(255) not null,
    -- ai模型id
    ai_model_id varchar(255) not null,
    -- 场景id
    sence_id varchar(255),
    version int(255) not null
);

-- 场景表
create table tb_scene (
    id varchar(255) primary key,
    scene_name varchar(255) not null,
    -- 场景名称
    scene_prompt varchar(255) not null,
    -- 场景提示
    scene_description varchar(255) not null -- 场景描述
);