package src.leds;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALLeds;

public class OogController {
    private ALLeds ogenLeds;
    public OogController(Session session) throws Exception {
        ogenLeds = new ALLeds(session);
    }
    public void bepaalOogKleur(String color, float duration) throws Exception {
        this.ogenLeds.fadeRGB ("FaceLeds", color, duration);
    }
}
