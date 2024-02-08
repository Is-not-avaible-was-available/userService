CREATE TABLE `role`
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    `role` VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    token          VARCHAR(255) NULL,
    user_id        BIGINT NULL,
    session_status TINYINT NULL,
    expiry_date    datetime NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    roles_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (roles_id, users_id)
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (users_id) REFERENCES users (id);