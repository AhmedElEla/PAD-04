package src.threads;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import src.motion.TrackerController;

public class PrintXYZ implements Runnable{
    private Application application;
    private static TrackerController redBallTracker;

    public void modules ()  {
        try {
            redBallTracker = new TrackerController(application.session());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public float[] returnPosition(int index)  {
        try {
            return redBallTracker.getPosition(1);
        } catch (CallError e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run()  {
        do {
            returnPosition(1);
            float[] position = returnPosition(1);
            System.out.println("_______");
            for (float i : position) {
                System.out.println(i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
