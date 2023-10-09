package irrgarten1;
import java.util.ArrayList;
public class Game {
    //Atributo de clase.
    private static final int MAX_ROUNDS = 10;
    //Atributos de instancia de la clase.
    private int currentPlayerIndex;
    private String log;
    private Labyrinth labyrinth;
    private Player currentPlayer;
    private ArrayList <Player> players;
    private ArrayList <Monster> monsters;
    
    //Constructor
    public Game(int nplayers){
        currentPlayerIndex = Dice.whoStarts(nplayers);
        players = new ArrayList<>();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    //Métodos
    //Método que devuelve el ganador del laberinto.
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    /*public boolean nextStep(Directions preferredDirection){
        
    }*/
    
    //Método que generauna instancia de GameState con toda la información del estado del juego.
    public GameState getGameState(){
        String players_s = "";
        for(int i = 0; i < players.size(); i++)
            players_s += players.get(i).toString() + "\n";
        
        String monsters_s = "";
        for(int i = 0; i < monsters.size(); i++)
            monsters_s += monsters.get(i).toString() + "\n";
        
        
        GameState gamestate = new GameState(labyrinth.toString(), players_s, monsters_s, currentPlayerIndex, finished(), log);
        
        return gamestate;
    
    }
    
    //Método que configura el laberinto añadiendo obstaculos y monstruos.
    private void configureLabyrinth(){

    }
    
    private void nextPlayer(){
        if(currentPlayerIndex + 1 == players.size())
            currentPlayerIndex = 0;
        else
            currentPlayerIndex ++;
        
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    /*private Directions actualDirection(Directions preferredDirection){
    
    }*/
    
    /*private GameCharacter combat(Monster monster){
        
    }*/
    
    /*private void manageReward(GameCharacter winner){
        
    }*/
    
    /*private void manageResurrection(){
        
    }*/
    
    private void logPlayerWon(){
        log += "Player " + currentPlayerIndex + " has won\n";
    }
    
    private void logMonsterWon(){
        log += "Player " + currentPlayerIndex + " has lost the combat";
    }
    
    private void logResurrected(){
        log += "Player " + currentPlayerIndex + " has resurrected\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "Player " + currentPlayerIndex + " has skiped the turn because is dead\n";
    }
    
    private void logPlayerNoOrders(){
        log += "Player " + currentPlayerIndex + " hasn´t followed the human´s instructions\n";
    }
    
    private void logNoMonster(){
        log += "Player " + currentPlayerIndex + " has moved to an empty square or hasn´t moved";
    }
    
    private void logRounds(){
        log += "Player " + currentPlayerIndex + " has consumed all combat rounds";
    }
    
}
