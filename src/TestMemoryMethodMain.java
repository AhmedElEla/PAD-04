package src;


import java.util.List;

public class TestMemoryMethodMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();

        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");
        naoTyrone.bepaalOogKleur("white", 1f);
        naoTyrone.postureInput("StandInit", 1f);

        naoTyrone.detectRedBall();

        while (true) {
            naoTyrone.armenOmhoog();
            naoTyrone.checkBallonBoven();
            naoTyrone.armenLinks();
            naoTyrone.checkBallonLinks();
            naoTyrone.armenRechts();
            naoTyrone.checkBallonRechts();
            naoTyrone.armenOnder();
            naoTyrone.checkBallonLaag();
        }
    }
}
