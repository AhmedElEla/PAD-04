package src.speech;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;

public class AnimatedSpeech {
    // Acces modifier for ALAnimatedSpeech
    private ALAnimatedSpeech animatedSpeech;
    // Constructor that initializes ALAnimatedSpeech
    public AnimatedSpeech(Session session) throws Exception {
        this.animatedSpeech = new ALAnimatedSpeech(session);
    }
    // Method that makes the NAO moves while he talks
    public void animatedText(String text) throws CallError, InterruptedException {
        this.animatedSpeech.say(text);
    }
}
