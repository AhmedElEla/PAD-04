package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALLeds;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Nao {
    private Application application;
// Verbind met robot
    public void verbind(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }
// Praten
    public void praten(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);
    }
// Rode ogen
    public void rodeOgen() throws Exception {
        ALLeds ogenLeds = new ALLeds(this.application.session());
        ogenLeds.fadeRGB("FaceLeds", "red", 0.1f);
    }
// Groene ogen
    public void groeneOgen() throws Exception {
        ALLeds leds = new ALLeds(this.application.session());
        leds.fadeRGB("FaceLeds", "green", 0.1F); // float value om het te specificeren
    }
// Ogen worden wit
    public void ogenUit() throws Exception {
        ALLeds ogenLeds = new ALLeds(this.application.session());
        ogenLeds.on("FaceLeds");
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
}
