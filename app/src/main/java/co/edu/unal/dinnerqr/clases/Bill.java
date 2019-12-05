package co.edu.unal.dinnerqr.clases;

public class Bill {

    Plato platos [];
    double total;
    boolean pagado;


    public Bill(Plato[] platos, double total, boolean pagado) {
        this.platos = platos;
        this.total = total;
        this.pagado = pagado;
    }

    public Plato[] getPlatos() {
        return platos;
    }

    public double getTotal() {
        return total;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPlatos(Plato[] platos) {
        this.platos = platos;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}
