package co.edu.unal.singinfirebase.clases;

public class Cliente {
    String name, eMail, password;
    Bill bill;
    Bill history [];

    public Cliente(String name, String eMail, String password, Bill bill, Bill[] history) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;
        this.bill = bill;
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public Bill getBill() {
        return bill;
    }

    public Bill[] getHistory() {
        return history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setHistory(Bill[] history) {
        this.history = history;
    }
}
