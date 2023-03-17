package src.movement;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Hoofdbewegen {
    private String naam;
    private Application application;

    public void verbind(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }
    public void staan() throws Exception {
        ALRobotPosture robotPosture = new ALRobotPosture(this.application.session());
        robotPosture.goToPosture("StandInit", 0.8f);
    }
    public void praten(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);
    }
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
