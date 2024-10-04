CREATE TABLE sawmill_result_items
(
    id                BIGSERIAL PRIMARY KEY,
    wood_type         VARCHAR(255),
    board_count       INTEGER,
    sawmill_result_id BIGINT,
    FOREIGN KEY (sawmill_result_id) REFERENCES sawmill_results (id)
);