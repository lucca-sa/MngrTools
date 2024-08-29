CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(70) NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(70) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address VARCHAR(100) NOT NULL,
    department_id BIGINT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

INSERT INTO department (name) VALUES 
('HR'),
('Finance'),
('Engineering'),
('Marketing'),
('Sales'),
('Customer Support'),
('Product'),
('Legal'),
('IT'),
('Operations');

INSERT INTO users (name, email, phone, address, department_id) VALUES
('Alice Johnson', 'alice.johnson@example.com', '123-456-7890', '123 Elm St', 1),
('Bob Smith', 'bob.smith@example.com', '123-456-7891', '456 Oak St', 2),
('Carol Brown', 'carol.brown@example.com', '123-456-7892', '789 Pine St', 3),
('David Wilson', 'david.wilson@example.com', '123-456-7893', '101 Maple St', 4),
('Eve Davis', 'eve.davis@example.com', '123-456-7894', '202 Birch St', 5),
('Frank Miller', 'frank.miller@example.com', '123-456-7895', '303 Cedar St', 6),
('Grace Lee', 'grace.lee@example.com', '123-456-7896', '404 Elm St', 7),
('Hank Thompson', 'hank.thompson@example.com', '123-456-7897', '505 Oak St', 8),
('Ivy Martinez', 'ivy.martinez@example.com', '123-456-7898', '606 Pine St', 9),
('Jack White', 'jack.white@example.com', '123-456-7899', '707 Maple St', 10),
('Kathy Moore', 'kathy.moore@example.com', '123-456-7800', '808 Birch St', 1),
('Liam Taylor', 'liam.taylor@example.com', '123-456-7801', '909 Cedar St', 2),
('Mia Anderson', 'mia.anderson@example.com', '123-456-7802', '1010 Elm St', 3),
('Noah Thomas', 'noah.thomas@example.com', '123-456-7803', '1111 Oak St', 4),
('Olivia Jackson', 'olivia.jackson@example.com', '123-456-7804', '1212 Pine St', 5),
('Paul Harris', 'paul.harris@example.com', '123-456-7805', '1313 Maple St', 6),
('Quinn Clark', 'quinn.clark@example.com', '123-456-7806', '1414 Birch St', 7),
('Rita Lewis', 'rita.lewis@example.com', '123-456-7807', '1515 Cedar St', 8),
('Steve Walker', 'steve.walker@example.com', '123-456-7808', '1616 Elm St', 9),
('Tina Young', 'tina.young@example.com', '123-456-7809', '1717 Oak St', 10),
('Ursula Scott', 'ursula.scott@example.com', '123-456-7810', '1818 Pine St', 1),
('Victor Adams', 'victor.adams@example.com', '123-456-7811', '1919 Maple St', 2),
('Wendy Baker', 'wendy.baker@example.com', '123-456-7812', '2020 Birch St', 3),
('Xander Harris', 'xander.harris@example.com', '123-456-7813', '2121 Cedar St', 4),
('Yvonne Nelson', 'yvonne.nelson@example.com', '123-456-7814', '2222 Elm St', 5),
('Zachary Carter', 'zachary.carter@example.com', '123-456-7815', '2323 Oak St', 6);
