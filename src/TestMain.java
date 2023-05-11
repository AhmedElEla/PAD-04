package src;

import com.aldebaran.qi.CallError;
import src.threads.PrintXYZ;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Thread printXYZThread = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    Nao naoTyrone = null;
                    naoTyrone = new Nao();
                    try {
                        naoTyrone.returnPosition(1);
                    } catch (CallError e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    float[] position;
                    try {
                        position = naoTyrone.returnPosition(1);
                    } catch (CallError e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("_______");
                    for (float i : position) {
                        System.out.println(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (true);
            }
        });

        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");
        naoTyrone.postureInput("StandInit", 1f);

        // rode ball tracken
        naoTyrone.track("Head", 20f, "RedBall", 0.6, "None");

        // handen open en naar elkaar richten
        naoTyrone.ballonVastHouden();

        // beweeg ballon naar boven
        naoTyrone.armenOmhoog();
        Thread.sleep(2000);
        naoTyrone.returnPosition(1);
        naoTyrone.checkBallonBoven();

        // beweeg ballon naar links
        naoTyrone.armenLinks();
        Thread.sleep(2000);
        naoTyrone.returnPosition(1);
        naoTyrone.checkBallonLinks();

        // beweeg ballon naar rechts
        naoTyrone.armenRechts();
        Thread.sleep(2000);
        naoTyrone.returnPosition(1);
        naoTyrone.checkBallonRechts();

        // beweeg ballon naar beneden
        naoTyrone.armenOnder();
        Thread.sleep(2000);
        naoTyrone.returnPosition(1);
        naoTyrone.checkBallonLaag();

        // Stop tracking red ball
        naoTyrone.stopTracker();
    }
}
