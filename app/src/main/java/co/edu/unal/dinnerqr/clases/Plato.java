package co.edu.unal.dinnerqr.clases;

public class Plato {
    String id, name, description;
    double price;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setPrice(double price) {
        this.price = price;
    }
}
