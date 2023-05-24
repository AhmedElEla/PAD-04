package src;

import com.aldebaran.qi.helper.EventCallback;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");

        naoTyrone.setBackgroundmovement(true);

        new Thread(new Nao.checkPoints(naoTyrone)).start();

        naoTyrone.touchButton("Front");
        naoTyrone.touchButton("Middle");
        naoTyrone.touchButton("Rear");

        naoTyrone.doWhile(1000, 10000);
    }
}
