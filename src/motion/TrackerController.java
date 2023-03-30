package src.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;

public class TrackerController {
    private ALTracker alTracker;

    public TrackerController(Session session) throws Exception {
        alTracker = new ALTracker(session);
    }

    public void startTracker(String pMode, Float pMaxDistance, String pTarget, Object pParams, String pEffector) throws CallError, InterruptedException {
        alTracker.setMode(pMode); // welke onderdelen van robot bewegen mee bv: Head, WholeBody, Move
        alTracker.setMaximumDistanceDetection(pMaxDistance); // bv 2f; max afstand om te tracken in meters
        alTracker.registerTarget(pTarget, pParams); // bv RedBall, 0.1; registreer het doelwit met parameters (RedBall, diameter van bal in meters) of update de parameters
        alTracker.track(pTarget); // bv RedBall; laat de doelwit getrackd worden en begin met tracken; doelwit moet eerst geregistreerd worden
        alTracker.setEffector(pEffector); // bv "Arms, "LArm", "RArm", "None"; bepaal "end-effector"; bepaal hoe de robot gaat tracken
    }

    public void stopTracker() throws CallError, InterruptedException {
        alTracker.stopTracker();
    }
}
