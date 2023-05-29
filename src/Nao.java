// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALMemory;
import src.audio.AudioController;
import src.configuration.ConfigureNao;
import src.configuration.Setup;
import src.core.BehaviourController;
import src.leds.OogController;
import src.motion.MotionController;
import src.motion.PostureController;
import src.motion.TrackerController;
import src.memory.Memory;
import src.motion.*;
import src.speech.AnimatedSpeech;
import src.vision.RedBallDetection;
import src.speech.TextToSpeech;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static src.Nao.knoppen.MIDDLE;
import static src.Nao.knoppen.REAR;

public class Nao {
    enum positions {
        LINKSBOVEN,
        MIDDENBOVEN,
        RECHTSBOVEN,
        MIDDEN,
        LINKSONDER,
        MIDDENONDER,
        RECHTSONDER,
    }
    enum knoppen {
        MIDDLE,
        REAR,
    }
    private Application application;
    private TextToSpeech tts;
    private OogController ogen;
    private PostureController posture;
    private MotionController motion;
    private RedBallDetection redBallDetection;
    private Memory memory;
    private ALMemory newALMemory;
    private static TrackerController redBallTracker;
	private BehaviourController behaviour;
    public static float X;
    public static float Y;
	private BackgroundMovement ALbackgroundmovement;
    private AnimatedSpeech animatedSpeech;
    private Setup systeem;
    private ArrayList<Point> pointsList;
    private positions ballPosition;
    private knoppen gedrukteKnop;
    private AudioController audioPlayer;
    private AudioController audioDevice;

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
		newALMemory = new ALMemory(application.session());
		ALbackgroundmovement = new BackgroundMovement(application.session());
        animatedSpeech = new AnimatedSpeech(application.session());
        systeem = new Setup(application.session());
        pointsList = new ArrayList<>();
        Point point = new Point(X, Y);
        audioPlayer = new AudioController(application.session());
        audioDevice = new AudioController(application.session());
    }
// Praten
    public void praten(String tekst) throws Exception {
        this.tts.praten(tekst);
    }
// Oog leds bedienen
    public void bepaalOogKleur(String color, float duration) throws Exception {
        ogen.bepaalOogKleur(color, duration);
    }
    public void randomEyeControl(float duration) throws CallError, InterruptedException {
        ogen.randomEyes(duration);
    }

// Audio player voor SFX in behaviours
    public void play(String filename) throws CallError, InterruptedException {
        audioPlayer.playSFX(filename);
    }

    public void volume(int volume) throws Exception {
        audioDevice.setOutputVolume(volume);
    }

// Armen bewegen
    // Posture (stand, crouch & sit)
    public void postureInput(String postureName, float maxSpeedFraction) throws Exception {
        posture.postureInput(postureName, maxSpeedFraction);
    }
