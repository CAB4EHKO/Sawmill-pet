CREATE TABLE workpieces
(
    id                    BIGINT PRIMARY KEY,
    wood_types_id         BIGINT,
    workpiece_diameter_id BIGINT,
    meters_length         BIGINT,
    FOREIGN KEY (wood_types_id) REFERENCES wood_types (id),
    FOREIGN KEY (workpiece_diameter_id) REFERENCES workpiece_diameter (id)
);
