package irrgarten;

import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;

public class Irrgarten1_1 {
    public static void main(String[] args) {
        //Probar Game y creación de personajes
        /*Labyrinth l = new Labyrinth(10, 10, 3, 3);
        System.out.println(l.toString());
        Monster m = new Monster("vinicius", (float)0.1, (float)0.5);
        System.out.println(m);
        
        for(int i = 0; i < l.validMoves(9, 9).size(); i++)
            if(l.validMoves(9,9).get(i) != null)
                System.out.println(l.validMoves(9,9).get(i));
        
        Game game1 = new Game(3);
        System.out.println(game1.getGameState().getLabyrinth());
        System.out.println(game1.getGameState().getPlayers());
        System.out.println(game1.getGameState().getMonsters());
        System.out.println(game1.getGameState().getCurrentPlayer());
        System.out.println(game1.getGameState().getWinner());
        System.out.println(game1.getGameState().getLog());
        System.out.println(game1.getGameState().getLabyrinth());
        
        for(int i = 0; i < 3; i++){
            game1.getPlayer(i).get();
        }
        */
        
        //Moverse
        /*Game game1 = new Game(3);
        TextUI view = new TextUI();
        Controller c = new Controller(game1, view);
        c.play();*/
        
        
        //Probar cosas del player
        Player p = new Player('4', Dice.randomIntelligence(), Dice.randomStrength());
        p.estado();
        float attack;
        attack = Dice.randomStrength();
        System.out.println(attack);
        int i = 0;
        while(!p.dead() && i < 11){
            System.out.println(p.defend(attack));
            p.getVida();
        }
        p.resurrect();
        p.estado();
        System.out.println("Daño de atque" + p.attack());
        p.receiveReward();
        p.get();
        Monster m = new Monster("pedro", Dice.randomIntelligence(), Dice.randomStrength());
        m.defend(p.attack());
        m.getM();
        
        /*Monster m = new Monster("pedro", Dice.randomIntelligence(), Dice.randomStrength());
        System.out.println(m.attack());
        m.getM();
        float attack = Dice.randomStrength();
        m.toString();
        System.out.println(attack);
        System.out.println(m.dead());
        while(!m.dead()){
            m.defend(attack);
            m.getM();
        }*/
    }
}
