package irrgarten1;
import java.util.ArrayList;
public class Player {
    //Atributo de clase.
    private static int MAX_WEAPONS = 2;
    private static int MAX_SHIELDS = 3;
    private static int INITIAL_HEALTH = 10;
    private static int HITS2LOSE = 3;
    //Atributos de instancia de la clase.
    private ArrayList<Shield> shields;
    private ArrayList<Weapon> weapons;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    
    //Constructor.
    public Player(char number, float intelligence, float strength){
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.name = "Player#" + number;
        this.consecutiveHits = 0;
        this.weapons = new ArrayList();
        this.shields = new ArrayList();
    }
    
    //Métodos.
    //Método que resucita al personaje.
    public void resurrect(){
        shields.clear();
        weapons.clear();
        resetHits();
        health = INITIAL_HEALTH;
    }
    
    //Getters
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public char getNumber(){
        return number;
    }
    
    //Método que establece la nueva casilla del personaje.
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    //Método que devuelve si está muerto o no el personaje.
    public boolean dead(){
        return health <= 0;
    }
    
    /*public Directions move (Directions direction, Directions[] validMoves){
        
    }*/
    
    
    
    //Método que calcula la suma de fuerza del jugador más la aportada por el arma.
    public float attack(){
        return sumWeapons() + strength;
    }
    
    //Método que devuelve si defiende o no el ataque que recibe el personaje.
    /*public boolean defend(float recivedAttack){
        return manageHit(recivedAttack);
    }*/
    
    /*public void receiveReward(){
        
    }*/
    
    //Método to_String;
    @Override
    public String toString(){
        String cadena = "Position [" + row + ", " + col + "]\nHealth " + health + "\n";
        for(int i = 0; i < shields.size(); i++)
            cadena += shields.get(i).toString() + "\n";
        for(int i = 0; i < weapons.size(); i++)
            cadena += weapons.get(i).toString() + "\n";
        return cadena;
    }
    
    //
    /*private void receiveWeapon(Weapon w){
        
    }
    
    private void receiveShield(Shield s){
        
    }*/

    //Método que crea una nueva instancia de un arma. Recibe los parametros de Dice.
    private Weapon newWeapon(){
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return w;
    }
    
    //Método que crea una nueva instancia de un escudo. Recibe los parametros de Dice.
    private Shield newShield(){
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft());
        return s;
    }

    //Método que calcula la suma de inteligencia con el aporte de los escudos.
    private float defensiveEnergy(){
        return sumShields() + intelligence;
    }
    
    /*private boolean manageHit(float receivedAttack){
        
    }*/

    //Método que pone el valor de impactos consecutivos a cero.
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    //Método que deccrementa en una unidad la salud del jugador.
    private void gotWounded(){
        health --;
    }
    
    //Método incrementa en una unidad el contador de impactos consecutivos.
    private void incConsecutiveHits(){
        consecutiveHits ++;
    }
    
    //Método que devuelve la suma del daño de todas las armas.
    private float sumWeapons(){
        float weapons_damage = (float)0.0;
        for(int i = 0; i < weapons.size(); i++)
            weapons_damage += weapons.get(i).attack();
        return weapons_damage;
    }
    
    //Método que devuelve la suma de la protección de todos los escudos.
    private float sumShields(){
        float shield_protection = (float) 0.0;
        for(int i = 0; i < shields.size(); i++)
            shield_protection += shields.get(i).protect();
        return shield_protection;
    }
}   

