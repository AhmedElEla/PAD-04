package src.leds;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAutonomousBlinking;

public class AutonomousBlinking {
    private ALAutonomousBlinking autonomousBlinking;

    public AutonomousBlinking(Session session) throws Exception {
        autonomousBlinking = new ALAutonomousBlinking(session);
    }
    public void blinkAutonomously(boolean enabled) throws CallError, InterruptedException {
        autonomousBlinking.setEnabled(enabled);
    }
}

