// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALAutonomousBlinking;
import src.configuration.ConfigureNao;
import src.core.BehaviourController;
import src.leds.OogController;
import src.motion.MotionController;
import src.motion.PostureController;
import src.motion.TrackerController;
import src.memory.Memory;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALAutonomousBlinking;
import src.motion.*;
import src.vision.RedBallDetection;
import src.speech.TextToSpeech;

public class Nao {
    private Application application;
    private TextToSpeech tts;
    private OogController ogen;
    private PostureController posture;
    private MotionController motion;
    private RedBallDetection redBallDetection;
    private Memory memory;
    private TrackerController redBallTracker;

    // can be used in later code maybe??
    private long redBallid;
	private BehaviourController behaviour;
    private BackgroundMovement ALbackgroundmovement;


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
        memory = new Memory(application.session());
        redBallTracker = new TrackerController(application.session());
		behaviour = new BehaviourController(application.session());
        ALbackgroundmovement = new BackgroundMovement(application.session());
        autonomousLife = new ALAutonomousLife(application.session());

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
        memory.subscribeToEvent("redBallDetected", data ->
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
// do while loop om tijdelijk events te controllen
    public void doWhile(int millis, int time) throws InterruptedException {
        int counter = 0;
        do {
            Thread.sleep(millis);
            counter++;
        } while (counter<time);
    }
	public void bepaalBehaviour(String behavior) throws CallError, InterruptedException{
        behaviour.bepaalBehaviour(behavior);
    }
    public void setBackgroundmovement(boolean enabled) throws CallError, InterruptedException {
        ALbackgroundmovement.moveInBackground(enabled);
    }
    public void checkBallonLinks() throws Exception {
        float[] position = returnPosition(0);

    public void touchButton(String sensorName, EventCallback touchEventCallback) throws Exception {
        switch (sensorName) {
            case "Front":
                memory.subscribeToEvent("FrontTactilTouched", touchEventCallback);
                break;

            case "Middle":
                memory.subscribeToEvent("MiddleTactilTouched", touchEventCallback);
                break;

            case "Rear":
                memory.subscribeToEvent("RearTactilTouched", touchEventCallback);
                break;

            default:
                throw new IllegalArgumentException("Invalid sensor name: " + sensorName);
        }
    }


        while (ballonRechts == false)
            bepaalOogKleur("Red", 1);
        bepaalOogKleur("Green", 1);
    }
    public void checkBallonLaag() throws Exception {
        float[] position = returnPosition(0);

        float x = position[0];
        float y = position[1];
        float z = position[2];   // hoe werkt de z axis, wat zijn de limieten? is die uberhaupt nodig?

        boolean ballonLaag = x >= -640 && x <= 640 && y <= -240 && y >= -480;

        while (ballonLaag == false)
            bepaalOogKleur("Red", 1);
        bepaalOogKleur("Green", 1);
    }
    public void ballonVastHouden() throws CallError, InterruptedException {
        motion.fingerControl();
    }
    public void armenOmhoog() throws Exception {
        motion.shoulderRollControl(0.0872665, -0.0872665);
        motion.shoulderPitchControl(-1.5708, -1.5708);
    }
    // armen links en rechts moet veranderd worden zodat de elleboog een beetje gebogen
    public void armenLinks() throws Exception {
        motion.shoulderPitchControl(0, 0);
        motion.shoulderRollControl(0.314159, 0.314159); // if LRoll is too short try a higher digit max 76
    }
    // armen links en rechts moet veranderd worden zodat de elleboog een beetje gebogen
    public void armenRechts() throws Exception {
        motion.shoulderRollControl(-0.314159, -0.314159);
    }
    public void armenOnder() throws Exception {
        motion.shoulderRollControl(0.0872665, -0.0872665);
        motion.shoulderPitchControl(1.09956, 1.09956);
    }
}
