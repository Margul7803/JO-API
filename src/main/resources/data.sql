-- Insert data into api-user
INSERT INTO "api-user" (uuid, name, last_name, email, password, phone, address, role)
VALUES 
    ('bc6bb639-f75b-44ed-990d-4f0ea8ad89e6', 'John', 'Doe', 'admin@example.com', '$2y$10$9ZW/6F3/K1JCODbL3RhmNOEgH3if9V9m9DtOGQ1o5QxYD8d.QM38G', '123456789', '123 Main St', 'ADMIN'),
    ('c739b603-0d59-40f6-942c-f4c0284c40ec', 'Jane', 'Smith', 'user@example.com', '$2y$10$Nd3bP.efibN5SEdMix4Kh.PD66Htu5QRuy74UJ9ndtTRKTjjmnwNm', '987654321', '456 Elm St', 'USER');

-- Insert data into stadium
INSERT INTO "stadium" (uuid, name, address, capacity)
VALUES 
    ('bcc57920-b0c7-4a7e-8b70-4e63779b3d03', 'Stadium A', '123 Stadium St', 10000),
    ('83869acd-d687-40f2-800e-87a8bf451b71', 'Stadium B', '456 Arena Ave', 15000);

-- Insert data into event
INSERT INTO "event" (uuid, name, max_entry, price, start_date, end_date, status, ticketing, stadium_uuid)
VALUES 
    ('101dc86a-af53-4ba6-aade-4202418ca73e', 'Event 1', 5000, 20.0, '2024-06-01 18:00:00', '2024-06-01 22:00:00', TRUE, TRUE, 'bcc57920-b0c7-4a7e-8b70-4e63779b3d03'),
    ('34d21fe5-3af4-4926-9c5c-0021ae3f8807', 'Event 2', 8000, 30.0, '2024-07-01 18:00:00', '2024-07-01 22:00:00', TRUE, TRUE, '83869acd-d687-40f2-800e-87a8bf451b71');

-- Insert data into client_order
INSERT INTO "client_order" (uuid, date, user_uuid, final_price)
VALUES 
    ('e34b3d73-157b-40fe-b063-f1d1630a3103', '2024-05-29 10:00:00', 'bc6bb639-f75b-44ed-990d-4f0ea8ad89e6', 50.0),
    ('0de3bee0-4be3-4477-9ed1-5f5d444991f8', '2024-05-30 12:00:00', 'c739b603-0d59-40f6-942c-f4c0284c40ec', 75.0);

-- Insert data into ticket
INSERT INTO "ticket" (uuid, client_name, client_last_name, client_order_uuid, event_uuid)
VALUES 
    ('e4537df9-cddf-4aa6-9624-eb92cb772307', 'Alice', 'Johnson', 'e34b3d73-157b-40fe-b063-f1d1630a3103', '101dc86a-af53-4ba6-aade-4202418ca73e'),
    ('9d465d27-b6d4-4e65-bfb3-443f438a5646', 'Bob', 'Smith', 'e34b3d73-157b-40fe-b063-f1d1630a3103', '101dc86a-af53-4ba6-aade-4202418ca73e'),
    ('c0772ca4-7a81-44ec-8cd9-869e508905c3', 'Charlie', 'Brown', '0de3bee0-4be3-4477-9ed1-5f5d444991f8', '34d21fe5-3af4-4926-9c5c-0021ae3f8807');
