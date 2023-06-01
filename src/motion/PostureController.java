package src.motion;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;

public class PostureController {
    // Acces modifier for ALRobotPosture
    private ALRobotPosture robotPosture;
    // Constructor that initializes ALRobotPosture
    public PostureController(Session session) throws Exception {
        robotPosture = new ALRobotPosture(session);
    }
    // Method that makes the NAO go to the posture we desire
    public void postureInput(String postureName, float maxSpeedFraction) throws Exception {
        this.robotPosture.goToPosture(postureName, maxSpeedFraction);
    }
}
