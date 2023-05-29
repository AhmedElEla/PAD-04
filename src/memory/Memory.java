package src.memory;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;

public class Memory {
    private ALMemory memory;
    public Memory(Session session) throws Exception {
        memory = new ALMemory(session);
    }
    public void subscribeToEvent(String event, EventCallback callback) throws Exception {
        memory.subscribeToEvent(event, callback);
    }
    public void unsubscribeAllEvent() throws Exception {
        memory.unsubscribeAllEvents();
    }
}
