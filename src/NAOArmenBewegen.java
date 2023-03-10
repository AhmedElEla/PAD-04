package src;
import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class NAOArmenBewegen {

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
}