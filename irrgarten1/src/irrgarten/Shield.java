package irrgarten;
public class Shield {
    //Atributos de instancia.
    private float protection;
    private int uses;
    
    //Constructores.
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    //Métodos.
    //Si tiene usos devuelve el valor de la protección, si no devuelve 0.
    public float protect(){
        float protect = 0;
        if(uses > 0){
            uses --;
            protect = protection;
        }
        return protect;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    //To_String.
    public String toString(){
        return "S[" + protection + ", " + uses + "]";
    }
}
