package src;

import src.movement.Armenbewegen;

public class Marmenbewegen {
    public static void main(String[] args) throws Exception {
        Armenbewegen nao = new Armenbewegen();
        nao.verbind("nao.local", 9559);
        nao.praten("Ik ben NAONAO");
        nao.staan();
        nao.rightShoulderUP();
        Thread.sleep(10);
        nao.leftShoulderUP();
        Thread.sleep(10);
        nao.staan();
        nao.leftShoulder();
        Thread.sleep(10);
        nao.rightShoulder();



        while(true){
            Thread.sleep(1000);
        }
    }
}
