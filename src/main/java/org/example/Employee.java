
package org.example;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class Employee {

    // Fields
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String name;
    private double salary;
    private String department;
    private String position;
    private Date dateOfJoining;
    private String phoneNumber;
    private String email;
    private String address;
    private String manager;
    private Date DOB;
    private Integer age;
    private EmploymentStatus status;

    // Enum for employment status
    public enum EmploymentStatus {
        Active, Inactive
    }

    // Default constructor
    public Employee() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
        this.age = calculateAge(DOB); // Calculate age when DOB is set
    }

    public Integer getAge() {
        return age;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Method to calculate age based on DOB
    private int calculateAge(Date dob) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dob);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // Adjust age if the birthday hasn't occurred yet this year
        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    // Method to set full name and split it into first, middle, and last names
    public void setFullName(String fullName) {
        String[] nameParts = fullName.split("\\s+");
        this.firstName = nameParts[0];
        this.lastName = nameParts[nameParts.length - 1];
        this.middleName = nameParts.length > 2 ? String.join(" ", Arrays.copyOfRange(nameParts, 1, nameParts.length - 1)) : "";
    }

    // Override toString for better object representation (optional)
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", manager='" + manager + '\'' +
                ", DOB=" + DOB +
                ", age=" + age +
                ", status=" + status +
                '}';
    }
}

