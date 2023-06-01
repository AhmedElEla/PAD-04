// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
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
import src.vision.BasicAwareness;
import src.vision.RedBallDetection;
import src.speech.TextToSpeech;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private RedBallDetection redBallDetection;
    private Memory memory;
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
    private BasicAwareness ALbasicawareness;
    private AutonomousLife ALautonomouslife;
    private ALMemory newALMemory;
    private static TrackerController redBallTracker;
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
        redBallDetection = new RedBallDetection(application.session());
        memory = new Memory(application.session());
        redBallTracker = new TrackerController(application.session());
		behaviour = new BehaviourController(application.session());
		newALMemory = new ALMemory(application.session());
		ALbackgroundmovement = new BackgroundMovement(application.session());
        animatedSpeech = new AnimatedSpeech(application.session());
        systeem = new Setup(application.session());
        pointsList = new ArrayList<>();
        audioPlayer = new AudioController(application.session());
        audioDevice = new AudioController(application.session());
        ALbasicawareness = new BasicAwareness(application.session());
        ALautonomouslife = new AutonomousLife(application.session());
        Point point = new Point(X, Y);

        memory.subscribeToEvent("EngagementZones/PersonEnteredZone2", (EventCallback<Integer>) id -> {
            if(waitForPeople) {
                if (id > 0) {

                    System.out.println("print 1 wait for peeople");
                    animateSpeech("^start (movements1/wave) Welkom! klik op het eerste knop op mijn hoofd om de intro te beginnen ^wait (movements1/wave)");
                    autonomousState("disabled");
                    waitForPeople = false;

                }
            }
        });
    }
// robot instelling
    public void naoRobotNaam(String name) throws CallError, InterruptedException {
        systeem.changeName(name);
    }
// Praten
    public void talking(String tekst) throws Exception {
        this.tts.talk(tekst);
    }
    public void setLanguage(String language) throws CallError, InterruptedException {
        this.tts.Language(language);
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
	public void bepaalBehaviour(String behavior) throws CallError, InterruptedException{
        behaviour.bepaalBehaviour(behavior);
    }
	public void setBackgroundmovement(boolean enabled) throws CallError, InterruptedException {
        ALbackgroundmovement.moveInBackground(enabled);
	}

// Autonomous life
    public void setEngagementMode (String modename) throws CallError, InterruptedException {
        ALbasicawareness.engagementMode(modename);
    }
    public void setHeadTracker (String trackingmode) throws CallError, InterruptedException {
        ALbasicawareness.headTracker(trackingmode);
    }
    public void stimulusDetection (String stimtype, boolean enable) throws CallError, InterruptedException {
        ALbasicawareness.stimulusMode(stimtype, enable);
    }
    public void autonomousState (String state) throws CallError, InterruptedException {
        ALautonomouslife.autonomousState(state);
    }
    public void animateSpeech(String text) throws CallError, InterruptedException {
        animatedSpeech.animateText(text);
    }
    boolean waitForPeople = false;

    public void waitForPeople() throws Exception {
        autonomousState("solitary");
        stimulusDetection("Movement", false);
        stimulusDetection("People", true);
        setEngagementMode("SemiEngaged");
        setHeadTracker("Head");
        waitForPeople = true;
    }

//  do while loop om tijdelijk events te controllen
    public void doWhile(int millis, int time) throws Exception {
        int counter = 0;
        do {
            Thread.sleep(millis);
            counter++;
        } while (counter < time);
    }

//  rood herkennen methode met enum definitie per "locatie"
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

    public void processPointsList() throws Exception {
        for (Point point : pointsList) {
            X = point.getX();
            Y = point.getY();
            // System.out.println("X = " + X + " Y = " + Y);
        }
        pointsList.clear();
    }
    public void linksBoven() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen links boven");
        bepaalBehaviour("movement/ArmenLinksBoven");
        Thread.sleep(4000);
        while(this.ballPosition != positions.LINKSBOVEN) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar linksboven te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Goed zo");
        Thread.sleep(500);
    }
    public void middenBoven() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen boven uw hoofd");
        bepaalBehaviour("movement/ArmenOmhoog");
        Thread.sleep(4000);
        while(this.ballPosition != positions.MIDDENBOVEN) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer boven uw hoofd te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Lekker bezig");
        Thread.sleep(500);
    }
    public void rechtsBoven() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen rechtsboven");
        bepaalBehaviour("movement/ArmenRechtsBoven");
        Thread.sleep(4000);
        while(this.ballPosition != positions.RECHTSBOVEN) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar rechtsboven te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("U doet het prachtig");
        Thread.sleep(500);
    }
    public void midden() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen in het midden");
        bepaalBehaviour("movement/ArmenMidden");
        Thread.sleep(4000);
        while(this.ballPosition != positions.MIDDEN) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar het midden te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Wauw perfect");
        Thread.sleep(500);
    }
    public void linksOnder() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen linksonder");
        bepaalBehaviour("movement/ArmenLinksOnder");
        Thread.sleep(4000);
        while(this.ballPosition != positions.LINKSONDER) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar linksonder te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Goedgedaan");
        Thread.sleep(500);
    }
    public void middenOnder() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen onder uw navel");
        bepaalBehaviour("movement/ArmenOmlaag");
        Thread.sleep(4000);
        while(this.ballPosition != positions.MIDDENONDER) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer onder uw navel te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Fantastisch");
        Thread.sleep(500);
    }
    public void rechtsOnder() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen rechtsonder");
        bepaalBehaviour("movement/ArmenRechtsOnder");
        Thread.sleep(4000);
        while(this.ballPosition != positions.RECHTSONDER) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar rechtsonder te bewegen!");
            bepaalOogKleur("red", 0);
        }
        bepaalOogKleur("green", 0);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("U bent de beste!");
        Thread.sleep(500);
    }
    public void simonSays() throws Exception {
        List<positions> movesList = new ArrayList<>();
        Random random = new Random();

        // Generate 20 random moves
        for (int i = 0; i < 6; i++) {
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
                    linksBoven();
                    break;
                case MIDDENBOVEN:
                    middenBoven();
                    break;
                case RECHTSBOVEN:
                    rechtsBoven();
                    break;
                case MIDDEN:
                    midden();
                    break;
                case LINKSONDER:
                    linksOnder();
                    break;
                case MIDDENONDER:
                    middenOnder();
                    break;
                case RECHTSONDER:
                    rechtsOnder();
                    break;
            }
        }
        animateSpeech(" ^start (animations/Stand/Gestures/Enthusiastic_5) Bedankt voor het spelen, hopelijk heeft uw net zoveel plezier gehad bij het spelen als wij dat hebben gehad met het maken van dit spel!");
    }

