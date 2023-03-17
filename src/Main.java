package src;

public class Main {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Rood herkennen testen!");
        naoTyrone.bepaalOogKleur("red", 0.1f);

    }
}
