package equipment.pieces;

import equipment.Equipment;

public class Sword extends Equipment {

    public Sword(){}

    public Sword(String material, String weight){
        super(material, weight);
        generateName();
        changeValueModifier(0.75f);
    }

    protected void generateName(){
        name = getWeight().toString().toLowerCase() + " " + getMaterial().toString().toLowerCase() + " sword";
    }
}
