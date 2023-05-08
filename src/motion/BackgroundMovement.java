package src.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALBackgroundMovement;

public class BackgroundMovement {
    private ALBackgroundMovement backgroundMovement;
    public BackgroundMovement(Session session) throws Exception {
        backgroundMovement = new ALBackgroundMovement(session);
    }
    public void moveInBackground(boolean enabled) throws CallError, InterruptedException {
        backgroundMovement.setEnabled(enabled);
    }
}
