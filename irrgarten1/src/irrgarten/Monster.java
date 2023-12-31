package irrgarten;
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
    public Monster(String name, float intelligence, float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = (float)10;
    }
    
    //Métodos.
    //Método que devuelve si el monstruo esta vivo o muerto.
    public boolean dead(){
        return (health <= 0);
    }
    
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
       boolean isDead = dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(intelligence);
            if(defensiveEnergy < receivedAttack){
               gotWounded();
               isDead = dead();
           }
       } 
       return isDead;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString(){
        return "M[name: " + name + ", intelligence:" + intelligence + ", strength: " + strength + "]"; 
    }

    private void gotWounded(){
        health --;
    }
    
    //Borrar
    public void getM(){
        System.out.println(health);
    }
}
