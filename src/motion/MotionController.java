// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src.motion;

import com.aldebaran.qi.CallError;
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
    public void shoulderPitchControl(double RPitchAngleLists, double LPitchAngleLists) throws Exception {
        bepaalMotion("RShoulderPitch", RPitchAngleLists, 2f, true);
        bepaalMotion("LShoulderPitch", LPitchAngleLists, 2f, true);
    }
    public void fingerControl() throws CallError, InterruptedException {
        this.robotMotion.openHand("RHand");
        this.robotMotion.openHand("LHand");
    }
    public void wristControl(double RWristAngleLists, double LWristAngleLists) throws Exception {
        bepaalMotion("RWristYaw", RWristAngleLists, 0.5f,true);
        bepaalMotion("LWristYaw", LWristAngleLists, 0.5f,true);
    }
    public void shoulderRollControl(double RRollAngleLists, double LRollAngleLists) throws Exception {
        bepaalMotion("RShoulderRoll", RRollAngleLists, 1f, true);
        bepaalMotion("LShoulderRoll", LRollAngleLists, 1f, true);
    }
}
