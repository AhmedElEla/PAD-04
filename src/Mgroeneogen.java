package src;
// Valentijn Bruggeman
// Klas IT101
// Dit programma is de main van de objecten van de NAO robot (hij roept de programmas aan)
public class Mgroeneogen {

        public static void main(String[] args) throws Exception {
            Groeneogen nao = new Groeneogen();
            nao.verbind("nao.local", 9559);
            //nao.zeg("Hallo Mats! Fijne vakantie gehad?");
            nao.groeneOgen();
            //nao.zeg("Ik ben blij om te zien dat u de beweging goed na doet! Ik krijg daar zelfs helemaal groene ogen van!!!");
            Thread.sleep(5000);
            nao.ledsWit();
        }
    }
