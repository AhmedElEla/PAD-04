package src;

import src.speech.Praten;

public class Mpraten {
    public static void main(String[] args) throws Exception {

        Praten naoRobot = new Praten();
        naoRobot.verbind("127.0.0.1", 9559);
        naoRobot.zeg("Hallo wereld!");


    }
}
