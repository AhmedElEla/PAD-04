package src.leds;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALLeds;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Rodeogen {
    private String naam;
    private Application application;

    public void verbind(String hostname, int port){
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();

    }

    public void ogenAan() throws Exception {
        ALLeds ogenLeds = new ALLeds(this.application.session());
        ogenLeds.fadeRGB("FaceLeds", "red", 0.1f);

    }

    public void ogenUit() throws Exception {
        ALLeds ogenLeds = new ALLeds(this.application.session());
        ogenLeds.on("FaceLeds");

    }

    public void praten(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);

    }


}





