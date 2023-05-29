package src.audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAudioDevice;
import com.aldebaran.qi.helper.proxies.ALAudioPlayer;

public class AudioController {
    private ALAudioPlayer audioPlayer;
    private ALAudioDevice audioDevice;
    public AudioController(Session session) throws Exception {
        this.audioPlayer = new ALAudioPlayer(session);
        this.audioDevice = new ALAudioDevice(session);
    }
    public void playSFX(String filename) throws CallError, InterruptedException {
        audioPlayer.setMasterVolume(0.5f);
        audioPlayer.playFile(filename);
    }
    public void setOutputVolume(int volume) throws Exception {
        audioDevice.setOutputVolume(volume);
    }
}
