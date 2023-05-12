package src.configuration;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALSystem;

public class Setup {
    private ALSystem systeem;
    public Setup(Session session) throws Exception {
        this.systeem = new ALSystem(session);
    }
    public void changeName(String name) throws CallError, InterruptedException {
        this.systeem.setRobotName(name);
    }
}
