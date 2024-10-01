package ru.uni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workpiece_diameter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkPiecesDiameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "milimeters_diameter", nullable = false, unique = true)
    private Integer diameter;

    @Column(name = "board_count", nullable = false)
    private Integer boardCount;

    @OneToMany(mappedBy = "diameter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkPieceEntity> workPieces = new ArrayList<>();
}
