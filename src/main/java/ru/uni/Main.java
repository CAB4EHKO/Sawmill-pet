package ru.uni;

import ru.uni.service.ReaderService;
import ru.uni.service.SawmillService;

public class Main {
    public static void main(String[] args) {

        SawmillService sawmillService = new SawmillService();
        ReaderService readerService = new ReaderService();

        sawmillService.saw(readerService.readTrees());

    }
}