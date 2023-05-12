package src;

public class FirstTestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");
        naoTyrone.postureInput("StandInit", 0.5f);

        naoTyrone.praten("Armen omhoog");
        naoTyrone.bepaalBehaviour("movement/ArmenOmhoog");
        Thread.sleep(500);
        naoTyrone.postureInput("StandInit", 0.3f);
        naoTyrone.praten("Armen omlaag");
        naoTyrone.bepaalBehaviour("movement/ArmenOmlaag");
        Thread.sleep(500);
        naoTyrone.postureInput("StandInit", 0.3f);
        naoTyrone.praten("Armen links");
        naoTyrone.bepaalBehaviour("movement/ArmenLinks");
        Thread.sleep(500);
        naoTyrone.postureInput("StandInit", 0.3f);
        naoTyrone.praten("Armen rechts");
        naoTyrone.bepaalBehaviour("movement/ArmenRechts");

    }
}
