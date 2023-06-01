package src.leds;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALLeds;

public class EyeController {
    // Acces modifier for AlLeds
    private ALLeds eyeLeds;
    // Constructor initializing ALLeds
    public EyeController(Session session) throws Exception {
        eyeLeds = new ALLeds(session);
    }
    // A  method that determines the eye color we want for the NAO
    public void determineEyeColour(String color, float duration) throws Exception {
        eyeLeds.fadeRGB ("FaceLeds", color, duration);
    }
    // A method that gives the NAO faded eyes.
    public void determineRGBValue(String name, Integer rgb, Float duration) throws CallError, InterruptedException {
        eyeLeds.fadeRGB(name, rgb, duration);
    }
    // A method that gives the NAO disco eyes
    public void randomEyes(float duration) throws CallError, InterruptedException {
        eyeLeds.randomEyes(duration);
    }
}
