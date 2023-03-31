package src;

public class Main {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Halloo!");
//        naoTyrone.bepaalOogKleur("green", 0.1f);
        naoTyrone.postureInput("Crouch", 0.8f);


        // rode bal herkennen en tracken met keuze uit hoofd, arm(en)
        naoTyrone.track("Head", 100f, "RedBall", 0.6,"LArm");
        int counter=0;
        naoTyrone.doWhile(1000, 20);
        naoTyrone.stopTracker();

        // rode bal herkennen en printen als die herkent is
        naoTyrone.detectRedBall();
        naoTyrone.doWhile(1000,10);
// behaviour uitvoeren
		naoTyrone.bepaalBehaviour("pad4-4efa3c/dans");
    }

}
