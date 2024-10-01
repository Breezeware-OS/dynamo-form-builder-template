-- Drop table and sequence if they exist
DROP TABLE IF EXISTS dynamo.form_response;
DROP SEQUENCE IF EXISTS dynamo.form_response_seq;

-- Create new sequence
CREATE SEQUENCE dynamo.form_response_seq
  START WITH 1
  INCREMENT BY 1;

-- Create table form_response
CREATE TABLE dynamo.form_response (
    id BIGINT NOT NULL DEFAULT NEXT VALUE FOR dynamo.form_response_seq,
    form_id BIGINT NOT NULL,
    response_json TEXT NOT NULL,
    created_on TIMESTAMP,
    modified_on TIMESTAMP,
    CONSTRAINT form_response_pkey PRIMARY KEY (id),
    CONSTRAINT fk_form_response_form FOREIGN KEY (form_id)
    REFERENCES dynamo.form (id)
);

-- Add a new column unique_id to the form table
ALTER TABLE dynamo.form ADD COLUMN unique_id VARCHAR(50);

-- Drop the form_json column from the form table
ALTER TABLE dynamo.form DROP COLUMN form_json;

-- Drop the description column from the form_version table
ALTER TABLE dynamo.form_version DROP COLUMN description;
