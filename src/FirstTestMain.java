package src;

public class FirstTestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");

       // naoTyrone.postureInput("StandInit", 2f);

        naoTyrone.track("Head", 20f, "RedBall", 0.6, "None");
        //naoTyrone.returnPosition(0);
        //naoTyrone.doWhile(500, 15);
        naoTyrone.stopTracker();

    }
}
