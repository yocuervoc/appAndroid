package co.edu.unal.singinfirebase.clases;

public class Restaurant {
    String name, address;
    Plato menu [];


    public Restaurant(String name, String address, Plato[] menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Plato[] getMenu() {
        return menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMenu(Plato[] menu) {
        this.menu = menu;
    }
}
