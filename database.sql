CREATE DATABASE BankApp OWNER postgres;

CREATE TABLE account (
   id varchar(100) PRIMARY KEY,
   user_bank_id varchar(100) NOT NULL ,
   balance decimal NOT NULL ,
   overdraft_balance decimal NOT NULL
);

CREATE TABLE user_bank (
   id varchar(100) PRIMARY KEY,
   names varchar(100) NOT NULL ,
   user_name varchar(50) UNIQUE NOT NULL ,
   password varchar(255) NOT NULL,
   email varchar(100) NOT NULL,
   phone varchar(50),
   account_id varchar(50),
   profile varchar(100) NOT NULL,
   created_date TIMESTAMP
);

CREATE TABLE transfer (
   id varchar(255) PRIMARY KEY,
   origin_account varchar(100) NOT NULL ,
   destination_account varchar(100) NOT NULL,
   amount decimal NOT NULL,
   created_date TIMESTAMP
);

ALTER TABLE account
ADD CONSTRAINT fk_user
    FOREIGN KEY (user_bank_id)
        REFERENCES user_bank(id);

ALTER TABLE transfer
ADD CONSTRAINT fk_origin_account
       FOREIGN KEY (origin_account)
           REFERENCES account(id);

ALTER TABLE transfer
ADD CONSTRAINT fk_destination_account
    FOREIGN KEY (destination_account)
        REFERENCES account(id);

INSERT INTO user_bank (id, names, user_name, password, email, phone, account_id, profile, created_date)
VALUES ('123456789','Pepito Perez','user1','24c9e15e52afc47c225b757e7bee1f9d','user1@mail.com','3131313131',null,'ADMIN', now());d