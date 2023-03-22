package src;

public class Main {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Lets go!");
        naoTyrone.bepaalOogKleur("green", 0.1f);
        naoTyrone.postureInput("Crouch", 0.8f);
        naoTyrone.postureInput("StandInit", 0.8f);
        naoTyrone.postureInput("Crouch", 0.8f);
        naoTyrone.bepaalMotion("HeadPitch", Math.PI / 4, 2.0f, true);
    }
}
