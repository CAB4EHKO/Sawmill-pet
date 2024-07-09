package ru.uni.service;

import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;
import ru.uni.model.Tree;

public class SawmillService {
    private int pineBoards = 0;
    private int oakBoards = 0;
    private int mapleBoards = 0;
    private int unknownWoodCount = 0;

    public void saw(Tree[] trees) {
        for (Tree tree : trees) {
            try {
                int boards = tree.getBoardsPerTwoMeters() * (tree.getLength() / 2);
                WoodType woodType = tree.getWoodType();

                switch (woodType) {
                    case PINE:
                        pineBoards += boards;
                        break;
                    case OAK:
                        oakBoards += boards;
                        break;
                    case MAPLE:
                        mapleBoards += boards;
                        break;
                }
            } catch (UnknownWoodTypeException e) {
                unknownWoodCount++;
            }
        }

        System.out.println("Pine: " + pineBoards);
        System.out.println("Oak: " + oakBoards);
        System.out.println("Maple: " + mapleBoards);
        System.out.println("Заготовок неизвестного происхождения было пропущено: " + unknownWoodCount);
    }
}

