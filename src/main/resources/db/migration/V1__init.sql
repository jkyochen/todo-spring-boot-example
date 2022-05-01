CREATE TABLE todo
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    create_time datetime              NULL,
    update_time datetime              NULL,
    content     VARCHAR(255)          NULL,
    done        BIT(1)                NULL,
    user_id     BIGINT                NULL,
    CONSTRAINT pk_todo PRIMARY KEY (id)
);

CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    create_time datetime              NULL,
    update_time datetime              NULL,
    username    VARCHAR(255)          NULL,
    password    VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE todo
    ADD CONSTRAINT FK_TODO_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);