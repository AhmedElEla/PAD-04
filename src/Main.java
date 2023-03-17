package src;

public class Main {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind("nao.local", 9559);
        naoTyrone.praten("Alle code zit in twee files!");
        naoTyrone.groeneOgen();
        naoTyrone.rodeOgen();
        naoTyrone.ogenUit();
        naoTyrone.staan();
        naoTyrone.leftShoulder();
        naoTyrone.rightShoulder();
        naoTyrone.leftShoulderUP();
        naoTyrone.rightShoulderUP();
        naoTyrone.hoofdLinks();
        naoTyrone.hoofdRechts();
        naoTyrone.hoofdMidden();
        naoTyrone.hoofdBoven();
        naoTyrone.hoofdBeneden();

    }
}
