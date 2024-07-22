package ru.uni.service;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;

public class SawmillService {
    private int pineBoards = 0;
    private int oakBoards = 0;
    private int mapleBoards = 0;
    private int unknownWoodCount = 0;

    //  для каждой заготовки определяем количесво досок и суммируем по типу
    public void saw(WorkPiece[] workPieces) {
        for (WorkPiece workPiece : workPieces) {
            int boards = getCountBoards(workPiece);

            // определяется тип заготовки

            try {
                switch (workPiece.woodType()) {
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
            } catch (IllegalArgumentException e) {
                unknownWoodCount++;
            }
        }


    }

    // Получить колличество досок исходя из длинны и диаметра загтовки
    // Исходя из диаметра заготовки получить колличество досок на 2 метра длинны
    // Выделить основные сущности которые будут использоватьться
    private int getCountBoards(WorkPiece workPiece) {

        Diameter diameter = workPiece.getDiameter();
        int boardsCountByDiameter = diameter.getBoardsPerTwoMeters();


        return boardsCountByDiameter * (workPiece.getLength() / 2);
    }
}

