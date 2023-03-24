package src.motion;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;

public class TrackerController {
    private ALTracker RedBallTracker;

    public TrackerController(Session session) throws Exception {
        RedBallTracker = new ALTracker(session);
    }
}
