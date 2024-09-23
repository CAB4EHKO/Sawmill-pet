package ru.uni.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "boards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wood_types_id", nullable = false)
    private WoodTypeEntity woodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workpieces_id", nullable = false)
    private WorkPieceEntity workPiece;

}
