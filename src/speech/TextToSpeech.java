package src.speech;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class TextToSpeech {
    private ALTextToSpeech tts;
    public TextToSpeech(Session session) throws Exception {
        this.tts = new ALTextToSpeech(session);
    }
    public void talk(String tekst) throws Exception {
        this.tts.say(tekst);
    }
    public void Language(String language) throws CallError, InterruptedException {
        this.tts.setLanguage(language);
    }
}

