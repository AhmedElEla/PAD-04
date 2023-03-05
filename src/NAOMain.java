import src.RodeOgen;

public class NAOMain {

    public static void main(String[] args) throws Exception {
        RodeOgen nao = new RodeOgen();
        nao.verbind("dynamo.local", 50249);
        //nao.praten("Hoi Steven");
        //nao.ogenAan();
        nao.ogenUit();
    }


}
