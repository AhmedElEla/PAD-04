package src.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAutonomousLife;

public class AutonomousLife {

    private ALAutonomousLife autonomousLife;

    public AutonomousLife (Session session) throws Exception{
        autonomousLife = new ALAutonomousLife(session);
    }

    public void autonomousState (String state) throws CallError, InterruptedException {
        autonomousLife.setState(state);
    }
}
