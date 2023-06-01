// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;

import java.util.List;

public class TrackerController {
    // Acces modifier ALTracker
    private ALTracker alTracker;
    // Constructor that initializes ALTracker
    public TrackerController(Session session) throws Exception {
        alTracker = new ALTracker(session);
    }
    // Method that starts the tracker
    public void startTracker(String pMode, Float pMaxDistance, String pTarget, Object pParams, String pEffector) throws CallError, InterruptedException {
        alTracker.setMode(pMode); // welke onderdelen van robot bewegen mee bv: Head, WholeBody, Move
        alTracker.setMaximumDistanceDetection(pMaxDistance); // bv 2f; max afstand om te tracken in meters
        alTracker.registerTarget(pTarget, pParams); // bv RedBall, 0.1; registreer het doelwit met parameters (RedBall, diameter van bal in meters) of update de parameters
        alTracker.track(pTarget); // bv RedBall; laat de doelwit getrackd worden en begin met tracken; doelwit moet eerst geregistreerd worden
        alTracker.setEffector(pEffector); // bv "Arms, "LArm", "RArm", "None"; bepaal "end-effector"; bepaal hoe de robot gaat tracken
    }
    // Method that stopts the tracker
    public void stopTracker() throws CallError, InterruptedException {
        alTracker.stopTracker();
    }
    // Array list that stores the position given by the tracker
    public float[] getPosition(int index) throws CallError, InterruptedException {
        List<Float> getPosition = alTracker.getTargetPosition(1);

        float x = getPosition.get(0);
        float y = getPosition.get(1);
        float z = getPosition.get(2);

        float[] positions = {x, y, z};

        return positions;
    }
}
