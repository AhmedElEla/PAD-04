/**
 * The goal of this project is to make the NAO robot play a simon says game with elderly people And perform a small
 * concert so the elderly people can enjoy their time and dance alongside the NAO
 * Project group : PAD-04
 * @authors Ahmed El Ela and Valentijn Bruggeman
 */

package src;

// NAOQI aldebaran imports
import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import src.audio.AudioController;
import src.configuration.ConfigureNao;
import src.configuration.Setup;
import src.core.BehaviourController;
import src.interaction_engine.AutonomousLife;
import src.interaction_engine.BackgroundMovement;
import src.leds.EyeController;
import src.motion.MotionController;
import src.motion.PostureController;
import src.motion.TrackerController;
import src.memory.Memory;
import src.point.Point;
import src.speech.AnimatedSpeech;
import src.interaction_engine.BasicAwareness;
import src.vision.RedBallDetection;
import src.speech.TextToSpeech;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NAO {

    // enums
    enum Positions {
        TOPLEFT,
        TOPCENTER,
        TOPRIGHT,
        MIDDLE,
        BOTTOMLEFT,
        BOTTOMCENTER,
        BOTTOMRIGHT,
    }
    enum Buttons {
        MIDDLE,
        REAR,
    }

    // Acces modifiers
    final static int EEN = 1;
    final static int NULL = 0;
    final static float NULLF = 0;
    final static int TIEN = 10;
    public static float X;
    public static float Y;
    public static boolean humanDetection = false;
    private Application application;
    private TextToSpeech ALTextToSpeech;
    private EyeController ALLeds;
    private PostureController ALRobotPosture;
    private RedBallDetection ALRedBallDetection;
    private Memory ALMemory;
	private BehaviourController ALBehaviourManager;
	private BackgroundMovement ALbackgroundMovement;
    private AnimatedSpeech ALAnimatedSpeech;
    private Setup ALSystem;
    private ArrayList<Point> pointsList;
    private Positions ballPosition;
    private Buttons pressedButton;
    private AudioController ALAudioPlayer;
    private BasicAwareness ALBasicAwareness;
    private AutonomousLife ALAutonomousLife;
    private static TrackerController ALTracker;
    private MotionController ALMotion;

// Connect with robot
    public void connect() throws Exception {
        String robotUrl = "tcp://" + ConfigureNao.HOSTNAME + ":" + ConfigureNao.PORT;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();

        // Using constructors to initalize objects
        ALTextToSpeech = new TextToSpeech(application.session());
        ALLeds = new EyeController(application.session());
        ALRobotPosture = new PostureController(application.session());
        ALMotion = new MotionController(application.session());
        ALRedBallDetection = new RedBallDetection(application.session());
        ALMemory = new Memory(application.session());
        ALTracker = new TrackerController(application.session());
		ALBehaviourManager = new BehaviourController(application.session());
		ALbackgroundMovement = new BackgroundMovement(application.session());
        ALAnimatedSpeech = new AnimatedSpeech(application.session());
        ALSystem = new Setup(application.session());
        pointsList = new ArrayList<>();
        ALAudioPlayer = new AudioController(application.session());
        ALBasicAwareness = new BasicAwareness(application.session());
        ALAutonomousLife = new AutonomousLife(application.session());
        Point point = new Point(X, Y);

        /**
         * This is a event that checks when a person enters the desired zone, the robot will wave and give a small
         * introduction on what the robot is programmed to do and what his functions are
         * @author Valentijn Bruggeman
         * Inspiration: Levi Notoporu
         */
        ALMemory.subscribeToEvent("EngagementZones/PersonEnteredZone2", (EventCallback<Integer>) id -> {
            if(humanDetection) {
                if (id > 0) {

                    System.out.println("print 1 wait for peeople");
                    animatedSpeech("^start (movements1/wave) Welkom! klik op het eerste knop op mijn hoofd om de intro te beginnen ^wait (movements1/wave)");
                    setAutonomousState("disabled");
                    humanDetection = false;

                }
            }
        });
    }

    /**
     * This method is made so you can change the NAO's name to the desired name
     * @param name
     * @throws CallError
     * @throws InterruptedException
     */
    public void naoRobotName(String name) throws CallError, InterruptedException {
        ALSystem.changeName(name);
    }

    /**
     * The next 3 methods are made so the NAO says something u want him to say and to change the robot language to the
     * desired language.
     * @author Valentijn Bruggeman
     */
    public void talking(String tekst) throws Exception {
        this.ALTextToSpeech.talk(tekst);
    }
    public void setLanguage(String language) throws CallError, InterruptedException {
        this.ALTextToSpeech.Language(language);
    }
    public void animatedSpeech(String text) throws CallError, InterruptedException {
        ALAnimatedSpeech.animatedText(text);
    }

    /**
     * The next 2 methods are made so you can control the eye colour of the NAO robot
     * @author Valentijn Bruggeman
     */
    public void eyeColour(String color, float duration) throws Exception {
        ALLeds.determineEyeColour(color, duration);
    }
    public void randomEyeControl(float duration) throws CallError, InterruptedException {
        ALLeds.randomEyes(duration);
    }

    /**
     * The next 2 methods are used to play sound effects and to adjust the volume of the NAO
     * @authors Ahmed El Ela and Valentijn Bruggeman
     */
    public void play(String filename) throws CallError, InterruptedException {
        ALAudioPlayer.playSFX(filename);
    }
    public void volume(int volume) throws Exception {
		ALAudioPlayer.setOutputVolume(volume);
    }

    /**
     * Changing the postue (stand, crouch & sit)of the NAO and determining which behaviour we want to import from Choreograph
     * @autors Ahmed El Ela and Valentijn Bruggeman
     */
    public void postureInput(String postureName, float maxSpeedFraction) throws Exception {
        ALRobotPosture.postureInput(postureName, maxSpeedFraction);
    }
	public void determineBehaviour(String behavior) throws CallError, InterruptedException{
        ALBehaviourManager.behaviour(behavior);
    }

    /**
     * Making the NAO look move in the background so it looks like he is alive, or that he looks alive by autonomouslife
     * @authors Ahmed El Ela and valentijn Bruggeman.
     */
	public void setBackgroundMovement(boolean enabled) throws CallError, InterruptedException {
        ALbackgroundMovement.moveInBackground(enabled);
	}
    public void setAutonomousState(String state) throws CallError, InterruptedException {
        ALAutonomousLife.autonomousState(state);
    }

    /**
     * Added 3 methods that use the ALBasicAwareness API. to put the NAO in an engagement mode and to make him detect
     * people
     * @author Ahmed El Ela
     */
    public void setEngagementMode (String modename) throws CallError, InterruptedException {
        ALBasicAwareness.engagementMode(modename);
    }
    public void setHeadTracker (String trackingmode) throws CallError, InterruptedException {
        ALBasicAwareness.headTracker(trackingmode);
    }
    public void setStimulusDetection(String stimtype, boolean enable) throws CallError, InterruptedException {
        ALBasicAwareness.stimulusMode(stimtype, enable);
    }

    /**
     * A method for human detection that uses the basicawareness methods and autonomouslife
     * @author Ahmed El Ela
     * @inspiredby Levi Notopuro
     * @adjustedby Valentijn Bruggeman
     */
    public void humanDetection() throws Exception {
        setAutonomousState("solitary");
        setStimulusDetection("Movement", false);
        setStimulusDetection("People", true);
        setEngagementMode("SemiEngaged");
        setHeadTracker("Head");
        humanDetection = true;
    }

    /**
     * Do while loop to control the events
     * @author Valentijn Bruggeman
     */
    public void doWhile(int millis, int time) throws Exception {
        int counter = 0;
        do {
            Thread.sleep(millis);
            counter++;
        } while (counter < time);
    }

    /**
     * Made a method that uses the ALRedBallDetection API to detect the redball being used in our simon says function
     * the code has a values set up for the NAO camera. The enums are used to identify the places we want the NAO to
     * detect and that gets stored a arraylist.
     * @author Valentijn Bruggeman
     */
    public void detectRedBall() throws Exception {
        System.out.println("Startg");
        ALRedBallDetection.subscribe();
        ALMemory.subscribeToEvent("redBallDetected", o -> {
            List<Object> data = (List<Object>) o;
            List<Float> BallInfo = (List<Float>) data.get(EEN);
            X = BallInfo.get(NULL);
            Y = BallInfo.get(EEN);

            this.X = X;
            this.Y = Y;

            System.out.println("X: " + X + " Y: " + Y);

            // Top left value
            if(X <= -0.13 && Y <= -0.10) {
                this.ballPosition = Positions.TOPLEFT;
                System.out.println("TOPLEFT");
            }
            // Top center value
            else if (X >= -0.12 && X <= 0.12 && Y <= -0.10) {
                this.ballPosition = Positions.TOPCENTER;
                System.out.println("TOPCENTER");
            }
            // Top right value
            else if (X >= 0.13 && X <= 2 && Y <= 0.10) {
                this.ballPosition = Positions.TOPRIGHT;
                System.out.println("TOPRIGHT");
            }
            // Middle value
            else if (Y >= -0.09f && Y <= 0.09f) {
                this.ballPosition = Positions.MIDDLE;
                System.out.println("MIDDLE");
            }
            // Bottom left value
            else if (X <= -0.13 && Y >= 0.1) {
                this.ballPosition = Positions.BOTTOMLEFT;
                System.out.println("BOTTOMLEFT");
            }
            // Bottom center value
            else if (X >= -0.12 && X <= 0.12 && Y >= 0.1) {
                this.ballPosition = Positions.BOTTOMCENTER;
                System.out.println("BOTTOMCENTER");
            }
            // Bottom right value
            else if (X >= 0.10 && X <= 2 && Y >= 0.1) {
                this.ballPosition = Positions.BOTTOMRIGHT;
                System.out.println("BOTTOMRIGHT");
            }

            pointsList.add(new Point(X, Y));

            if (pointsList.size() == TIEN) {
                try {
                    processPointsList();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * A method that identifies the arraylist
     * @author Valentijn Bruggeman
     */
    public void processPointsList() throws Exception {
        for (Point point : pointsList) {
            X = point.getX();
            Y = point.getY();
        }
        pointsList.clear();
    }

    /**
     *  The next 7 methods are made for the positions we identified earlier. When the NAO detects ur ball in the correct
     *  place he moves on to give a compliment if its not in the correct place he tells u how u can adjust the ball to
     *  the correct place. We also added SFX when he detects the correct and the incorrect place.
     * @authors Ahmed El Ela and Valentijn Bruggeman
     */
    public void topLeft() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen links boven");
        determineBehaviour("movement/ArmenLinksBoven");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.TOPLEFT) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar linksboven te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Goed zo");
        Thread.sleep(500);
    }
    public void topCenter() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen boven uw hoofd");
        determineBehaviour("movement/ArmenOmhoog");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.TOPCENTER) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer boven uw hoofd te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Lekker bezig");
        Thread.sleep(500);
    }
    public void topRight() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen rechtsboven");
        determineBehaviour("movement/ArmenRechtsBoven");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.TOPRIGHT) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar rechtsboven te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("U doet het prachtig");
        Thread.sleep(500);
    }
    public void middle() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen in het midden");
        determineBehaviour("movement/ArmenMidden");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.MIDDLE) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar het midden te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Wauw perfect");
        Thread.sleep(500);
    }
    public void bottomLeft() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen linksonder");
        determineBehaviour("movement/ArmenLinksOnder");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.BOTTOMLEFT) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar linksonder te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Goedgedaan");
        Thread.sleep(500);
    }
    public void bottomCenter() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen onder uw navel");
        determineBehaviour("movement/ArmenOmlaag");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.BOTTOMCENTER) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer onder uw navel te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("Fantastisch");
        Thread.sleep(500);
    }
    public void bottomRight() throws Exception {
        postureInput("StandInit", 0.3f);
        talking("Cijmon zegt armen rechtsonder");
        determineBehaviour("movement/ArmenRechtsOnder");
        Thread.sleep(4000);
        while(this.ballPosition != Positions.BOTTOMRIGHT) {
            play("/opt/aldebaran/var/www/apps/movement/mixkit-wrong-answer-fail-notification-946.wav");
            talking("Probeer uw armen iets meer naar rechtsonder te bewegen!");
            eyeColour("red", NULLF);
        }
        eyeColour("green", NULLF);
        play("/opt/aldebaran/var/www/apps/movement/y2mate.com-Correct-sound-effect.ogg");
        talking("U bent de beste!");
        Thread.sleep(500);
    }

    /**
     * This method is uses the moves we identified and throws them in a random generator. The move generator checks
     * the loop for the last used move so it doesnt repeat the move back to back. The moves are stored in an arraylist.
     * @authors Ahmed El Ela and Valentijn Bruggeman
     */
    public void simonSays() throws Exception {
        List<Positions> movesList = new ArrayList<>();
        Random random = new Random();

        // Generate amount of random moves
        for (int i = NULL; i < 6; i++) {
            Positions move;
            int lastMove = movesList.size() - EEN;

            // Generate a random move that is not the same as the previous move
            do {
                move = Positions.values()[random.nextInt(Positions.values().length)];
            } while (lastMove >= NULL && move == movesList.get(lastMove));

            movesList.add(move);

            // Switch case method for each move
            switch (move) {
                case TOPLEFT:
                    topLeft();
                    break;
                case TOPCENTER:
                    topCenter();
                    break;
                case TOPRIGHT:
                    topRight();
                    break;
                case MIDDLE:
                    middle();
                    break;
                case BOTTOMLEFT:
                    bottomLeft();
                    break;
                case BOTTOMCENTER:
                    bottomCenter();
                    break;
                case BOTTOMRIGHT:
                    bottomRight();
                    break;
            }
        }
        animatedSpeech(" ^start (animations/Stand/Gestures/BowShort_1) Bedankt voor het spelen, hopelijk heeft uw net zoveel plezier gehad bij het spelen als wij dat hebben gehad met het maken van dit spel!");
    }

    /**
     * Made 3 events for each button that the NAO has on his head. In the event there is an if loop that checks if the
     * button has had a threshold for 0.5 secs, if so the robot stands still and starts the desired function. Each
     * button has the same SFX. The "Front" button is a small intro, the "Middle" button is our simon says and the
     * "Rear" button is for our small concert.
     * @author Ahmed El Ela
     * @adjustedby Valentijn Bruggeman
     */
	public void touchButton(String sensorName) throws Exception {
        switch (sensorName) {
            case "Front":
                ALMemory.subscribeToEvent("FrontTactilTouched", o -> {
                    float touch = (float)o;
                    final float TOUCH_THRESHOLD = 0.5f;
                    if (touch >= TOUCH_THRESHOLD) {
                        setBackgroundMovement(false);
                        try {
                            play("/opt/aldebaran/var/www/apps/movement/bell.wav");
                            // Do something when front button is pressed
                            postureInput("StandInit", 0.5f);
                            animatedSpeech("^startTag(Hey_1) Hallo, ^wait(Hey_1) ^start(animations/Stand/Gestures/Explain_10) mijn naam is Cijmon. Ik ben gemaakt om jullie te helpen bewegen! Ik heb twee spel modes! 1 is gemaakt om mij na te doen en de andere is voor uw amusement! Klik de knop op het midden van mijn hoofd om mij na te doen of klik de achterste knop om vermaakt te worden!");
                            Thread.sleep(500);
                        } catch (Exception e) {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                    }
                });
                break;

            case "Middle":
                ALMemory.subscribeToEvent("MiddleTactilTouched", o -> {
                    float touch = (float)o;
                    final float TOUCH_THRESHOLD = 0.5f;
                    if (touch >= TOUCH_THRESHOLD) {
                        setBackgroundMovement(false);
                        try {
                            // Do something when middle button is pressed
                            play("/opt/aldebaran/var/www/apps/movement/bell.wav");
                            eyeColour("white", NULLF);
                            postureInput("StandInit", 0.5f);
                            animatedSpeech(" ^start(animations/Stand/Gestures/Enthusiastic_5) Dit spel heet Cijmon zegt! Het werkt als volgt: Doe mijn bewegingen zo goed mogelijk na en probeer zoveel mogelijk plezier te hebben bij het spelen!");
                            Thread.sleep(500);
                            //detectRedBall();
                            this.pressedButton = Buttons.MIDDLE;
                            simonSays();
                            eyeColour("white", NULLF);
                            ALRedBallDetection.unsubscribe();
                            postureInput("Crouch", 0.5f);
                        } catch (Exception e) {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                    }
                });
                break;

            case "Rear":
                ALMemory.subscribeToEvent("RearTactilTouched", o -> {
                    float touch = (float)o;
                    final float TOUCH_THRESHOLD = 0.5f;
                    if (touch >= TOUCH_THRESHOLD) {
                        setBackgroundMovement(false);
                        try {
                            play("/opt/aldebaran/var/www/apps/movement/bell.wav");
                            postureInput("StandInit", 0.5f);
                            animatedSpeech(" ^start(animations/Stand/BodyTalk/BodyTalk_10) Bent u klaar om te zien hoe de dans van kinderen voor kinderen eruit ziet? Geniet ervan!");
                            this.pressedButton = Buttons.REAR;
                            volume(30);
                            determineBehaviour("movement/Dance 1");
                            Thread.sleep(500);
                            animatedSpeech(" ^start (animations/Stand/Gestures/BowShort_1) Bedankt voor uw tijd, ik hoop dat jullie het een mooie voorstelling vonden! ");
                            postureInput("Crouch", 0.3f);
                            eyeColour("white", 1F);
                            volume(30);

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

    /**
     * made 2 threads so the function stays running. One thread is for the DetectRedBall and the other is for the
     * disco eyes during our small concert.
     * @author Valentijn Bruggeman
     */
    static class checkPoints implements Runnable {
        private NAO tyrone2;
        public checkPoints (NAO tyrone2) {
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
                if (this.tyrone2.pressedButton == Buttons.MIDDLE) {
                    System.out.println("detecting redball");
                    try {
                        this.tyrone2.detectRedBall();
                    } catch (Exception e) {
                        System.err.println("Error occurred while interacting with ALRedBallDetection: " + e.getMessage());
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                    this.tyrone2.pressedButton = null;
                }
            }
        }
    }
    static class randomEyes implements Runnable {
        private NAO tyrone3;
        public randomEyes (NAO tyrone3) {
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
                if (this.tyrone3.pressedButton == Buttons.REAR) {
                    System.out.println("thread random eyes");
                    try {
                        this.tyrone3.randomEyeControl(20f);
                    } catch (CallError e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        this.tyrone3.eyeColour("white", NULLF);
                        System.out.println("white");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    this.tyrone3.pressedButton = null;


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
