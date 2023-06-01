package src;

public class TestMain {
    public static void main(String[] args) throws Exception {
        NAO naoTyrone = new NAO();

        naoTyrone.connect();
        naoTyrone.talking("Verbonden");
        naoTyrone.volume(100);
        naoTyrone.setLanguage("Dutch");
        Thread.sleep(2000);
        naoTyrone.humanDetection();

        new Thread(new NAO.checkPoints(naoTyrone)).start();
        new Thread(new NAO.randomEyes(naoTyrone)).start();

        naoTyrone.touchButton("Front");
        naoTyrone.touchButton("Middle");
        naoTyrone.touchButton("Rear");

        naoTyrone.doWhile(1000, 10000);
    }
}
