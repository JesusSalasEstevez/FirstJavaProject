package irrgarten1;
public class Irrgarten1_1 {
    public static void main(String[] args) {
        Labyrinth l = new Labyrinth(10, 10, 3, 3);
        System.out.println(l.toString());
        Monster m = new Monster("vinicius", (float)0.1, (float)0.5);
        System.out.println(m);
    }
}
