// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMotion;

public class MotionController {

    // Acces modifier for ALMotion
    private ALMotion robotMotion;
    // Constructor that initializes ALMotion
    public MotionController(Session session) throws Exception {
        robotMotion = new ALMotion(session);
    }
    // Method that determines the motion we want the NAO to make
    public void determineMotion(String names, double angleLists, float timeLists, boolean isAbsolute) throws Exception {
        this.robotMotion.angleInterpolation(names, angleLists, timeLists, isAbsolute);
    }
    // A method for the shoulderpitch motion
    public void shoulderPitchControl(double RPitchAngleLists, double LPitchAngleLists) throws Exception {
        determineMotion("RShoulderPitch", RPitchAngleLists, 2f, true);
        determineMotion("LShoulderPitch", LPitchAngleLists, 2f, true);
    }
    // A method for the finger motion
    public void fingerControl() throws CallError, InterruptedException {
        this.robotMotion.openHand("RHand");
        this.robotMotion.openHand("LHand");
    }
    // A method for the wirst motion
    public void wristControl(double RWristAngleLists, double LWristAngleLists) throws Exception {
        determineMotion("RWristYaw", RWristAngleLists, 0.5f,true);
        determineMotion("LWristYaw", LWristAngleLists, 0.5f,true);
    }
    // A method for the shoulderroll motion
    public void shoulderRollControl(double RRollAngleLists, double LRollAngleLists) throws Exception {
        determineMotion("RShoulderRoll", RRollAngleLists, 1f, true);
        determineMotion("LShoulderRoll", LRollAngleLists, 1f, true);
    }
}
