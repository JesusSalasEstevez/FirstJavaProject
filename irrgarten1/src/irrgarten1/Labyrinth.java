package irrgarten1;
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
    private char[][] LabyrinthSquare;
    private Monster [][] monsters;
    private Player [][] players;
            
    //Constructor
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.players = new Player [nRows][nCols];
        this.monsters = new Monster [nRows][nCols];
        this.LabyrinthSquare = new char [nRows][nCols];
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitCol = exitCol;
        this.exitRow = exitRow;
        for(int i = 0; i < nRows; i++)
            for(int j = 0; j < nCols; j++)
                LabyrinthSquare[i][j] = EMPTY_CHAR;
        LabyrinthSquare[exitRow][exitCol] = EXIT_CHAR;
    }
    
    //Métodos
    public void spreadPlayers(Player[] players){
        for(int i = 0; i < players.length; i++){
            Player p = players[i];
            int pos[] = randomEmptyPos();
            putPlayer2D(-1, -1, pos[0], pos[1], p);
        }
    }
    
    //Método que devuelve si hay un jugador en la casilla de salida.
    public boolean haveAWinner(){
        return players[nRows][nCols] != null;
    }
    
    //Método toString
    @Override
    public String toString(){
        String string = "";
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                string += LabyrinthSquare[i][j];
            }
            string += "\n";
        }
        return string;
    }

    //Método que añade un monstruo a una casilla.
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col)){
            LabyrinthSquare[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
        }

    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int newPos[] = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[0], newPos[1], player);
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow;
        int incCol;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }
        int row = startRow;
        int col = startCol;
        while(posOK(row, col) && emptyPos(row, col) && length > 0){
            LabyrinthSquare[row][col] = BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    
    public Directions[] validMoves(int row, int col){
        Directions[] output = new Directions[4];
        if(canStepOn(row+1, col))
            output[0] = Directions.DOWN;
        if(canStepOn(row-1, col))
            output[1] = Directions.UP;
        if(canStepOn(row, col + 1))
            output[2] = Directions.LEFT;
        if(canStepOn(row, col - 1))
            output[3] = Directions.RIGHT;
        
        return output;
    }
    
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
            switch(direction){
                case UP:
                    row --;
                break;
                
                case RIGHT:
                    col ++;
                break;
                
                case LEFT:
                    col --;
                break;
                
                case DOWN:
                    row ++;
                break;
            }
        int [] pos = new int [2];
        pos[0] = row;
        pos[1] = col;
        return pos;
    }
    
    //
    private int[] randomEmptyPos(){
        int [] pos = new int [2];
        do{
            pos[0] = Dice.randomPos(nRows);
            pos[1] = Dice.randomPos(nCols);
        }while(!(LabyrinthSquare[pos[0]][pos[1]] == EMPTY_CHAR) && !(players[pos[0]][pos[1]] == null));
        return pos;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        if(canStepOn(row,col)){
            if(posOK(row,col)){
                Player p = players[oldRow][oldCol];
                if(p == player){
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            boolean monsterPos = monsterPos(row, col);
            if(monsterPos){
                LabyrinthSquare[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }else{
                char number = player.getNumber();
                LabyrinthSquare[row][col] = number;
            }
            players[row][col] = player;
            player.setPos(row, col);
        }
        return output;
    }
}
    
