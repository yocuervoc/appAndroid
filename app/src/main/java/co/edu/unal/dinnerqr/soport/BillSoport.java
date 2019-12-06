package co.edu.unal.dinnerqr.soport;


public class BillSoport {
    private String name;
    private String birthday;


    public BillSoport(String name, String birthday) {
        this.birthday = birthday;
        this.name = name;

    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
