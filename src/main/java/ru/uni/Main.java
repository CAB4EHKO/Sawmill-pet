package ru.uni;

import ru.uni.enums.WoodType;
import ru.uni.model.Tree;
import ru.uni.service.SawmillService;


public class Main {

    public static void main(String[] args) {

        SawmillService sawmillService = new SawmillService();

        Tree[] trees = {
                new Tree(6, 200, WoodType.PINE),
                new Tree(4, 500, WoodType.MAPLE),
                new Tree(8, 700, WoodType.OAK)
        };

        sawmillService.saw(trees);

    }
}