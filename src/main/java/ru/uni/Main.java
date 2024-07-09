package ru.uni;

import ru.uni.enums.WoodType;
import ru.uni.model.Tree;
import ru.uni.service.SawmillService;


public class Main {

    public static void main(String[] args) {

        SawmillService sawmillService = new SawmillService();

        Tree[] trees = {
                new Tree(6, 500, WoodType.OAK),
                new Tree(5, 700, 111), // неизвестный тип древесины
                new Tree(4, 500, "MAPLE"),
                new Tree(8, 300, "OAK"),
                new Tree(5, 700, "Пихта") // неизвестный тип древесины
        };

        sawmillService.saw(trees);

    }
}