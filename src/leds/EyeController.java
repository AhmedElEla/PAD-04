package src.leds;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALLeds;

public class EyeController {
    private ALLeds eyeLeds;
    public EyeController(Session session) throws Exception {
        eyeLeds = new ALLeds(session);
    }
    public void determineEyeColour(String color, float duration) throws Exception {
        eyeLeds.fadeRGB ("FaceLeds", color, duration);
    }
    public void determineRGBValue(String name, Integer rgb, Float duration) throws CallError, InterruptedException {
        eyeLeds.fadeRGB(name, rgb, duration);
    }
    public void randomEyes(float duration) throws CallError, InterruptedException {
        eyeLeds.randomEyes(duration);
    }
}
