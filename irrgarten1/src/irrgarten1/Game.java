package irrgarten1;
public class Game {
    //Atributo de clase.
    private static final int MAX_ROUNDS = 10;
    //Atributos de instancia de la clase.
    private int currentPlayerIndex;
    private String log;
    //
    private Labyrith labyrinth;
    private Player currentPlayer;
    private ArrayList <Player> players;
    
    //Constructor
    public Game(int nplayers){
        currentPlayerIndex = Dice.whoStarts(nplayers);
        labyrinth = new Labyrinth();
        players = new ArrayList<>();
        currenPlayer = players.get(currentPlayerIndex);
    }
    
    //Métodos
    //Método que devuelve el ganador del laberinto.
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        
    }
    
    //Método que generauna instancia de GameState con toda la información del estado del juego.
    public GameState getGameState(){
        
    }
    
    //Método que configura el laberinto añadiendo obstaculos y monstruos.
    private void configureLabyrinth(){
        
    }
    
    private void nextPlayer(){
    
    }
    
    private Directions actualDirection(Directions preferredDirection){
    
    }
    
    private GameCharacter combat(Monster monster){
        
    }
    
    private void manageReward(GameCharacter winner){
        
    }
    
    private void manageResurrection(){
        
    }
    
    private void logPlayerWon(){
        
    }
    
    private void logMonsterWon(){
        
    }
    
    private void logResurrected(){
        
    }
    
    private void logPlayerSkipTurn(){
        
    }
    
    private void logPlayerNoOrders(){
        
    }
    
    private void logNoMonster(){
        
    }
    
    private void logRounds(){
        
    }
    
}
