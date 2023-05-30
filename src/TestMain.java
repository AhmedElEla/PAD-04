package src;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();

        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");
        naoTyrone.volume(50);
        naoTyrone.postureInput("Standinit", 1f);
        naoTyrone.setBackgroundmovement(true);

        new Thread(new Nao.checkPoints(naoTyrone)).start();
        new Thread(new Nao.randomEyes(naoTyrone)).start();

        naoTyrone.touchButton("Front");
        naoTyrone.touchButton("Middle");
        naoTyrone.touchButton("Rear");

        naoTyrone.doWhile(1000, 10000);
    }
}
