package knightgear.equipment;

import knightgear.extras.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipment{

    private static Logger logger = Logger.getLogger("Equipment");
    private Weights weight;
    private Materials material;
    private float valueModifier;
    private float weightModifier;
    protected String name;

    protected Equipment(){}

    public Equipment(String materialName, String typeName){
        setMaterial(materialName);
        valueModifier = Materials.getModifier(this.material);
        setWeight(typeName);
        weightModifier = Weights.getModifier(this.weight);
        if (name == null) {
            generateName();
        }
    }

    private void setMaterial(String materialName){
        try {
            for (int i = 0; i < Materials.values().length; i++){
                if (material == null && materialName.equalsIgnoreCase(Materials.values()[i].toString())) {
                    material = Materials.values()[i];
                } else{
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Not a proper material, material set to bronze.");
            logger.log(Level.WARNING, "Not a proper material", e);
            material = Materials.BRONZE;
        }
    }

    private void setWeight(String weightName){
        try {
            for (int i = 0; i < Weights.values().length; i++){
                if (weight == null && weightName.equalsIgnoreCase(Weights.values()[i].toString())) {
                    weight = Weights.values()[i];
                } else{
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Not a proper weight, weight set to light.");
            logger.log(Level.WARNING, "Not a proper weight", e);
            weight = Weights.LIGHT;
        }
    }

    protected void changeValueModifier(float newModifier){
        valueModifier *= newModifier;
    }

    protected void generateName(){
        name = getWeight().toString().toLowerCase() + " " + getMaterial().toString().toLowerCase() + " equipment piece";
    }

    protected Materials getMaterial(){
        return material;
    }

    protected Weights getWeight(){
        return weight;
    }

    public float getValue(){
        return weightModifier*valueModifier;
    }

    public String getName(){
        return name;
    }

    public void print(){
        System.out.println(name + ", cost is " + getValue());
    }

    @Override
    public String toString(){
        return name;
    }
}
