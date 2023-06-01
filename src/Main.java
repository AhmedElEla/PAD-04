// Dit is een project gemaakt door: Ahmed El Ela en Valentijn Bruggeman
// Dit project heeft als eind doel om een NAO-robot te laten bewegen, dansen en muziek af te laten spelen. Daarnaast controleert de NAO of zijn bewegingen correct nagedaan worden
// Project groep naam: PAD-04
// Klas: IT101

package src;

public class Main {
    public static void main(String[] args) throws Exception {
        NAO naoTyrone = new NAO();
        naoTyrone.connect();
        naoTyrone.talking("Verbonden");
        naoTyrone.postureInput("Standinit", 1f);

        naoTyrone.detectRedBall();

        naoTyrone.setBackgroundMovement(true);

        naoTyrone.touchButton("Front");
        naoTyrone.touchButton("Middle");
        naoTyrone.touchButton("Rear");

        naoTyrone.doWhile(1000, 100);
    }
}
