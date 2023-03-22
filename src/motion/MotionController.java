package src.motion;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMotion;

public class MotionController {

    private ALMotion robotMotion;

    public MotionController(Session session) throws Exception {
        robotMotion = new ALMotion(session);
    }

    public void bepaalMotion(String names, double angleLists, float timeLists, boolean isAbsolute) throws Exception {
        this.robotMotion.angleInterpolation(names, angleLists, timeLists, isAbsolute);
    }
}
