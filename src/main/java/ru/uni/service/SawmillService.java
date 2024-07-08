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
            int boards = 0;

            // Определение количества досок, исходя из диаметра и длинны дерева
            if (tree.getDiameter() == 200) {
                boards = 3 * (tree.getLength() / 2);
            }  else if (tree.getDiameter() == 700) {
                boards = 12 * (tree.getLength() / 2);
            } else if (tree.getDiameter() == 500) {
                boards = 7 * (tree.getLength() / 2);
            }

            // Добавление полученных досок к соответствующему виду дерева
            switch (tree.getWoodType()) {
                case "Pine":
                    pineBoards += boards;
                    System.out.println(123123);
                    break;
                case "Oak":
                    oakBoards += boards;
                    break;
                case "Maple":
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
