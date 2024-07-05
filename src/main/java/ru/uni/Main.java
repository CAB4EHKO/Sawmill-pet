package ru.uni;

import ru.uni.model.Tree;
import ru.uni.model.ext.Maple;
import ru.uni.model.ext.Oak;
import ru.uni.model.ext.Pine;
import ru.uni.service.SawmillService;

public class Main {

    public static void main(String[] args) {
        SawmillService sawmillService = new SawmillService();

        Tree[] trees = {
                new Pine(6, 200, "Pine"),
                new Maple(4, 500, "Maple"),
                new Oak(8, 700, "Oak"),
        };
        sawmillService.saw(trees);
    }
}