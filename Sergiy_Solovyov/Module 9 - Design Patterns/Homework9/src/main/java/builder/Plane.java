package builder;

/**
 * @author Sergey Solovyov
 */
public class Plane {
    enum Color {RED, BLACK, WHITE}

    enum Fuselage {TUBE, BARREL, BOX, LOBE, CIGAR, NACELLE}

    private Color color;
    private Fuselage fuselage;
    private String name;

    public Plane(String name) {
        this.name = name;
    }

    public void setFuselage(Fuselage fuselage) {this.fuselage = fuselage;}

    public void setColor(Color color) {this.color = color;}

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", fuselage='" + fuselage + '\'' +
                '}';
    }
}
