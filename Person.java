package DOANCUOIKI;

import java.io.Serializable;

public abstract class Person extends Entity implements Serializable {
    
    protected String username;
    protected String password;
    protected String fullName;
    protected int age;
    protected String gender;
    protected String phone;

    public Person(String username, String password, String fullName, int age, String gender, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
    }

    abstract public double SumSalary();


    

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
