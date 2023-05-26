package src.audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAudioPlayer;

public class AudioPlayer {
    private ALAudioPlayer audioPlayer;
    public AudioPlayer(Session session) throws Exception {
        this.audioPlayer = new ALAudioPlayer(session);
    }
    public void playSFX(String filename) throws CallError, InterruptedException {
        audioPlayer.setMasterVolume(0.5f);
        audioPlayer.playFile(filename);
    }
}
