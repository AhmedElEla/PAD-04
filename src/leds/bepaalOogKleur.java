package src.leds;

import com.aldebaran.qi.helper.proxies.ALLeds;

public class bepaalOogKleur {
    private ALLeds ogenLeds;

    public void bepaalOogKleur(String color, float duration) throws Exception {
        this.ogenLeds.fadeRGB ("FaceLeds", color, duration);
    }

}
