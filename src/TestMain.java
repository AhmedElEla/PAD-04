package src;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();

        // beweeg ballon naar boven
        // hier komt de beweging van armen omhoog
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonBoven();

        // beweeg ballon naar links
        // hier komt de beweging van armen naar links
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonLinks();

        // beweeg ballon naar rechts
        // hier komt de beweging van armen naar rechts
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonRechts();

        // beweeg ballon naar beneden
        // hier komt de beweging van armen naar beneden
        Thread.sleep(2000);
        naoTyrone.returnPosition(0);
        naoTyrone.checkBallonLaag();
    }
}
