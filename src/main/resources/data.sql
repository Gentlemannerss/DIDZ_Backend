/*Create Products:*/
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (100, 'Digital Book', 19.99, 'EBOOK');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (101, 'The Digital world and Me', 29.99, 'BOOK');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (102, 'Office365 Workshop', 1149.99, 'WORKSHOP');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (103, 'PC & ME Cursus', 999.99, 'ONLINECURSUS');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (104, 'Group Coaching', 1499.99, 'GROUPCOACHING');
INSERT INTO Products (product_id, product_name, price, product_type) VALUES (105, 'Personal Coaching', 1999.99, 'COACHINGSESSIONS');

/* Create Users:*/
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (100, 'admin', '$2a$12$J5of065Lukv6dEf/veVhpeUCA63ID9rsC8hhTKtqpseHg/f1TskyS', 'Admin Damon', 'private@admin.nl', 'work@admin.nl', 'admin', true, 'male', 'admin', 'admin', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (99, 'superAdmin', '$2a$12$J5of065Lukv6dEf/veVhpeUCA63ID9rsC8hhTKtqpseHg/f1TskyS', 'Super Admin Damon', 'private@admin.nl', 'work@admin.nl', 'admin', true, 'male', 'admin', 'admin', '0623232323');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (101, 'user1', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Sandra','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (102, 'user2', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Ilse','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (103, 'user3', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Paul','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (104, 'user4', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Sam','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (105, 'user5', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Nick','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (106, 'user6', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Emiel','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (107, 'user7', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Robert-jan','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (108, 'user8', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Alwyn','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (109, 'user9', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Lucinde','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (110, 'user10', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Jelmer','private@user.nl', 'user@user.nl', 'user', true, 'female', 'user', 'user', '0610101010');
INSERT INTO Users (id, username, password, full_name, privateemail, workemail, address, enabled, gender, company_name, company_address, phone_number) VALUES (201, 'coach', '$2a$12$4RoD7f6dsyZR1WuiJEXHWe86S91aWAhQKjU82WtJHXqPVcyf0ID.C', 'Coach Esmee', 'private@coach.nl', 'coach@coach.nl', 'coach', true,'male', 'coach','coach', '0610101010');

/* Create Authorities:*/
INSERT INTO authorities (user_id, authority) VALUES (100, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (99, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (99, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (99, 'ROLE_COACH');
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
INSERT INTO study_groups (group_id, group_name) VALUES (200, 'Class about Safe Internet');

UPDATE study_groups SET product_product_id = 105 WHERE group_id = 200;

INSERT INTO studygroup_user (studygroup_id, user_id) VALUES (200, 100);
INSERT INTO studygroup_user (studygroup_id, user_id) VALUES (200, 101);
INSERT INTO studygroup_user (studygroup_id, user_id) VALUES (200, 201);

/* Create Reviews:*/
INSERT INTO Reviews (review_id, score, date_of_writing, review_description, customer_id, product_id) VALUES (100, 3, current_date, 'top product', 101, 100)
