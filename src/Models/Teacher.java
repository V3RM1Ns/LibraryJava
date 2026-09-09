package Models;

import Extensions.NullCheckExtensions;

public final class Teacher extends User {

    private String department;

    public Teacher(String name, String gmail,String department) {
        super(name, gmail);

        this.department = NullCheckExtensions.isValidString(department, "Department").trim();
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(){
        this.department = NullCheckExtensions.isValidString(department, "Department").trim();
    }

    @Override
    public void showInfo(){
        System.out.println("Name: "+getName()+" | Gmail: "+getGmail()+" | Department: "+ department);
    }
}
