// Dit is een project gemaakt door: Steven van den Nieuwenhoff, Cas Sombroek, Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;

public class Main {
    public static void main(String[] args) throws Exception {
        Nao naoTyrone = new Nao();
        naoTyrone.verbind();

        // Define a single event callback for all buttons

        EventCallback frontTouchEventCallback = o -> {
            float touch = (float)o;
            float touchThreshold = 0.5f;
            if (touch >= touchThreshold) {
                System.out.println("Front button pressed");
                try {
                    // Do something when front button is pressed
                    naoTyrone.praten("voorste knop ingedrukt");
                    Thread.sleep(500);
                    naoTyrone.postureInput("StandInit", 0.5f);
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
                System.out.println("Middle button pressed");
                try {
                    // Do something when middle button is pressed
                    naoTyrone.praten("Middelste knop ingedrukt");
                    Thread.sleep(500);
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
                System.out.println("Middle button pressed");
                try {
                    // Do something when middle button is pressed
                    naoTyrone.praten("Achterste knop ingedrukt");
                    Thread.sleep(500);
                    naoTyrone.postureInput("StandInit", 0.5f);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        };

        // Subscribe to all three touch events
        naoTyrone.touchButton("Front", frontTouchEventCallback);
        naoTyrone.touchButton("Middle", middleTouchEventCallback);
        naoTyrone.touchButton("Rear", rearTouchEventCallback);


        while (true) {
            naoTyrone.setBackgroundmovement(true);
        }
    }
}
