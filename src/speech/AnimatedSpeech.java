package src.speech;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;

public class AnimatedSpeech {
    private ALAnimatedSpeech animatedSpeech;
    public AnimatedSpeech(Session session) throws Exception {
        this.animatedSpeech = new ALAnimatedSpeech(session);
    }
    public void animatedText(String text) throws CallError, InterruptedException {
        this.animatedSpeech.say(text);
    }
}
