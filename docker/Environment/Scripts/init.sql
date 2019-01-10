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

CREATE SCHEMA "main" AUTHORIZATION root;

CREATE SEQUENCE "main"."persons_id_seq";

CREATE TABLE "main"."persons"
(
    "id" bigint NOT NULL DEFAULT nextval('"main"."persons_id_seq"'::regclass),
    "name" character varying(300) NOT NULL,
    "company" character varying(100) NOT NULL,
    "title" character varying(10),
    "department" character varying(60),
    CONSTRAINT "persons_pkey" PRIMARY KEY ("id")
);

CREATE SEQUENCE "main"."persons_phones_id_seq";

CREATE TABLE "main"."persons_phones"
(
    "id" bigint NOT NULL DEFAULT nextval('"main"."persons_phones_id_seq"'::regclass),
    "person_id" bigint NOT NULL,
    "type" character varying(12) NOT NULL,
    "number" character varying(30),
    CONSTRAINT "persons_phones_pkey" PRIMARY KEY ("id"),	
	CONSTRAINT "phones_persons_id_fk" FOREIGN KEY ("person_id")
        REFERENCES "main"."persons" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE	
);

CREATE SEQUENCE "main"."persons_emails_id_seq";

CREATE TABLE "main"."persons_emails"
(
    "id" bigint NOT NULL DEFAULT nextval('"main"."persons_emails_id_seq"'::regclass),
    "person_id" bigint NOT NULL,
    "address" character varying(300) NOT NULL,	
    CONSTRAINT "persons_emails_pkey" PRIMARY KEY ("id"),	
	CONSTRAINT "emails_persons_id_fk" FOREIGN KEY ("person_id")
        REFERENCES "main"."persons" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE	
);

CREATE SEQUENCE "main"."persons_addresses_id_seq";

CREATE TABLE "main"."persons_addresses"
(
    "id" bigint NOT NULL DEFAULT nextval('"main"."persons_addresses_id_seq"'::regclass),
    "person_id" bigint NOT NULL,
    "street" character varying(200) NOT NULL,	
    "city" character varying(60) NOT NULL,	
    "state" character varying(60) NOT NULL,	
    "zip" character varying(15) NOT NULL,	
    "country" character varying(60) NOT NULL,		
    CONSTRAINT "persons_addresses_pkey" PRIMARY KEY ("id"),	
	CONSTRAINT "addresses_persons_id_fk" FOREIGN KEY ("person_id")
        REFERENCES "main"."persons" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE	
);