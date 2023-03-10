package src;

public class TestProgrammaArmenBeweeg {
    public static void main(String[] args) throws Exception {
        NAOArmenBeweeg nao = new NAOArmenBeweeg();
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
