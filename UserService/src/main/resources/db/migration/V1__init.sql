CREATE TABLE authorization
(
    id                            VARCHAR(255)  NOT NULL,
    registered_client_id          VARCHAR(255)  NULL,
    principal_name                VARCHAR(255)  NULL,
    authorization_grant_type      VARCHAR(255)  NULL,
    authorized_scopes             VARCHAR(1000) NULL,
    attributes                    Text NULL,
    state                         VARCHAR(500)  NULL,
    authorization_code_value      Text NULL,
    authorization_code_issued_at  datetime      NULL,
    authorization_code_expires_at datetime      NULL,
    authorization_code_metadata   VARCHAR(255)  NULL,
    access_token_value            Text NULL,
    access_token_issued_at        datetime      NULL,
    access_token_expires_at       datetime      NULL,
    access_token_metadata         VARCHAR(2000) NULL,
    access_token_type             VARCHAR(255)  NULL,
    access_token_scopes           VARCHAR(1000) NULL,
    refresh_token_value           Text NULL,
    refresh_token_issued_at       datetime      NULL,
    refresh_token_expires_at      datetime      NULL,
    refresh_token_metadata        VARCHAR(2000) NULL,
    oidc_id_token_value           Text NULL,
    oidc_id_token_issued_at       datetime      NULL,
    oidc_id_token_expires_at      datetime      NULL,
    oidc_id_token_metadata        VARCHAR(2000) NULL,
    oidc_id_token_claims          VARCHAR(2000) NULL,
    user_code_value               Text NULL,
    user_code_issued_at           datetime      NULL,
    user_code_expires_at          datetime      NULL,
    user_code_metadata            VARCHAR(2000) NULL,
    device_code_value             Text NULL,
    device_code_issued_at         datetime      NULL,
    device_code_expires_at        datetime      NULL,
    device_code_metadata          VARCHAR(2000) NULL,
    CONSTRAINT pk_authorization PRIMARY KEY (id)
);

CREATE TABLE authorization_consent
(
    registered_client_id VARCHAR(255)  NOT NULL,
    principal_name       VARCHAR(255)  NOT NULL,
    authorities          VARCHAR(1000) NULL,
    CONSTRAINT pk_authorizationconsent PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE client
(
    id                            VARCHAR(255)  NOT NULL,
    client_id                     VARCHAR(255)  NULL,
    client_id_issued_at           datetime      NULL,
    client_secret                 VARCHAR(255)  NULL,
    client_secret_expires_at      datetime      NULL,
    client_name                   VARCHAR(255)  NULL,
    client_authentication_methods VARCHAR(1000) NULL,
    authorization_grant_types     VARCHAR(1000) NULL,
    redirect_uris                 VARCHAR(1000) NULL,
    post_logout_redirect_uris     VARCHAR(1000) NULL,
    scopes                        VARCHAR(1000) NULL,
    client_settings               VARCHAR(2000) NULL,
    token_settings                VARCHAR(2000) NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    `role` VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    token          VARCHAR(255)          NULL,
    user_id        BIGINT                NULL,
    session_status Tinyint              NULL,
    expiry_date    datetime              NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255)          NULL,
    password VARCHAR(255)          NULL,
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