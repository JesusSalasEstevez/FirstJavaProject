package irrgarten;
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
        monsters = new ArrayList<>();
        labyrinth = new Labyrinth(10, 10, 3, 3);
        log = "";
        configureLabyrinth();
    }
    
    //Métodos
    //Método que devuelve el ganador del laberinto.
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        boolean dead = currentPlayer.dead();
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            if(direction != preferredDirection)
                logPlayerNoOrders();
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            if(monster == null)
                logNoMonster();
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }else
            manageResurrection();
        boolean endGame = finished();
        if(!endGame)
            nextPlayer();
        return endGame;
    }
    
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
        Monster monster;
        Player player;
        for(int i = 0; i < 3; i++){
            monster = new Monster("M"+i, Dice.randomIntelligence(), Dice.randomStrength());
            player = new Player((char) (i+'0'), Dice.randomIntelligence(), Dice.randomStrength());
            monsters.add(monster);
            players.add(player);
            labyrinth.addBlock(Orientation.VERTICAL,Dice.randomPos(9), Dice.randomPos(9), Dice.randomPos(3));
            labyrinth.addMonster(Dice.randomPos(9), Dice.randomPos(9), monster);
        }
        labyrinth.spreadPlayers(players);
        currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private void nextPlayer(){
        if(currentPlayerIndex + 1 == players.size())
            currentPlayerIndex = 0;
        else
            currentPlayerIndex ++;
        
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        return currentPlayer.move(preferredDirection, labyrinth.validMoves(currentPlayer.getRow(), currentPlayer.getCol()));
    }
    
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        boolean lose =  monster.defend(currentPlayer.attack());
        while(!lose && rounds < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            lose = currentPlayer.defend(monster.attack());
            rounds ++;
            if(!lose){
                lose = monster.defend(currentPlayer.attack());
                winner = GameCharacter.PLAYER;
            }
        }
        logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }else
            logMonsterWon();
    }
    
    private void manageResurrection(){
       boolean resurrect = Dice.resurrectPlayer();
       if(resurrect){
           currentPlayer.resurrect();
           logResurrected();
       }else
           logPlayerSkipTurn();
    }
    
    private void logPlayerWon(){
        log += "Player " + currentPlayer.getNumber() + "has won";
    }
    
    private void logMonsterWon(){
        log += "Player " + currentPlayer.getNumber() + " has lost the combat\n";
    }
    
    private void logResurrected(){
        log += "Player " + currentPlayer.getNumber() + " has resurrected\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "Player " + currentPlayer.getNumber() + " has skiped the turn because is dead\n";
    }
    
    private void logPlayerNoOrders(){
        log += "Player " + currentPlayer.getNumber() + " hasn´t followed the human´s instructions\n";
    }
    
    private void logNoMonster(){
        log += "Player " + currentPlayer.getNumber() + " has moved to an empty square or hasn´t moved\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Player " + currentPlayer.getNumber() + " has reached " + rounds +"/" + max + " rounds\n"; 
    }
    
    //BORRAR
    public Player getPlayer(int i){
        return players.get(i);
    }
    
}
