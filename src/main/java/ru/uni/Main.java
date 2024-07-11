package ru.uni;

import org.apache.log4j.Logger;
import ru.uni.service.ReaderService;
import ru.uni.service.SawmillService;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {

        SawmillService sawmillService = new SawmillService();
        ReaderService readerService = new ReaderService();

        sawmillService.saw(readerService.readTrees());
        logger.info("Вызван метод: saw");

    }
}