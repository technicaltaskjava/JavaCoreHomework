package equipment.pieces;

import equipment.Equipment;

public class Armor extends Equipment {

    public Armor(){}

    public Armor(String material, String weight){
        super(material, weight);
        generateName();
    }

    protected void generateName(){
        name = getWeight().toString().toLowerCase() + " " + getMaterial().toString().toLowerCase() + " armor";
    }
}
