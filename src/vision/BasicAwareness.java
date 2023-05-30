package src.vision;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALBasicAwareness;

public class BasicAwareness {

    private ALBasicAwareness basicAwareness;

    public BasicAwareness (Session session) throws Exception {
        basicAwareness = new ALBasicAwareness(session);
    }

    public void engagementMode (String modename) throws CallError, InterruptedException {
        basicAwareness.setEngagementMode(modename);
    }
    public void headTracker (String trackingmode) throws CallError, InterruptedException {
        basicAwareness.setTrackingMode(trackingmode);
    }

    public void stimulusMode (String stimtype, boolean enable) throws CallError, InterruptedException {
        basicAwareness.setStimulusDetectionEnabled(stimtype, enable);
    }
}
