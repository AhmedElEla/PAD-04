package src.audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAudioDevice;
import com.aldebaran.qi.helper.proxies.ALAudioPlayer;

public class AudioController {
    // Created an acces modifier for audioPlayer and for audioDevice
    private ALAudioPlayer audioPlayer;
    private ALAudioDevice audioDevice;

    // Created a constructor called AudioController that initializes two instance variables
    public AudioController(Session session) throws Exception {
        this.audioPlayer = new ALAudioPlayer(session);
        this.audioDevice = new ALAudioDevice(session);
    }

    // Created a method called playSFX for added sound effects and a method setOutputVolume so i can control how loud the nao is
    public void playSFX(String filename) throws CallError, InterruptedException {
        audioPlayer.setMasterVolume(0.5f);
        audioPlayer.playFile(filename);
    }
    public void setOutputVolume(int volume) throws Exception {
        audioDevice.setOutputVolume(volume);
    }
}
