package src.configuration;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALSystem;

public class Setup {
    // Created an acces modifier for ALSystem
    private ALSystem system;

    // Created a constructor called setup to that initializes the ALSystem
    public Setup(Session session) throws Exception {
        this.system = new ALSystem(session);
    }
    // Created this method so we can change the name of the NAO to our desired name
    public void changeName(String name) throws CallError, InterruptedException {
        this.system.setRobotName(name);
    }
}
