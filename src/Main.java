package src;

public class Main {
    public static void main(String[] args) throws Exception {

        Armenbewegen nao = new Armenbewegen();
        nao.verbind("localhost", 50992);
        nao.praten("Hallo wereld!");
        nao.staan();







    }
}
