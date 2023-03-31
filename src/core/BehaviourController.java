package src.core;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALBehaviorManager;

public class BehaviourController {
    private ALBehaviorManager robotBehaviour;
    public BehaviourController(Session session) throws Exception {
        robotBehaviour = new ALBehaviorManager(session);
    }
    public void bepaalBehaviour(String behavior) throws CallError, InterruptedException {
        this.robotBehaviour.startBehavior(behavior);
    }
}