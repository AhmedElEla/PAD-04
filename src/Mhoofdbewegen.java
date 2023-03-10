package src;

public class Mhoofdbewegen {
    public static void main(String[] args) throws Exception {
        Hoofdbewegen nao = new Hoofdbewegen();
        nao.verbind("nao.local", 9559);
        nao.praten("Ik ben NAONAO");
        nao.staan();
        nao.hoofdBoven();
        Thread.sleep(10);
        nao.hoofdLinks();
        Thread.sleep(10);
        nao.hoofdRechts();
        Thread.sleep(10);
        nao.hoofdBeneden();

    }



}
