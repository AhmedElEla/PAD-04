package src;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();

        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");

        naoTyrone.detectRedBall();

        naoTyrone.doWhile(1000, 10);
    }
}
