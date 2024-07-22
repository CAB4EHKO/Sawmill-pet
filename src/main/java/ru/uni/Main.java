package ru.uni;

import ru.uni.enums.WoodType;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;
import ru.uni.service.SawmillService;


public class Main {

    public static void main(String[] args) {

        SawmillService sawmillService = new SawmillService();

        WorkPiece[] workPieces = new WorkPiece[]{
                new Tree(4, 200, "PINE"),
                new Tree(6, 500, "OAK"),
                new Tree(8, 700, "MAPLE"),
                new Tree(10, 500, "Неизвестная заготовка")
        };

        sawmillService.saw(workPieces);

    }
}