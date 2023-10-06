package irrgarten1;
public class Monster {
    //Atributo de clase.
    private static final int INITIAL_HEALTH = 5;
    //Atributos de instancia de la clase.
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    //Constructor.
    public Monster(String name, float intellige, float strenth){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
    }
    
    //Métodos.
    //Método que devuelve si el monstruo esta vivo o muerto.
    public boolean dead(){
        return health <= 0;
    }
    
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
        
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public String toString(){
        
    }

    private void gotWounded(){
        health -= 1;
    }

}
