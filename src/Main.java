package src;

public class Main {
    public static void main(String[] args) {

        Nao naoRobot = new Nao();
        naoRobot.verbind("127.0.0.1", 9559);
        naoRobot.zeg("Hallo wereld!");


    }
}
