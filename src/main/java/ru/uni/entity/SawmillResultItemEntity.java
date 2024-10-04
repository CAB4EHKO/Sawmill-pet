package ru.uni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sawmill_result_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SawmillResultItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String woodType;
    private Integer boardCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sawmill_result_id")
    private SawmillResultEntity result;
}
