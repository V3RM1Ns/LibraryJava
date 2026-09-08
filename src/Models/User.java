package Models;

public class User {

    private static int _id;
    private final int id;
    private String name;
    private  String gmail;


    public User(String name, String gmail) {
        if (name==null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (gmail==null || gmail.trim().isEmpty()) throw new IllegalArgumentException("Gmail cannot be empty");


        this.name = name.trim();
        this.gmail = gmail;
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
        if (name != null && !name.trim().isEmpty()) this.name = name;

    }

    public void setGmail(String gmail){
        if (gmail != null && !gmail.trim().isEmpty()) this.gmail = gmail;

    }

    public  void showInfo(){
        System.out.println("Name: "+name+" | Gmail: "+gmail);
    }



}
