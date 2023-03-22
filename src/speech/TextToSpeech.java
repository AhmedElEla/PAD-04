package src.speech;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class TextToSpeech {
    private ALTextToSpeech tts;

    public TextToSpeech(Session session) throws Exception {
        this.tts = new ALTextToSpeech(session);
    }
    public void praten(String tekst) throws Exception {
        this.tts.say(tekst);
    }
}

