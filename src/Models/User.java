package Models;

import Extensions.NullCheckExtensions;

public class User {

    private static int _id = 1;
    private final int id;
    private String name;
    private  String gmail;


    public User(String name, String gmail) {
        this.name = NullCheckExtensions.isValidString(name, "Name").trim();
        this.gmail = NullCheckExtensions.isValidString(gmail, "Gmail").trim();
        this.id = _id++;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getGmail(){
        return gmail;
    }

    public void setName(String name){
        this.name = NullCheckExtensions.isValidString(name, "Name").trim();
    }

    public void setGmail(String gmail){
        this.gmail = NullCheckExtensions.isValidString(gmail, "Gmail").trim();
    }

    public  void showInfo(){
        System.out.println("Name: "+name+" | Gmail: "+gmail);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Gmail: " + gmail;
    }

}
