CREATE TABLE boards
(
    id            BIGINT PRIMARY KEY,
    wood_types_id BIGINT,
    workpieces_id BIGINT,
    FOREIGN KEY (wood_types_id) REFERENCES wood_types (id),
    FOREIGN KEY (workpieces_id) REFERENCES workpieces (id)
);
