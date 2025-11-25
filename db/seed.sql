INSERT INTO roles (name) VALUES ('ADMIN'),('INSTRUCTOR'),('USER');

-- NOTE: Replace <bcrypt-hash> with actual hashed passwords or use plaintext for testing (not secure).
INSERT INTO users (name,email,password_hash,role_id)
VALUES ('Admin','admin@example.com','$2y$10$placeholderhashadmin',1),
       ('Instructor','ins@example.com','$2y$10$placeholderhashins',2),
       ('User','user@example.com','$2y$10$placeholderhashuser',3);
