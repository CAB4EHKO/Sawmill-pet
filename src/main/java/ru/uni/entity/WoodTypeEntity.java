package ru.uni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.uni.enums.WoodType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wood_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WoodTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private WoodType name;

    @OneToMany(mappedBy = "woodType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkPieceEntity> workPieces = new ArrayList<>();

    @OneToMany(mappedBy = "woodType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardEntity> boards = new ArrayList<>();

}
