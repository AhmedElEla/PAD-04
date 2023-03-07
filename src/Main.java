public class Main {
    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        nao.verbind("nao.local", 9559);
        nao.zeg("Hallo Mats! Fijne vakantie gehad?");
        nao.groeneOgen();

    }
}
