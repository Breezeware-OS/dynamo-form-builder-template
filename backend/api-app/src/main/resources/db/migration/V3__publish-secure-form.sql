-- Drop table and sequence if they exist
DROP TABLE IF EXISTS dynamo.form_invitation;
DROP SEQUENCE IF EXISTS dynamo.form_invitation_seq;

-- Create new sequence
CREATE SEQUENCE dynamo.form_invitation_seq
  START WITH 1
  INCREMENT BY 1;

-- Create table form_invitation
CREATE TABLE dynamo.form_invitation (
    id BIGINT NOT NULL DEFAULT NEXT VALUE FOR dynamo.form_invitation_seq,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    form_id BIGINT NOT NULL,
    created_on TIMESTAMP,
    modified_on TIMESTAMP,
    CONSTRAINT form_invitation_pkey PRIMARY KEY (id),
    CONSTRAINT fk_form_invitation_form FOREIGN KEY (form_id)
    REFERENCES dynamo.form (id)
);

-- Add new columns to the form table
ALTER TABLE dynamo.form ADD COLUMN access_type VARCHAR(50);
ALTER TABLE dynamo.form ADD COLUMN owner VARCHAR(150);

-- Add new column to the form_response table
ALTER TABLE dynamo.form_response ADD COLUMN email VARCHAR(150);
