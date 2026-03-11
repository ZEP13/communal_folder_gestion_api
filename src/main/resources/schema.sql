CREATE TABLE folder (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE address (
    id BIGSERIAL PRIMARY KEY,
    full_user_name VARCHAR(255),
    street VARCHAR(255),
    postal_code VARCHAR(20),
    city VARCHAR(255),
    number VARCHAR(50),
    status VARCHAR(50) DEFAULT 'PENDING',
    folder_id BIGINT,
    CONSTRAINT fk_address_folder
        FOREIGN KEY (folder_id)
        REFERENCES folder(id)
        ON DELETE SET NULL
);

CREATE INDEX idx_address_folder_id
ON address(folder_id);
