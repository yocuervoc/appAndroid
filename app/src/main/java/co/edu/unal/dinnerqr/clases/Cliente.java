package co.edu.unal.dinnerqr.clases;

public class Cliente {
    String  id, name, eMail;
    String bill;



/*
    public Cliente( String id, String name, String eMail, String bill) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
        this.bill = bill;
    }

 */



    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public String getBill() {
        return bill;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
