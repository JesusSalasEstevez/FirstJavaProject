package irrgarten1;
public class Irrgarten1_1 {
    public static void main(String[] args) {
        Labyrinth l = new Labyrinth(10, 10, 3, 3);
        System.out.println(l.toString());
        Monster m = new Monster("vinicius", (float)0.1, (float)0.5);
        System.out.println(m);
        
        for(int i = 0; i < l.validMoves(9, 9).length; i++)
            if(l.validMoves(9, 9)[i] != null)
                System.out.println(l.validMoves(9,9)[i]);
    }
}
