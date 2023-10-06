package irrgarten1;
public class Irrgarten1_1 {
    public static void main(String[] args) {
        Shield s = new Shield((float)3.0, 2);
        Weapon w = new Weapon((float)2.0, 2);
        System.out.println(s.toString());
        System.out.println(w.toString());
        Player p = new Player ();
        System.out.println(p.toString() + "\n" + w.discard());
    }
    
}
