package src.memory;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;

import java.util.List;
import java.util.Map;

public class Memory {
    private ALMemory memory;

    public Memory(Session session) throws Exception {
        memory = new ALMemory(session);
    }

    public void subscribeToEvent(String event, EventCallback callback) throws Exception {
        memory.subscribeToEvent(event, callback);
    }
    public Map<String, Object> getRedBallData() throws CallError, InterruptedException {
        List<Object> redBallDetected = (List<Object>) memory.getData("redBallDetected");

        Map<String, Object> BallInfo = (Map<String, Object>) redBallDetected.get(1);
        int x = (int) BallInfo.get("centerX");
        int y = (int) BallInfo.get("centerY");

        System.out.println("X: " + x);
        System.out.println("Y: " + y);

        return BallInfo;
    }
}