//  Hoofd knoppen besturing
	public void touchButton(String sensorName) throws Exception {
        switch (sensorName) {
            case "Front":
                memory.subscribeToEvent("FrontTactilTouched", o -> {
                    float touch = (float)o;
                    float touchThreshold = 0.5f;
                    if (touch >= touchThreshold) {
                        setBackgroundmovement(false);
                        try {
                            play("/opt/aldebaran/var/www/apps/movement/bell.wav");
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
                memory.subscribeToEvent("MiddleTactilTouched", o -> {
                    float touch = (float)o;
                    float touchThreshold = 0.5f;
                    if (touch >= touchThreshold) {
                        setBackgroundmovement(false);
                        try {
                            // Do something when middle button is pressed
                            play("/opt/aldebaran/var/www/apps/movement/bell.wav");
                            postureInput("StandInit", 0.5f);
                            animateSpeech(" ^start(animations/Stand/Gestures/Enthusiastic_5) Dit spel heet Cijmon zegt! Het werkt als volgt: Doe mijn bewegingen zo goed mogelijk na en probeer zoveel mogelijk plezier te hebben bij het spelen!");
                            Thread.sleep(500);
                            //detectRedBall();
                            this.gedrukteKnop = knoppen.MIDDLE;
                            simonSays();
                            bepaalOogKleur("green", 0f);
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
                            play("/opt/aldebaran/var/www/apps/movement/bell.wav");
                            postureInput("StandInit", 0.5f);
                            animateSpeech(" ^start(animations/Stand/BodyTalk/BodyTalk_10) Bent u klaar om te zien hoe de dans van kinderen voor kinderen eruit ziet? Geniet ervan!");
                            this.gedrukteKnop = knoppen.REAR;
                            bepaalBehaviour("movement/Dance 1");
                            Thread.sleep(500);
                            bepaalOogKleur("green", 0f);
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

//  Threads om tegelijkertijd functies uit te voeren
    static class checkPoints implements Runnable {
        private Nao tyrone2;
        public checkPoints (Nao tyrone2) {
            this.tyrone2 = tyrone2;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (this.tyrone2.gedrukteKnop == knoppen.MIDDLE) {
                    System.out.println("detecting redball");
                    try {
                        this.tyrone2.detectRedBall();
                    } catch (Exception e) {
                        System.err.println("Error occurred while interacting with ALRedBallDetection: " + e.getMessage());
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                    this.tyrone2.gedrukteKnop = null;
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
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (this.tyrone3.gedrukteKnop == knoppen.REAR) {
                    System.out.println("thread random eyes");
                    try {
                        this.tyrone3.randomEyeControl(1f);
                    } catch (CallError e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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
