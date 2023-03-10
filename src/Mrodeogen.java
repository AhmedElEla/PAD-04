package src;

public class Mrodeogen {
    public static void main(String[] args) throws Exception {
        Rodeogen nao = new Rodeogen();
        nao.verbind("nao.local", 9559);
        nao.praten("Die beweging is niet goed");
        nao.ogenAan();
        Thread.sleep(5000);
        nao.ogenUit();
    }
}
