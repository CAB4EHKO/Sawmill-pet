package ru.uni.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "workpieces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkPieceEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "meters_length", nullable = false)
    private Integer length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wood_types_id", nullable = false)
    private WoodTypeEntity woodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workpiece_diameter_id", nullable = false)
    private WorkPiecesDiameterEntity diameter;

    @OneToMany(mappedBy = "workPiece", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardEntity> boards = new ArrayList<>();
}
