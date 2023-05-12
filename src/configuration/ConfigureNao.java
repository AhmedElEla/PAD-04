
package src.configuration;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALSystem;

public class ConfigureNao {
    public static final String HOSTNAME = "nao.local";
    public static final int PORT = 9559;
    private ALSystem systeem;
    public ConfigureNao(Session session) throws Exception {
        this.systeem = new ALSystem(new Session());
    }
    public void changeName(String name) throws CallError, InterruptedException {
        this.systeem.setRobotName(name);
    }
}
