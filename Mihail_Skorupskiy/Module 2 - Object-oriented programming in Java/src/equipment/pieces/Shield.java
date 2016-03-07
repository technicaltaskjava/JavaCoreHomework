package equipment.pieces;

import equipment.Equipment;

public class Shield extends Equipment {

    public Shield(){}

    public Shield(String material, String weight) {
        super(material, weight);
        generateName();
        changeValueModifier(0.5f);
    }

    protected void generateName(){
        name = getWeight().toString().toLowerCase() + " " + getMaterial().toString().toLowerCase() + " shield";
    }
}
