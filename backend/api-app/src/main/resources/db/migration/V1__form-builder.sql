-- create a new schema (H2 does not fully support CREATE SCHEMA, but you can use a schema as follows)
CREATE SCHEMA IF NOT EXISTS dynamo;
SET SCHEMA dynamo;

-- drop existing sequence and create a new sequence
DROP SEQUENCE IF EXISTS dynamo.form_seq;
CREATE SEQUENCE dynamo.form_seq
  START WITH 1000
  INCREMENT BY 1;

-- drop existing table and create a new table
DROP TABLE IF EXISTS dynamo.form;
CREATE TABLE dynamo.form (
    id BIGINT NOT NULL DEFAULT NEXT VALUE FOR dynamo.form_seq,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    version VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    form_json TEXT NOT NULL,
    created_on TIMESTAMP,
    modified_on TIMESTAMP,
    CONSTRAINT form_pkey PRIMARY KEY (id)
);

-- drop existing sequence and create a new sequence
DROP SEQUENCE IF EXISTS dynamo.form_version_seq;
CREATE SEQUENCE dynamo.form_version_seq
  START WITH 1
  INCREMENT BY 1;

-- drop existing table and create a new table
DROP TABLE IF EXISTS dynamo.form_version;
CREATE TABLE dynamo.form_version (
    id BIGINT NOT NULL DEFAULT NEXT VALUE FOR dynamo.form_version_seq,
    description TEXT NOT NULL,
    version VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    form_json TEXT NOT NULL,
    form BIGINT NOT NULL,
    created_on TIMESTAMP,
    modified_on TIMESTAMP,
    CONSTRAINT form_version_pkey PRIMARY KEY (id),
    CONSTRAINT form_version_fkey FOREIGN KEY (form)
    REFERENCES dynamo.form (id)
);
