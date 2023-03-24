package src.memory;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMemory;

public class Memory {

    private ALMemory memory;

    public Memory(Session session) throws Exception {
        memory = new ALMemory(session);
    }
}
