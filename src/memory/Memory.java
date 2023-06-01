package src.memory;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;

public class Memory {
    // Acces modifier for ALMemory
    private ALMemory memory;
    // Constructor that initializes ALMemory
    public Memory(Session session) throws Exception {
        memory = new ALMemory(session);
    }
    // Method that subscribes to an event
    public void subscribeToEvent(String event, EventCallback callback) throws Exception {
        memory.subscribeToEvent(event, callback);
    }
}
