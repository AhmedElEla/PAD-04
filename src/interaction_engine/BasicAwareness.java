package src.interaction_engine;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALBasicAwareness;

public class BasicAwareness {
    // Acces modifier for ALBasicAwareness
    private ALBasicAwareness basicAwareness;
    // Constructor that initializes ALBasicAwareness
    public BasicAwareness (Session session) throws Exception {
        basicAwareness = new ALBasicAwareness(session);
    }
    // Method for the engagement mode of the NAO using BasicAwareness
    public void engagementMode (String modename) throws CallError, InterruptedException {
        basicAwareness.setEngagementMode(modename);
    }
    // Method for the head tracker of the NAO using BasicAwareness
    public void headTracker (String trackingmode) throws CallError, InterruptedException {
        basicAwareness.setTrackingMode(trackingmode);
    }
    // Method for the stimulus mode of the NAO using BasicAwareness
    public void stimulusMode (String stimtype, boolean enable) throws CallError, InterruptedException {
        basicAwareness.setStimulusDetectionEnabled(stimtype, enable);
    }
}
