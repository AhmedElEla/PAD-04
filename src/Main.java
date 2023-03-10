import src.RodeOgen;

<<<<<<<< HEAD:src/NAOMain.java
public class NAOMain {
========
public class Main {
>>>>>>>> parent of 604c471 (Naam veranderd):src/Main.java

    public static void main(String[] args) throws Exception {
        RodeOgen nao = new RodeOgen();
        nao.verbind("nao.local", 9559);
        nao.praten("Die beweging is niet goed");
        nao.ogenAan();
        Thread.sleep(5000);
        nao.ogenUit();
    }


}
