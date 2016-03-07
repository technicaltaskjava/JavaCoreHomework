package equipment;

import extras.*;

public class Equipment{

    private Weights weight;
    private Materials material;
    private float valueModifier;
    private float weightModifier;
    protected String name;

    protected Equipment(){
    }

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
        materialName = materialName.toUpperCase();
        try {
            for (int i = 0; i < Materials.values().length; i++){
                if (material == null) {
                    if (materialName.equals(Materials.values()[i].toString())) {
                        material = Materials.values()[i];
                    }
                } else{
                    break;
                }
            }
        } catch (Throwable IllegalArgumentException) {
            System.out.println("Not a proper material, material set to bronze.");
            material = Materials.BRONZE;
        }
    }

    private void setWeight(String weightName){
        weightName = weightName.toUpperCase();
        try {
            for (int i = 0; i < Weights.values().length; i++){
                if (weight == null) {
                    if (weightName.equals(Weights.values()[i].toString())) {
                        weight = Weights.values()[i];
                    }
                }else{
                    break;
                }
            }
        } catch (Throwable IllegalArgumentException) {
            System.out.println("Not a proper weight, weight set to light.");
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
}