// rood herkennen (is nog niet helemaal netjes)
    public void detectRedBall() throws Exception {
        System.out.println("Startg");
        redBallDetection.subscribe();
        memory.subscribeToEvent("redBallDetected", o -> {
            List<Object> data = (List<Object>) o;
            List<Float> BallInfo = (List<Float>) data.get(1);
            X = BallInfo.get(0);
            Y = BallInfo.get(1);

            this.X = X;
            this.Y = Y;

            System.out.println("X: " + X + " Y: " + Y);

            // LinksBoven
            if(X <= -0.13 && Y <= -0.10) {
                this.ballPosition = positions.LINKSBOVEN;
                System.out.println("LINKSBOVEN");
            }
            // MiddenBoven
            else if (X >= -0.12 && X <= 0.12 && Y <= -0.10) {
                this.ballPosition = positions.MIDDENBOVEN;
                System.out.println("MIDDENBOVEN");
            }
            // RechtsBoven
            else if (X >= 0.13 && X <= 2 && Y <= 0.10) {
                this.ballPosition = positions.RECHTSBOVEN;
                System.out.println("RECHTSBOVEN");
            }
            // Midden
            else if (Y >= -0.09f && Y <= 0.09f) {
                this.ballPosition = positions.MIDDEN;
                System.out.println("MIDDEN");
            }
            // LinksOnder
            else if (X <= -0.13 && Y >= 0.1) {
                this.ballPosition = positions.LINKSONDER;
                System.out.println("LINKSONDER");
            }
            // MiddenOnder
            else if (X >= -0.12 && X <= 0.12 && Y >= 0.1) {
                this.ballPosition = positions.MIDDENONDER;
                System.out.println("MIDDENONDER");
            }
            // RechtsOnder
            else if (X >= 0.10 && X <= 2 && Y >= 0.1) {
                this.ballPosition = positions.RECHTSONDER;
                System.out.println("RECHTSONDER");
            }

            pointsList.add(new Point(X, Y));

            if (pointsList.size() == 10) {
                try {
                    processPointsList();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
	public void touchButton(String sensorName) throws Exception {
        switch (sensorName) {
            case "Front":
                memory.subscribeToEvent("FrontTactilTouched", o -> {
                    float touch = (float)o;
                    float touchThreshold = 0.5f;
                    if (touch >= touchThreshold) {
                        setBackgroundmovement(false);
                        try {
                            // Do something when front button is pressed
                            postureInput("StandInit", 0.5f);
                            animateSpeech("^startTag(Hey_1) Hallo, ^wait(Hey_1) ^start(animations/Stand/Gestures/Explain_10) mijn naam is Cijmon. Ik ben gemaakt om jullie te helpen bewegen! Ik heb twee spel modes! 1 is gemaakt om mij na te doen en de andere is voor uw amusement! Klik de knop op het midden van mijn hoofd om mij na te doen of klik de achterste knop om vermaakt te worden!");
                            Thread.sleep(500);
                        } catch (Exception e) {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                    }
                });
                break;

            case "Middle":
                this.gedrukteKnop = knoppen.MIDDLE;
                memory.subscribeToEvent("MiddleTactilTouched", o -> {
                    float touch = (float)o;
                    float touchThreshold = 0.5f;
                    if (touch >= touchThreshold) {
                        setBackgroundmovement(false);
                        try {
                            // Do something when middle button is pressed
                            postureInput("StandInit", 0.5f);
                            animateSpeech(" ^start(animations/Stand/Gestures/Enthusiastic_5) Dit spel heet Cijmon zegt! Het werkt als volgt: Doe mijn bewegingen zo goed mogelijk na en probeer zoveel mogelijk plezier te hebben bij het spelen!");
                            Thread.sleep(500);
                            simonSays();
                            redBallDetection.unsubscribe();
                            postureInput("Crouch", 0.5f);
                        } catch (Exception e) {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                    }
                });
                break;

            case "Rear":
                memory.subscribeToEvent("RearTactilTouched", o -> {
                    float touch = (float)o;
                    float touchThreshold = 0.5f;
                    if (touch >= touchThreshold) {
                        setBackgroundmovement(false);
                        try {
                            postureInput("StandInit", 0.5f);
                            animateSpeech(" ^start(animations/Stand/BodyTalk/BodyTalk_10) Bent u klaar om te zien hoe de dans van kinderen voor kinderen eruit ziet? Geniet ervan!");
                            this.gedrukteKnop = knoppen.REAR;
                            bepaalBehaviour("movement/Dance 1");
                            Thread.sleep(500);
                        } catch (Exception e) {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                    }
                });
                break;

            default:
                throw new IllegalArgumentException("Invalid sensor name: " + sensorName);
        }
    }
//    public void linksBoven() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen links boven");
//        bepaalBehaviour("movement/ArmenLinksBoven");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.LINKSBOVEN) {
//            praten("Probeer uw armen iets meer naar linksboven te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
//    public void middenBoven() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen boven uw hoofd");
//        bepaalBehaviour("movement/ArmenOmhoog");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.MIDDENBOVEN) {
//            praten("Probeer uw armen iets meer boven uw hoofd te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
//    public void rechtsBoven() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen rechtsboven");
//        bepaalBehaviour("movement/ArmenRechtsBoven");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.RECHTSBOVEN) {
//            praten("Probeer uw armen iets meer naar rechtsboven te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
//    public void midden() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen in het midden");
//        bepaalBehaviour("movement/ArmenMidden");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.MIDDEN) {
//            praten("Probeer uw armen iets meer naar het midden te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
//    public void linksOnder() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen linksonder");
//        bepaalBehaviour("movement/ArmenLinksOnder");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.LINKSONDER) {
//            praten("Probeer uw armen iets meer naar linksonder te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
//    public void middenOnder() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen onder uw navel");
//        bepaalBehaviour("movement/ArmenOmlaag");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.MIDDENONDER) {
//            praten("Probeer uw armen iets meer onder uw navel te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
//    public void rechtsOnder() throws Exception {
//        postureInput("StandInit", 0.3f);
//        praten("Cijmon zegt armen rechtsonder");
//        bepaalBehaviour("movement/ArmenRechtsOnder");
//        Thread.sleep(2000);
//        while(this.ballPosition != positions.RECHTSONDER) {
//            praten("Probeer uw armen iets meer naar rechtsonder te bewegen!");
//            bepaalOogKleur("red", 0);
//        }
//        bepaalOogKleur("green", 0);
//        praten("Goed zo");
//        Thread.sleep(500);
//    }
    public void simonSays() throws Exception {
        List<positions> movesList = new ArrayList<>();
        Random random = new Random();

        // Generate 20 random moves
        for (int i = 0; i < 20; i++) {
            positions move;
            int lastMove = movesList.size() - 1;

            // Generate a random move that is not the same as the previous move
            do {
                move = positions.values()[random.nextInt(positions.values().length)];
            } while (lastMove >= 0 && move == movesList.get(lastMove));

            movesList.add(move);

            // Switch case method for each move
            switch (move) {
                case LINKSBOVEN:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen links boven");
                    bepaalBehaviour("movement/ArmenLinksBoven");
                    Thread.sleep(2000);
                    while (this.ballPosition != positions.LINKSBOVEN) {
                        praten("Probeer uw armen iets meer naar linksboven te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
                case MIDDENBOVEN:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen boven uw hoofd");
                    bepaalBehaviour("movement/ArmenOmhoog");
                    Thread.sleep(2000);
                    while(this.ballPosition != positions.MIDDENBOVEN) {
                        praten("Probeer uw armen iets meer boven uw hoofd te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
                case RECHTSBOVEN:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen rechtsboven");
                    bepaalBehaviour("movement/ArmenRechtsBoven");
                    Thread.sleep(2000);
                    while(this.ballPosition != positions.RECHTSBOVEN) {
                        praten("Probeer uw armen iets meer naar rechtsboven te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
                case MIDDEN:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen in het midden");
                    bepaalBehaviour("movement/ArmenMidden");
                    Thread.sleep(2000);
                    while(this.ballPosition != positions.MIDDEN) {
                        praten("Probeer uw armen iets meer naar het midden te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
                case LINKSONDER:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen linksonder");
                    bepaalBehaviour("movement/ArmenLinksOnder");
                    Thread.sleep(2000);
                    while(this.ballPosition != positions.LINKSONDER) {
                        praten("Probeer uw armen iets meer naar linksonder te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
                case MIDDENONDER:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen onder uw navel");
                    bepaalBehaviour("movement/ArmenOmlaag");
                    Thread.sleep(2000);
                    while(this.ballPosition != positions.MIDDENONDER) {
                        praten("Probeer uw armen iets meer onder uw navel te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
                case RECHTSONDER:
                    postureInput("StandInit", 0.3f);
                    praten("Cijmon zegt armen rechtsonder");
                    bepaalBehaviour("movement/ArmenRechtsOnder");
                    Thread.sleep(2000);
                    while(this.ballPosition != positions.RECHTSONDER) {
                        praten("Probeer uw armen iets meer naar rechtsonder te bewegen!");
                        bepaalOogKleur("red", 0);
                    }
                    bepaalOogKleur("green", 0);
                    praten("Goed zo");
                    Thread.sleep(500);
                    break;
            }
        }
        animateSpeech(" animations/Stand/Gestures/Enthusiastic_5 Bedankt voor het spelen, hopelijk heeft uw net zoveel plezier gehad bij het spelen als wij dat hebben gehad met het maken van dit spel!");
    }
    public void animateSpeech(String text) throws CallError, InterruptedException {
        animatedSpeech.animateText(text);
    }
    public void naoRobotNaam(String name) throws CallError, InterruptedException {
        systeem.changeName(name);
    }
    public void processPointsList() throws Exception {
        for (Point point : pointsList) {
            X = point.getX();
            Y = point.getY();
            // System.out.println("X = " + X + " Y = " + Y);
        }
        pointsList.clear();
    }

    // Deze thread moet nog aangepast worden zodat hij wel aangaat als de middenknop gedrukt wordt
    static class checkPoints implements Runnable {
        private Nao tyrone2;
        public checkPoints (Nao tyrone2) {
            this.tyrone2 = tyrone2;
        }
        @Override
        public void run() {
            while (tyrone2.gedrukteKnop == MIDDLE) {
                try {
                    System.out.println("Thread checkpoints");
                    this.tyrone2.detectRedBall();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static class randomEyes implements Runnable {
        private Nao tyrone3;
        public randomEyes (Nao tyrone3) {
            this.tyrone3 = tyrone3;
        }
        @Override
        public void run() {
            while (tyrone3.gedrukteKnop == REAR) {
                try {
                    this.tyrone3.randomEyeControl(60f);
                } catch (CallError e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}



// Code dat gemaakt is door ons maar niet nodig blijkt te zijn bij het uiteindelijke product

/*
    public void bepaalMotion(String names, double angleLists, float timeLists, boolean isAbsolute) throws Exception {
        motion.bepaalMotion(names, angleLists, timeLists, isAbsolute);
    }

// rode bal tracken (bekijk de TrackerController voor comments)
    public void track(String pMode, Float pMaxDistance, String pTarget, Object pParams, String pEffector) throws CallError, InterruptedException {
        redBallTracker.startTracker(pMode, pMaxDistance, pTarget, pParams, pEffector);
    }
// niets meer tracken
    public void stopTracker() throws CallError, InterruptedException {
        redBallTracker.stopTracker();
    }

    public float[] returnPosition(int index) throws CallError, InterruptedException {

        return redBallTracker.getPosition(1);
    }

    public void checkBallonBoven() throws Exception {
        bepaalOogKleur("white", 0);
        //System.out.println("ik check de ballon hoogte");

        while (true) {
            if (x >= -2 && x <= 2 && y >= -2 && y <= -0.2) {
                bepaalOogKleur("green", 0);
                praten("Goed gedaan");
                break;
            } else {
                praten("Beweeg nu je armen omhoog!");
                bepaalOogKleur("red", 0);
            }
            Thread.sleep(3000);
        }
    }
    public void checkBallonLinks() throws Exception {
        bepaalOogKleur("white", 0);
        //System.out.println("ik check de ballon hoogte");

        while (true) {
            if (x >= -2 && x <= 0 && y >= -0.2 && y <= 0.2) {
                bepaalOogKleur("green", 0);
                praten("Goed gedaan");
                break;
            } else {
                praten("Beweeg nu je armen naar links!");
                bepaalOogKleur("red", 0);
            }
            Thread.sleep(3000);
        }
    }
    public void checkBallonRechts() throws Exception {
        bepaalOogKleur("white", 0);
        //System.out.println("ik check de ballon hoogte");

        while (true) {
            if (x >= 0 && x <= 2 && y >= -0.2 && y <= 0.2) {
                bepaalOogKleur("green", 0);
                praten("Goed gedaan");
                break;
            } else {
                praten("Beweeg nu je armen naar rechts!");
                bepaalOogKleur("red", 0);
            }
            Thread.sleep(3000);
        }
    }
    public void checkBallonLaag() throws Exception {
        bepaalOogKleur("white", 0);
        //System.out.println("ik check de ballon hoogte");

        while (true) {
            if (x >= -2 && x <= 2 && y >= 0.1 && y <= 2) {
                bepaalOogKleur("green", 0);
                praten("Goed gedaan");
                break;
            } else {
                praten("Beweeg nu je armen naar beneden!");
                bepaalOogKleur("red", 0);
            }
            Thread.sleep(3000);
        }
    }
    public void armenOmhoog() throws Exception {
        motion.shoulderRollControl(0.0872665, -0.0872665);
        motion.shoulderPitchControl(-1.5, -1.5);
    }
    // armen links en rechts moet veranderd worden zodat de elleboog een beetje gebogen is
    public void armenRechts() throws Exception {
        motion.shoulderPitchControl(0, 0);
        motion.shoulderRollControl(0.314159, 0.314159); // if LRoll is too short try a higher digit max 76
    }
    // armen links en rechts moet veranderd worden zodat de elleboog een beetje gebogen is
    public void armenLinks() throws Exception {
        motion.shoulderRollControl(-0.314159, -0.314159);
    }
    public void armenOnder() throws Exception {
        motion.shoulderRollControl(0.0872665, -0.0872665);
        motion.shoulderPitchControl(1.09956, 1.09956);
    }*/
