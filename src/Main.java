// Valentijn Bruggeman
// Klas IT101
// Dit programma is de main van de objecten van de NAO robot (hij roept de programmas aan)

public class Main {
    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        nao.verbind("nao.local", 9559);
        nao.zeg("Hallo Mats! Fijne vakantie gehad?");
        nao.groeneOgen();
        nao.zeg("Fijn om te horen! Ik krijg daar helemaal groene ogen van!!!");

    }
}
