package src.red_detection;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALRedBallDetection;

public class RedBallDetection {

    private ALRedBallDetection redBallDetection;
    public RedBallDetection(Session session) throws Exception{
        redBallDetection = new ALRedBallDetection(session);


    }

    public void subscribeToEvent(String redBallDetected, EventCallback ik_zie_een_rode_object) throws Exception {
        this.redBallDetection.subscribe("redBallDetected");

    }
}
