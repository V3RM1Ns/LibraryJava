package Models;

public final class Teacher extends User {

    private String department;


    public Teacher(String name, String gmail,String department) {
        super(name, gmail);

        if(department==null || department.trim().isEmpty()) throw new IllegalArgumentException("Department cannot be empty!");

        this.department=department;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(){
        if(department==null || department.trim().isEmpty()) throw new IllegalArgumentException("Department cannot be empty!");
        this.department=department;
    }

    @Override
    public void showInfo(){
        System.out.println("Name: "+getName()+" | Gmail: "+getGmail()+" | Department: "+ department);
    }
}
