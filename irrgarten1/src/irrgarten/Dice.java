package irrgarten;

import java.util.Random;
public class Dice {
    //Atributos de clase.
    private final static int MAX_USES = 5;
    private final static float MAX_INTELLIGENCE = (float)10.0;
    private final static float MAX_STRENGTH = (float) 10.0;
    private final static float RESURRECT_PROB = (float) 0.3;
    private final static int WEAPONS_REWARD = 2;
    private final static int SHIELDS_REWARD = 3;
    private final static int HEALTH_REWARD = 5;
    private final static int MAX_ATTACK = 3;
    private final static int MAX_SHIELD = 2;
    private static Random generator = new Random();
    
    //Métodos.
    //Devuelve un número aleatorio entre el 0 y el max;
    public static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    public static float randomIntelligence(){
        return (float) generator.nextDouble() * MAX_INTELLIGENCE;
    }
    
    public static float randomStrength(){
        return (float) generator.nextDouble() * MAX_STRENGTH;
    }
    
    public static boolean resurrectPlayer(){
        boolean resurrect = false;
        if(generator.nextFloat() < 0.3)
            resurrect = true;
        return resurrect;
    }
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }
    
    public static float weaponPower(){
        return (float) generator.nextDouble() * MAX_ATTACK;
    }
    
    public static float shieldPower(){
        return (float) generator.nextDouble() * MAX_SHIELD;
    }
    
    public static int usesLeft(){
        return generator.nextInt(MAX_USES);
    }
    
    public static float intensity(float competence){
        return (float) generator.nextDouble() * competence;
    }
    
    public static boolean discardElement(int usesLeft){
        return generator.nextInt(MAX_USES) + 1 > usesLeft;
    }
}
