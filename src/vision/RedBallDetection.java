package src.vision;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALRedBallDetection;

public class RedBallDetection {
    // Acces modifier for ALRedBallDetection
    private ALRedBallDetection redBallDetection;
    // Constructor that initializes ALRedBallDetection
    public RedBallDetection(Session session) throws Exception{
        redBallDetection = new ALRedBallDetection(session);
    }
    // Method that subscribes to the RedBallDetection
    public void subscribe() throws Exception {
        redBallDetection.subscribe("redBallDetected");
    }
    // Method that unsubscribes to the RedBallDetection
    public void unsubscribe() throws CallError, InterruptedException {
        redBallDetection.unsubscribe("redBallDetected");
    }
}
