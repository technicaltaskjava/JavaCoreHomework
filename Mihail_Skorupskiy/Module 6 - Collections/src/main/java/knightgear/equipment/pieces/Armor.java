package knightgear.equipment.pieces;

import knightgear.equipment.Equipment;

public class Armor extends Equipment {

    public Armor(String material, String weight){
        super(material, weight);
        generateName();
    }

    @Override
    protected void generateName(){
        name = getWeight().toString().toLowerCase() + " " + getMaterial().toString().toLowerCase() + " armor";
    }
}
