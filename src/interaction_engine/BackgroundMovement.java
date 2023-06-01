package src.interaction_engine;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALBackgroundMovement;

public class BackgroundMovement {
    // Acces modifier ALBackgroundMovement
    private ALBackgroundMovement backgroundMovement;
    // Constructor that initializes ALBackgroundMovement
    public BackgroundMovement(Session session) throws Exception {
        backgroundMovement = new ALBackgroundMovement(session);
    }
    // Method that makes the NAO look like he is alive by making him move in the background
    public void moveInBackground(boolean enabled) throws CallError, InterruptedException {
        backgroundMovement.setEnabled(enabled);
    }
}
