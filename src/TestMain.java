package src;

import com.aldebaran.qi.helper.EventCallback;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();
        naoTyrone.praten("Verbonden");

        naoTyrone.setBackgroundmovement(true);

        EventCallback frontTouchEventCallback = o -> {
            float touch = (float)o;
            float touchThreshold = 0.5f;
            if (touch >= touchThreshold) {
                naoTyrone.setBackgroundmovement(false);
                //System.out.println("Voor knop gedrukt");
                try {
                    // Do something when front button is pressed
                    naoTyrone.postureInput("StandInit", 0.5f);
                    naoTyrone.animateSpeech("^startTag(Hey_1) Hallo, ^wait(Hey_1) ^start(animations/Stand/Gestures/Explain_10) mijn naam is Cijmon. Ik ben gemaakt om jullie te helpen bewegen! 1 van mijn spel modes is gemaakt zodat jullie mij na kunnen doen. Klik de knop op het midden van mijn hoofd om te beginnen!");
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        };

        EventCallback middleTouchEventCallback = o -> {
            float touch = (float)o;
            float touchThreshold = 0.5f;
            if (touch >= touchThreshold) {
                naoTyrone.setBackgroundmovement(false);
                //System.out.println("Midden knop gedrukt");
                try {
                    // Do something when middle button is pressed
                    naoTyrone.animateSpeech("Ik leg nu uit hoe het spel werkt. ^start(animations/Stand/Gestures/YouKnowWhat_1) Dit spel heet Cijmon zegt! Doe mijn bewegingen zo goed mogelijk na en probeer zoveel mogelijk plezier te hebben bij het spelen");
                    Thread.sleep(500);
                    new Thread(new Nao.checkPoints(naoTyrone)).start();
                    naoTyrone.simonSays();
                    naoTyrone.postureInput("Crouch", 0.5f);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        };

        EventCallback rearTouchEventCallback = o -> {
            float touch = (float)o;
            float touchThreshold = 0.5f;
            if (touch >= touchThreshold) {
                naoTyrone.setBackgroundmovement(false);
                //System.out.println("Achterste knop gedrukt");
                try {
                    naoTyrone.postureInput("StandInit", 0.5f);
                    naoTyrone.animateSpeech("  ^start(animations/Stand/Gestures/Enthusiastic_4) U heeft de laatste knop ingedrukt!! ^wait(animations/Stand/Gestures/Enthusiastic_4) ^start(animations/Stand/BodyTalk/BodyTalk_10) Hier komt de show en dans van kinderen voor kinderen! In de laatste sprint zien jullie het resultaat");
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        };

        naoTyrone.touchButton("Front", frontTouchEventCallback);
        naoTyrone.touchButton("Middle", middleTouchEventCallback);
        naoTyrone.touchButton("Rear", rearTouchEventCallback);

        naoTyrone.doWhile(1000, 10000);
    }
}
