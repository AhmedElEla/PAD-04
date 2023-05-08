package src;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");
        naoTyrone.postureInput("StandInit", 1f);

        // handen open en naar elkaar richten
        naoTyrone.ballonVastHouden();

        // beweeg ballon naar boven
        naoTyrone.armenOmhoog();
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonBoven();

        // beweeg ballon naar links
        naoTyrone.armenLinks();
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonLinks();

        // beweeg ballon naar rechts
        naoTyrone.armenRechts();
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonRechts();

        // beweeg ballon naar beneden
        naoTyrone.armenOnder();
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonLaag();
    }
}
