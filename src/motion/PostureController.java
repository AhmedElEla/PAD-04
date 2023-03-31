package src.motion;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;

public class PostureController {
    private ALRobotPosture robotPosture;
    public PostureController(Session session) throws Exception {
        robotPosture = new ALRobotPosture(session);
    }
    public void postureInput(String postureName, float maxSpeedFraction) throws Exception {
        this.robotPosture.goToPosture(postureName, maxSpeedFraction);
    }
}
