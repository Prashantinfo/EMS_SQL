package org.example;


public interface EmployeeDaoIntrf {


    public void createEmployee(Employee emp);

    public void showAllEmployee();

    public void showEmployeeBasedOnID(int id);

    public void updateEmployee(int id, String firstName, String middleName, String lastName, String department, String position, String dojInput, String phone, String address, String manager, String DOB, int age, String isactive);

    public void deactivateEmployee(int id);

    public  void deleteEmployee(int id);
}