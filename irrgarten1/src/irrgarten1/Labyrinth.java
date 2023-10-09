package irrgarten1;
import java.util.ArrayList;
public class Labyrinth {
    //Atributos de instancia de la clase.
    private final char BLOCK_CHAR = 'X';
    private final char EMPTY_CHAR = '-';
    private final char MONSTER_CHAR = 'M';
    private final char EXIT_CHAR = 'E';
    private final char COMBAT_CHAR = 'C';
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    private char[][] LabyrinthSquare = new char [nRows][nCols];
    private Monster [][] MonsterSquare = new Monster [nRows][nCols];
    private Player [][] PlayerSquare = new Player [nRows][nCols];
            
    //Constructor
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitCol = exitCol;
        this.exitRow = exitRow;
        for(int i = 0; i < nRows; i++)
            for(int j = 0; j < nCols; j++)
                LabyrinthSquare[i][j] = EMPTY_CHAR;re
    }
    
    //Métodos
    /*public void spreadPlayers(Player players){
        
    }*/
    
    //Método que devuelve si hay un jugador en la casilla de salida.
    public boolean haveAWinner(){
        
    }
    
    //Método toString
    public String toString(){
        String string = "";
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                string += LabyrinthSquare[nRows][nCols];
            }
            string += "\n";
        }
        return string;
    }

    //Método que añade un monstruo a una casilla.
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col)){
            LabyrinthSquare[row][col] = MONSTER_CHAR;
            MonsterSquare[row][col] = monster;
        }

    }
    
    /*public Monster putPlayer(Directions direction, Player player){
        
    }*/
    
    /*public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        if(orientation == Orientation.VERTICAL)
        	for(int i = 0; i < length; i++)
        		LabyrinthSquare[startRow + i][startCol] = BLOCK_CHAR;
		else
        	for(int i = 0; i < length; i++)
        		LabyrinthSquare[startRow][startCol + i] = BLOCK_CHAR;
    }*/
    
    /*public ArrayList<Directions> validMoves(int row, int col){
        
        if(posOK(row - 1, col))
        	
        else if(posOK(row + 1, col))
        else if(posOK(row, col - 1))
        else if(posOK(row, col + 1))
    }*/
    
    //Método que devuelve si la posición está dentro del tablero.
    private boolean posOK(int row, int col){
        return 0 <= row && row < nRows && 0 <= col && col < nCols;
    }
    
    //Método que devuelve true si la posición está vacia.
    private boolean emptyPos(int row, int col){
		return posOK(row, col) && LabyrinthSquare[row][col] == EMPTY_CHAR;
    }
    
    //Método que devuelve true si la posicion contiene solo un monstruo.
    private boolean monsterPos(int row, int col){
		return LabyrinthSquare[row][col] == MONSTER_CHAR && posOK(row, col);
    }
    
    //Método que devuelve true si es la casilla de salida.
    private boolean exitPos(int row, int col){
    	return LabyrinthSquare[row][col] == EXIT_CHAR && posOK(row, col);
    }
    
    //Método que devuelve true si la casilla contiene un monstruo y un jugador.
    private boolean combatPos(int row, int col){
		return LabyrinthSquare [row][col] == COMBAT_CHAR && posOK(row, col);
    }
    
    //Método que indica si el jugador puede quedarse en la casilla.
    private boolean canStepOn(int row, int col){
        boolean can_step = false;
        if(posOK(row,col) && (monsterPos(row, col) || emptyPos(row, col) || exitPos(row, col)))
            can_step = true;
        return can_step;
    }       
    
    
    //Método que modifica el estado de la casilla cuando un jugador la abandona.
    private void updateOldPos(int row, int col){
        if(posOK(row, col))
            if(combatPos(row,col))
                LabyrinthSquare[row][col] = MONSTER_CHAR;
            else
                LabyrinthSquare[row][col] = EMPTY_CHAR;
    }
    
    //
    private int[] dir2Pos(int row, int col, Directions direction){
        if(posOK(row, col))
            if(direction == Directions.LEFT)
                col --;
            else
                if(direction == Directions.RIGHT)
                    col ++;
                else
                    if(direction == Directions.UP)
                        row --;
                    else
                        row ++;
        int [] pos = new int [2];
        pos[0] = row;
        pos[1] = col;
        return pos;
    }
    
    //
    private int[] randomEmptyPos(){
        int [] pos = new int [2];
        pos[0] = Dice.randomPos(nRows);
        pos[1] = Dice.randomPos(nCols);
        return pos;
    }
    
    /*private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        
    }*/
}
    
