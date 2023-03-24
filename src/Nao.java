package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import src.configuration.ConfigureNao;
import src.leds.OogController;
import src.motion.MotionController;
import src.motion.PostureController;
import src.speech.TextToSpeech;

import java.util.ArrayList;

public class Nao {
    private Application application;
    private TextToSpeech tts;
    private OogController ogen;
    private PostureController posture;
    private MotionController motion;

// Verbind met robot
    public void verbind() throws Exception {
        String robotUrl = "tcp://" + ConfigureNao.HOSTNAME + ":" + ConfigureNao.PORT;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();

        tts = new TextToSpeech(application.session());
        ogen = new OogController(application.session());
        posture = new PostureController(application.session());
        motion = new MotionController(application.session());
    }
// Praten
    public void praten(String tekst) throws Exception {
        this.tts.praten(tekst);
    }
// Oog leds bedienen
    public void bepaalOogKleur(String color, float duration) throws Exception {
        ogen.bepaalOogKleur(color, duration);
    }
// Armen bewegen
    // Posture (stand, crouch & sit)
    public void postureInput(String postureName, float maxSpeedFraction) throws Exception {
        posture.postureInput(postureName, maxSpeedFraction);
    }
    public void bepaalMotion(String names, double angleLists, float timeLists, boolean isAbsolute) throws Exception {
        motion.bepaalMotion(names, angleLists, timeLists, isAbsolute);
    }

    public void roodHerkennen() throws Exception {
        // Create an instance of ALMemory and subscribe to the "redBallDetected" event
        ALMemory alMemory = new ALMemory(this.application.session());
        alMemory.subscribeToEvent("redBallDetected", new EventCallback() {
            @Override
            public void onEvent(Object o) throws InterruptedException, CallError {
                ArrayList<?> redBallList = (ArrayList<?>) o;

                System.out.println("Ik zie een rode object");
                for (Object redData : redBallList) {
                    System.out.println(redData);
                    // Perform any additional actions here
                }
            }
        });
    }
}
