package src.speech;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class TextToSpeech {
    // Acces modifier for ALTextToSpeech
    private ALTextToSpeech tts;
    // Constructor that initializes ALTextToSpeech
    public TextToSpeech(Session session) throws Exception {
        this.tts = new ALTextToSpeech(session);
    }
    // Method that makes the NAO say what we desire
    public void talk(String tekst) throws Exception {
        this.tts.say(tekst);
    }
    // Method that changes the talking language of the NAO
    public void Language(String language) throws CallError, InterruptedException {
        this.tts.setLanguage(language);
    }
}

