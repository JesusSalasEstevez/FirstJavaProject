package irrgarten1;


public class Weapon {
    //Atributos de instancia.
    private float power;
    private int uses;
    
    //Constructor.
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
    //Métodos.
    //Si tiene usos devuelve el valor del ataque, si no devuelve 0;
    public float attack (){
        float attack = 0;
        if(uses > 0){
            uses --;
            attack = power;
        }
        return attack;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    //To_String
    public String toString(){
        return "W[" + power + ", " + uses + "]"; 
    }
}
