package ru.uni.service;

import ru.uni.model.Tree;

/**
 * Класс SawmillService представляет собой лесопилку, которая обрабатывает массив объектов Tree
 * и подсчитывает количество досок, получаемых из каждого вида дерева (Сосна, Дуб, Клён).
 */
public class SawmillService {

    private int pineBoards = 0;
    private int oakBoards = 0;
    private int mapleBoards = 0;

    /**
     * Метод saw обрабатывает массив деревьев и подсчитывает количество досок,
     * полученных из каждого вида дерева в зависимости от их диаметра и длины.
     * Результаты подсчета выводятся на экран.
     *
     * @param trees массив объектов Tree, представляющих заготовки деревьев для обработки.
     */
    public void saw(Tree[] trees) {
        for (Tree tree : trees) {
            int boards = tree.getBoardsPerTwoMeters() * (tree.getLength() / 2);

            switch (tree.getWoodType()) {
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
        }

        // Вывод результатов на экран
        System.out.println("Pine: " + pineBoards);
        System.out.println("Oak: " + oakBoards);
        System.out.println("Maple: " + mapleBoards);
    }
}
