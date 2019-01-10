CREATE SCHEMA "security" AUTHORIZATION root;

CREATE SEQUENCE "security"."users_id_seq";

CREATE TABLE "security"."users"
(
    "id" bigint NOT NULL DEFAULT nextval('"security"."users_id_seq"'::regclass),
    "name" character varying(90) NOT NULL,
    "password" character varying(300) NOT NULL,
    "enabled" boolean NOT NULL,
    "account_non_expired" boolean,
    "credentials_non_expired" boolean,
    "account_non_locked" boolean,
    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

CREATE INDEX "user_name_idx" ON "security"."users" USING hash ("name") TABLESPACE pg_default;

CREATE SEQUENCE "security"."users_authorization_id_seq";    

CREATE TABLE "security"."users_authorization"
(
    "id" bigint NOT NULL DEFAULT nextval('"security"."users_authorization_id_seq"'::regclass),
    "user_id" bigint NOT NULL,
    "name" character varying(300) COLLATE pg_catalog."default" NOT NULL,    
    CONSTRAINT "users_auth_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "user_id_fk" FOREIGN KEY ("user_id")
        REFERENCES "security"."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

INSERT INTO "security"."users"("id", "name", "password", "enabled") VALUES(default, 'admin', '$2a$10$DSVuacwxycVuwPdYGWeE8.QqmoOOGQ5tsLluEEgILsPFFodA8RNm2', true);

INSERT INTO "security"."users_authorization"("id", "user_id", "name") VALUES(default, 1, 'ROLE_ADMIN');

