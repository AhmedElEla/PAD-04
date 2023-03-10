// Dit programma is gemaakt door Valentijn Bruggeman
// Het dient ervoor om de robot groene ogen te geven
// Klas: IT101

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALLeds;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class NAOOud {
    private String naam;
    private Application application;

    public void verbind (String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }

    public void zeg (String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);
    }

    //Groene ogen user story
    public void groeneOgen () throws Exception {
        ALLeds leds = new ALLeds(this.application.session());
        leds.fadeRGB("FaceLeds", "green", 0.1F); // float value om het te specificeren
        //leds.setIntensity("FaceLeds", (float)0.9);
        //leds.fadeRGB("FeetLeds", "green", 0.1F );
        //leds.fadeRGB("ChestLeds", "green", 0.1F);
    }

    public void ledsWit () throws Exception {
        ALLeds leds = new ALLeds(this.application.session());
        leds.on("FaceLeds");
        //leds.on("AllLeds");
    }

    // RoodHerkennen User Story
    public void roodHerkennen () throws Exception {

    }

}
