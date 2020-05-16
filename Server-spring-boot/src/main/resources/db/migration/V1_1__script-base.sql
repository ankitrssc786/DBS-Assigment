-- 
-- PostgreSQL Database: rlabs_flyway_mvn (UTF-8)
-- Initial SQL Script
--

-- create table user_request

CREATE TABLE user_request (
	request_id bigserial not null UNIQUE,
	submitted_by text not null,
	submitted_date timestamp not null default now(),
	approved_by text,
	approval_date date null,
	status text,
	account_number bigserial not null PRIMARY KEY
);


-- exclusion_account table
CREATE TABLE exclusion_account (
    account_number text null,
    PRIMARY KEY (account_number),
    CONSTRAINT fk_account_number FOREIGN KEY (account_number) REFERENCES user_request (account_number)

