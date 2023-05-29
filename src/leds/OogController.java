package src.leds;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALLeds;

public class OogController {
    private ALLeds ogenLeds;
    public OogController(Session session) throws Exception {
        ogenLeds = new ALLeds(session);
    }
    public void bepaalOogKleur(String color, float duration) throws Exception {
        ogenLeds.fadeRGB ("FaceLeds", color, duration);
    }
    public void bepaalRGBWaarde(String name, Integer rgb, Float duration) throws CallError, InterruptedException {
        ogenLeds.fadeRGB(name, rgb, duration);
    }
    public void randomEyes(float duration) throws CallError, InterruptedException {
        ogenLeds.randomEyes(duration);
    }
}
