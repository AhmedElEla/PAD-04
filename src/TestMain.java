package src;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.returnPosition(0);
        naoTyrone.checkRedballPosition(0);
    }
}
