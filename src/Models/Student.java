package Models;

public class Student extends User{

    private int studentNumber;

    public Student(String name, String gmail,int studentNumber) {
        super(name, gmail);

        if(studentNumber<=0) throw new IllegalArgumentException("Student Number cannot be zero or negative!");

        this.studentNumber=studentNumber;
    }

    public void setStudentNumber(int studentNumber){
        if(studentNumber<=0) throw new IllegalArgumentException("Student Number cannot be zero or negative!");

        this.studentNumber=studentNumber;
    }

    public int getStudentNumber(){
        return studentNumber;
    }

    @Override
    public void showInfo(){
        System.out.println("Name: "+getName()+" | Gmail: "+getGmail()+" | Student Number: "+ studentNumber);
    }

}
