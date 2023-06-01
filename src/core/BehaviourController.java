package src.core;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALBehaviorManager;

public class BehaviourController {
    // Created an acces modifier for ALBehaviourManager
    private ALBehaviorManager robotBehaviour;
    // Created a constructor that initializes ALBehaviourManager
    public BehaviourController(Session session) throws Exception {
        robotBehaviour = new ALBehaviorManager(session);
    }
    // Created a method that is designed so we can import behaviours from Choreographe
    public void behaviour(String behavior) throws CallError, InterruptedException {
        this.robotBehaviour.startBehavior(behavior);
    }
}