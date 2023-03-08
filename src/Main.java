package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Main {
    public static void main(String[] args) throws Exception {
        HoofdBewegen nao = new HoofdBewegen();
        nao.verbind("nao.local", 9559);
        nao.praten("Hello World");
        nao.staan();
        Thread.sleep(10);
        nao.hoofdLinks();
        Thread.sleep(10);
        nao.hoofdRechts();
        Thread.sleep(10);
        nao.hoofdMidden();
        Thread.sleep(10);
        nao.hoofdBoven();
        Thread.sleep(10);
        nao.hoofdBeneden();




        while(true){
            Thread.sleep(1000);
        }
    }
}
