package src.interaction_engine;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAutonomousLife;

public class AutonomousLife {
    // Acces modifier ALAutonomousLife
    private ALAutonomousLife autonomousLife;
    // Constructor that initializes ALAutonomousLife
    public AutonomousLife (Session session) throws Exception{
        autonomousLife = new ALAutonomousLife(session);
    }
    // Method that sets the state of autonomouslife to the desired state
    public void autonomousState (String state) throws CallError, InterruptedException {
        autonomousLife.setState(state);
    }
    // Method that stops the autonomouslife
    public void stopAll() throws CallError, InterruptedException {
        autonomousLife.stopAll();
    }
}
