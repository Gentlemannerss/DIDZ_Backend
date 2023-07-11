/*Create Products:*/
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (100, 'Digital Book', 19.99, 'EBOOK');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (101, 'The Digital world and Me', 29.99, 'BOOK');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (102, 'Office365 Workshop', 1149.99, 'WORKSHOP');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (103, 'PC & ME Cursus', 999.99, 'ONLINECURSUS');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (104, 'Group Coaching', 1499.99, 'GROUPCOACHING');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (105, 'Personal Coaching', 1999.99, 'COACHINGSESSIONS');

/* Create Users:*/
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (100, 'admin', '$2a$12$J5of065Lukv6dEf/veVhpeUCA63ID9rsC8hhTKtqpseHg/f1TskyS', 'admin', 'private@admin.nl', 'work@admin.nl', 'admin', true, 'male', 'admin', 'admin', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (101, 'user1', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (102, 'user2', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (103, 'user3', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (104, 'user4', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (105, 'user5', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (106, 'user6', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (107, 'user7', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (108, 'user8', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (109, 'user9', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (110, 'user10', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'user','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (201, 'coach', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'coach', 'private@coach.nl', 'coach@coach.nl', 'coach', true,'male', 'coach','coach', '0610101010');

/* Create Authorities:*/
INSERT INTO authorities (user_id, authority) VALUES (100, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (100, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (100, 'ROLE_COACH');
INSERT INTO authorities (user_id, authority) VALUES (101, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (102, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (103, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (104, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (105, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (106, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (107, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (108, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (109, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (110, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (201, 'ROLE_COACH');

/* Create Study Groups:*/

/* Create Reviews:*/
/*INSERT INTO Reviews (review_id, score, date_of_writing, review_description, customer, product) VALUES (100, 3, current_date, 'top product', 100, 100)
*/
/* Create Contact Forms:*/

/* Create Messages:*/

/* Create Invoices:*/