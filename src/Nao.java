package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.*;
import src.configuration.ConfigureNao;
import src.leds.bepaalOogKleur;
import src.speech.TextToSpeech;

public class Nao {
    private Application application;
    private TextToSpeech tts;
// Verbind met robot
    public void verbind() {
        String robotUrl = "tcp://" + ConfigureNao.HOSTNAME + ":" + ConfigureNao.PORT;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }
// Praten
    public void praten(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        // (als de verandering niet werkt dit weer gebruiken tijdelijk) ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Robot iets laten zeggen
        this.tts.praten(tekst);
    }
// Oog leds bedienen
    public void bepaalOogKleur(String color, float duration) throws Exception {
        return;
    }
// Armen bewegen
    public void staan() throws Exception {
        ALRobotPosture robotPosture = new ALRobotPosture(this.application.session());
        robotPosture.goToPosture("StandInit", 0.8f);
    }
    public void rightShoulder() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("RShoulderRoll", Math.PI / -4, 0.2f, true);
    }

    public void leftShoulder() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("LShoulderRoll", Math.PI / 4, 0.2f, true);
    }
    public void rightShoulderUP() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("RShoulderPitch", Math.PI / -2, 1.0f, true);

    }
    public void leftShoulderUP() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("LShoulderPitch", Math.PI / -2, 1.0f, true);
    }
// Hoofd bewegen
    public void hoofdLinks() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("HeadYaw", Math.PI / 2, 1.0f, true);

    }

    public void hoofdRechts() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("LShoulderPitch", Math.PI / -2, 1.0f, true);
        robotPosture.angleInterpolation("HeadYaw", Math.PI / -2, 1.0f, true);
    }

    public void hoofdMidden() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("HeadYaw", Math.PI / 8, 2.0f, true);
    }
    public void hoofdBoven() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("HeadPitch", Math.PI / 4, 2.0f,true);
    }
    public void hoofdBeneden() throws Exception {
        ALMotion robotPosture;
        robotPosture = new ALMotion(this.application.session());
        robotPosture.angleInterpolation("HeadPitch", Math.PI / -2, 2.0f,true);
    }
// Rood herkennen
/*    public void roodHerkennen() throws Exception {
        ALRedBallDetection rodebal = new ALRedBallDetection(this.application.session());
        rodebal.
    }*/
}
