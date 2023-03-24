package src.vision;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALRedBallDetection;

public class RedBallDetection {
    private ALRedBallDetection redBallDetection;
    public RedBallDetection(Session session) throws Exception{
        redBallDetection = new ALRedBallDetection(session);
    }
    public void subscribe() throws Exception {
        redBallDetection.subscribe("redBallDetected");
    }
}
