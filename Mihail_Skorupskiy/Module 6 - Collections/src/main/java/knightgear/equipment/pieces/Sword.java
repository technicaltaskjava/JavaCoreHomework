package knightgear.equipment.pieces;

import knightgear.equipment.Equipment;

public class Sword extends Equipment {

    public Sword(String material, String weight){
        super(material, weight);
        generateName();
        changeValueModifier(0.75f);
    }

    @Override
    protected void generateName(){
        name = getWeight().toString().toLowerCase() + " " + getMaterial().toString().toLowerCase() + " sword";
    }
}
