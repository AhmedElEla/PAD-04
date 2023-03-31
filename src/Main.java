// Dit is een project gemaakt door: Steven van den Nieuwenhoff, Cas Sombroek, Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

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
        naoTyrone.postureInput("StandInit", 0.8f);
    }


}
