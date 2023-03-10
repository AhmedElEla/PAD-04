package src;

public class TestProgrammaArmenBewegen {
    public static void main(String[] args) throws Exception {
        HoofdBewegen nao = new HoofdBewegen();
        nao.verbind("nao.local", 9559);
        nao.praten("Ik ben NAONAO");
        nao.staan();
        //nao.rightShoulderUP();

        nao.praten("Hello World");
        nao.staan();

        Thread.sleep(10);
        nao.hoofdLinks();
        Thread.sleep(10);

        nao.staan();
        //nao.leftShoulder();

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
