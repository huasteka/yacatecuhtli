SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;

CREATE SEQUENCE hibernate_sequence
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE account (
  id          INTEGER               NOT NULL,
  row_version INTEGER               NOT NULL,
  created_at  TIMESTAMP WITHOUT TIME ZONE,
  updated_at  TIMESTAMP WITHOUT TIME ZONE,
  name        CHARACTER VARYING(50) NOT NULL,
  code        CHARACTER VARYING(10) NOT NULL
);

CREATE TABLE account_balance (
  id          INTEGER NOT NULL,
  row_version INTEGER NOT NULL,
  balance     NUMERIC(19, 2),
  month       INTEGER,
  year        INTEGER,
  account_id  INTEGER
);

CREATE TABLE budget_category (
  id          INTEGER                NOT NULL,
  row_version INTEGER                NOT NULL,
  name        CHARACTER VARYING(100) NOT NULL,
  group_id    INTEGER
);


CREATE TABLE budget_group (
  id          INTEGER                NOT NULL,
  row_version INTEGER                NOT NULL,
  name        CHARACTER VARYING(100) NOT NULL
);

CREATE TABLE entry (
  id              INTEGER                     NOT NULL,
  row_version     INTEGER                     NOT NULL,
  code            CHARACTER VARYING(100),
  addition        NUMERIC(10, 2),
  description     CHARACTER VARYING(500),
  discount        NUMERIC(10, 2),
  executed_at     TIMESTAMP WITHOUT TIME ZONE,
  gross_value     NUMERIC(10, 2),
  issued_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  net_value       NUMERIC(10, 2),
  reversed_at     TIMESTAMP WITHOUT TIME ZONE,
  tax             NUMERIC(10, 2),
  type            CHARACTER VARYING(255),
  account_id      INTEGER,
  category_id     INTEGER,
  payment_type_id INTEGER                     NOT NULL
);

CREATE TABLE payment_type (
  id                     INTEGER               NOT NULL,
  row_version            INTEGER               NOT NULL,
  created_at             TIMESTAMP WITHOUT TIME ZONE,
  updated_at             TIMESTAMP WITHOUT TIME ZONE,
  name                   CHARACTER VARYING(50) NOT NULL,
  first_installment_term INTEGER,
  installment_quantity   INTEGER,
  installment_term       INTEGER,
  staged_payment         BOOLEAN,
  tax                    NUMERIC(10, 2),
  payment_account_id     INTEGER               NOT NULL
);

CREATE TABLE reversed_entry (
  id          INTEGER NOT NULL,
  row_version INTEGER NOT NULL,
  reverse_id  INTEGER NOT NULL,
  reversed_id INTEGER NOT NULL
);

CREATE TABLE scheduled_entry (
  id          INTEGER                     NOT NULL,
  row_version INTEGER                     NOT NULL,
  execute_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  category_id INTEGER                     NOT NULL,
  entry_id    INTEGER                     NOT NULL
);

CREATE TABLE transferred_entry (
  id             INTEGER                     NOT NULL,
  row_version    INTEGER                     NOT NULL,
  transferred_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  source_id      INTEGER                     NOT NULL,
  target_id      INTEGER                     NOT NULL
);

ALTER TABLE ONLY account_balance
  ADD CONSTRAINT account_balance_pkey PRIMARY KEY (id);

ALTER TABLE ONLY account
  ADD CONSTRAINT account_pkey PRIMARY KEY (id);

ALTER TABLE ONLY budget_category
  ADD CONSTRAINT budget_category_pkey PRIMARY KEY (id);

ALTER TABLE ONLY budget_group
  ADD CONSTRAINT budget_group_pkey PRIMARY KEY (id);

ALTER TABLE ONLY entry
  ADD CONSTRAINT entry_pkey PRIMARY KEY (id);

ALTER TABLE ONLY payment_type
  ADD CONSTRAINT payment_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY reversed_entry
  ADD CONSTRAINT reversed_entry_pkey PRIMARY KEY (id);

ALTER TABLE ONLY scheduled_entry
  ADD CONSTRAINT scheduled_entry_pkey PRIMARY KEY (id);

ALTER TABLE ONLY transferred_entry
  ADD CONSTRAINT transferred_entry_pkey PRIMARY KEY (id);

ALTER TABLE ONLY reversed_entry
  ADD CONSTRAINT fk1iqnxlqey10hcalymnljpo90g FOREIGN KEY (reverse_id) REFERENCES entry (id);

ALTER TABLE ONLY entry
  ADD CONSTRAINT fk4co40llmt3yampxxsbfvp9itq FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE ONLY transferred_entry
  ADD CONSTRAINT fk64t2sbmvchcoauvqqwcrnwxuv FOREIGN KEY (source_id) REFERENCES entry (id);

ALTER TABLE ONLY reversed_entry
  ADD CONSTRAINT fkat6cuedchjym9foeli0m9mp9y FOREIGN KEY (reversed_id) REFERENCES entry (id);

ALTER TABLE ONLY payment_type
  ADD CONSTRAINT fkep7y2a9kr3d3hrx3pndtwgx37 FOREIGN KEY (payment_account_id) REFERENCES account (id);

ALTER TABLE ONLY entry
  ADD CONSTRAINT fkghkjyqo48o5s4w0s53bgxf7xw FOREIGN KEY (category_id) REFERENCES budget_category (id);

ALTER TABLE ONLY account_balance
  ADD CONSTRAINT fklixfd5d2qjux0b3peypcpeol1 FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE ONLY transferred_entry
  ADD CONSTRAINT fkp0yt1uxgw09fxl8m9tc3dcany FOREIGN KEY (target_id) REFERENCES entry (id);

ALTER TABLE ONLY scheduled_entry
  ADD CONSTRAINT fkqgoet9o8pfiksbyw1ttoavb21 FOREIGN KEY (category_id) REFERENCES budget_category (id);

ALTER TABLE ONLY entry
  ADD CONSTRAINT fkr3lxils1xig0di0plb59ejou3 FOREIGN KEY (payment_type_id) REFERENCES payment_type (id);

ALTER TABLE ONLY scheduled_entry
  ADD CONSTRAINT fkrunrylf37ag3b4tlnr0noe8vs FOREIGN KEY (entry_id) REFERENCES entry (id);

ALTER TABLE ONLY budget_category
  ADD CONSTRAINT fkstaoflueyek3q9v0o38557p2 FOREIGN KEY (group_id) REFERENCES budget_group (id);
