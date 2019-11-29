package co.edu.unal.singinfirebase;

public class Bill {

    Plato platos [];
    double total;

    public Bill(Plato[] platos, double total) {
        this.platos = platos;
        this.total = total;
    }

    public Plato[] getPlatos() {
        return platos;
    }

    public double getTotal() {
        return total;
    }

    public void setPlatos(Plato[] platos) {
        this.platos = platos;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
