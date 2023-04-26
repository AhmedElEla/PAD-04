package src.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;

import java.util.List;

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
    public static float[] getPosition(ALTracker alTracker, int index) throws CallError, InterruptedException {
        List<Float> getPosition = alTracker.getTargetPosition(index);
        float x = getPosition.get(0);
        float y = getPosition.get(1);
        float z = getPosition.get(2);
        return new float[]{x, y, z};
    }
}
