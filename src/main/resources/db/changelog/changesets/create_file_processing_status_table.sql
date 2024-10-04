CREATE TABLE file_processing_status
(
    id        BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(255),
    status    VARCHAR(50),
    comment   TEXT
);