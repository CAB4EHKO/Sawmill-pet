package ru.uni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "sawmill_results")
@Getter
@Setter
public class SawmillResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_number", nullable = false)
    private String batchNumber;

    @Column(name = "result", nullable = false)
    private String result;
}
