package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import src.configuration.ConfigureNao;
import src.core.BehaviourController;
import src.leds.OogController;
import src.memory.Memory;
import src.motion.MotionController;
import src.motion.PostureController;
import src.motion.TrackerController;
import src.vision.RedBallDetection;
import src.speech.TextToSpeech;

public class Nao {
    private Application application;
    private TextToSpeech tts;
    private OogController ogen;
    private PostureController posture;
    private MotionController motion;
    private RedBallDetection redBallDetection;
    private Memory redballmemory;
    private TrackerController redBallTracker;
    // can be used in later code maybe??
    private long redBallid;
	private BehaviourController behaviour;

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
        redBallDetection = new RedBallDetection(application.session());
        redballmemory = new Memory(application.session());
        redBalltracker = new TrackerController(application.session());
		behaviour = new BehaviourController(application.session());

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
// rood herkennen (is nog niet helemaal netjes)
    public void detectRedBall() throws Exception {
        redBallDetection.subscribe();
        redballmemory.subscribeToEvent("redBallDetected", data ->
                System.out.println("Red ball detected"));
    }

// rode bal tracken (bekijk de TrackerController voor comments)
    public void track(String pMode, Float pMaxDistance, String pTarget, Object pParams, String pEffector) throws CallError, InterruptedException {
        redBallTracker.startTracker(pMode, pMaxDistance, pTarget, pParams, pEffector);
    }
// niets meer tracken
    public void stopTracker() throws CallError, InterruptedException {
        redBallTracker.stopTracker();
    }
	public void bepaalBehaviour(String behavior) throws CallError, InterruptedException{
        behaviour.bepaalBehaviour(behavior);
    }
    public void behaviorTest() throws Exception {
        ALBehaviorManager behavior = new ALBehaviorManager(this.application.session());
        behavior.startBehavior("pad4-4efa3c/dans");
	}
}
