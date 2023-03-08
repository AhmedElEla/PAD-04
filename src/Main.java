package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Main {
    public static void main(String[] args) throws Exception {
        NAOArmenBewegen nao = new NAOArmenBewegen();
        nao.verbind("nao.local", 9559);
        nao.praten("Ik ben cute");
        nao.rightShoulderUP();
        Thread.sleep(10);
        nao.leftShoulderUP();
        Thread.sleep(10);
        nao.leftShoulder();
        Thread.sleep(10);
        nao.rightShoulder();



        while(true){
            Thread.sleep(1000);
        }
    }
}
