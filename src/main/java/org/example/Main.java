package org.example;

import mongo.MongoDBTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBConnection.createDBConnetion();


        EmployeeDaoIntrf dao = new EmployeeDaoImpl();

        LeaveManagementDAO leaveDao = new LeaveManagementDAO();
        System.out.println("Welcome to Employee management application");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Add Employee\n" +
                    "2. Show All Employee\n" +
                    "3. Show Employee based on id \n" +
                    "4. Update the employee\n" +
                    "5. Deactivate the employee\n"+
                    "6. Delete the employee\n"+
                    "7. Apply for Leave\n" +
                    "8. View Leave History\n" +
                    "9. Manage Leave Requests (for managers)\n" +
                    "10. Exit\n");



            System.out.println("Enter Choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    Employee emp = new Employee();
//                    System.out.println("Enter ID: ");
//                    int id = sc.nextInt();
//                    sc.nextLine(); // Consume the newline character

                    System.out.println("Enter full name (first middle last): ");
                    String name = sc.next();
                    sc.nextLine();


                    System.out.println("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine(); // Consume the newline character

                    System.out.println("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.println("Enter Position: ");
                    String position = sc.nextLine();

                    System.out.print("Enter date of joining (yyyy-MM-dd): ");
                    String dateInput = sc.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


                    try {
                        emp.setDateOfJoining(dateFormat.parse(dateInput));
//                        System.out.println("Date of Joining: " + emp.getDateOfJoining());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }

                    System.out.println("Enter Phone Number: ");
                    String phoneNumber = sc.nextLine();
//                    if(!utility.isValidPhoneNumber(phoneNumber)){
//                        return;
//                    }


                    System.out.println("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.println("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.println("Enter Manager's Name: ");
                    String manager = sc.nextLine();

                    System.out.print("Enter date of birth (yyyy-MM-dd): ");
                    String dobInput = sc.nextLine();

                    try {
                        emp.setDOB(dateFormat.parse(dobInput));
//                        emp.setAge(emp.calculateAge(dateFormat.parse(dobInput)));

                    } catch (ParseException e) {
                        System.out.println("Invalid date format for date of birth. Please use yyyy-MM-dd.");
                    }

                    System.out.print("Enter status (Active, Inactive): ");
                    String statusInput = sc.nextLine();

                    try {
                        emp.setStatus(Employee.EmploymentStatus.valueOf(statusInput));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status. Please enter either ACTIVE or INACTIVE.");
                    }


                    emp.setName(name);
                    emp.setSalary(salary);
                    emp.setDepartment(department);
                    emp.setPosition(position);
//                    emp.setDateOfJoining(dateOfJoining);
                    emp.setPhoneNumber(phoneNumber);
                    emp.setEmail(email);
                    emp.setAddress(address);
                    emp.setManager(manager);
//                    emp.setDOB(DOB);
//                    emp.setIsactive(isActive);
//                    int a=emp. getAge(DOB);
//                    emp.setAge(a);

                    emp.setFullName(name);
                    dao.createEmployee(emp);
                    System.out.println(emp);

                    break;
                case 2:
                    dao.showAllEmployee();
                    break;
                case 3:
                    System.out.println("Enter id to show the details ");
                    int empid = sc.nextInt();
                    dao.showEmployeeBasedOnID(empid);
                    break;
                case 4:



                    System.out.print("Enter the ID of the employee to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();


                    System.out.println("Enter the new first name (leave blank to skip):");
                    String firstName = sc.nextLine();
                    if (firstName.isEmpty()) firstName = null;

                    System.out.println("Enter the new middle name (leave blank to skip):");
                    String middleName = sc.nextLine();
                    if (middleName.isEmpty()) middleName = null;

                    System.out.println("Enter the new last name (leave blank to skip):");
                    String lastName = sc.nextLine();
                    if (lastName.isEmpty()) lastName = null;

                    System.out.println("Enter the new department (leave blank to skip):");
                    department = sc.nextLine();
                    if (department.isEmpty()) department = null;

                    System.out.println("Enter the new position (leave blank to skip):");
                    position = sc.nextLine();
                    if (position.isEmpty()) position = null;

                    System.out.println("Enter the new date of joining (yyyy-MM-dd) (leave blank to skip):");
                    String dojInput = sc.nextLine();
                    if (dojInput.isEmpty()) dojInput = null;

                    System.out.println("Enter the new phone number (leave blank to skip):");
                    String phone = sc.nextLine();
                    if (phone.isEmpty()) phone = null;

                    System.out.println("Enter the new address (leave blank to skip):");
                    address = sc.nextLine();
                    if (address.isEmpty()) address = null;

                    System.out.println("Enter the new manager's name (leave blank to skip):");
                    manager = sc.nextLine();
                    if (manager.isEmpty()) manager = null;

                    System.out.println("Enter the new date of birth (yyyy-MM-dd) (leave blank to skip):");
                    String DOB = sc.nextLine();
                    if (DOB.isEmpty()) DOB = null;

                    System.out.println("Enter the new age (leave blank to skip):");
                    String ageInput = sc.nextLine();
                    int age = -1; // Default invalid value
                    if (!ageInput.isEmpty()) {
                        age = Integer.parseInt(ageInput);
                    }

                    System.out.println("Enter the new active status (Active/Inactive) (leave blank to skip):");
                    String isactive = sc.nextLine();
                    if (isactive.isEmpty()) isactive = null;


                    dao.updateEmployee(id, firstName, middleName, lastName, department, position, dojInput, phone, address, manager, DOB, age, isactive);



                    break;


                case 5:
                    System.out.println("Enter the id to deactivate the employee: ");
                    id=sc.nextInt();
                    dao.deactivateEmployee(id);
                    break;
                case 6 :
                    System.out.println("Enter the id to delete the employee: ");
                    id=sc.nextInt();
                    dao.deleteEmployee(id);
                    break;


                case 7:
                    System.out.println("Enter Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Leave Type (CASUAL, SICK, VACATION): ");
                    String leaveType = sc.nextLine();

                    System.out.println("Enter Start Date (yyyy-MM-dd): ");
                    String startDateStr = sc.nextLine();

                    System.out.println("Enter End Date (yyyy-MM-dd): ");
                    String endDateStr = sc.nextLine();

                    System.out.println("Enter Reason: ");
                    String reason = sc.nextLine();

                    try {
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        LeaveRequest leaveRequest = new LeaveRequest(
                                empId,
                                leaveType,
                                dateFormat.parse(startDateStr),
                                dateFormat.parse(endDateStr),
                                reason
                        );
                        leaveDao.applyLeave(leaveRequest);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd");
                    }
                    break;

                case 8:
                    System.out.println("Enter Employee ID: ");
                    empId = sc.nextInt();
                    List<LeaveRequest> leaveHistory = leaveDao.getEmployeeLeaveHistory(empId);

                    System.out.println("\nLeave History:");
                    System.out.println("----------------------------------------");
                    for (LeaveRequest leave : leaveHistory) {
                        System.out.printf("Leave ID: %s\nType: %s\nFrom: %s\nTo: %s\nStatus: %s\n",
                                leave.getId(),
                                leave.getLeaveType(),
                                leave.getStartDate(),
                                leave.getEndDate(),
                                leave.getStatus());



                        if (leave.getApproverComments() != null) {
                            System.out.println("Comments: " + leave.getApproverComments());
                        }
                        System.out.println("----------------------------------------");
                    }
                    break;

                case 9:
                    List<LeaveRequest> pendingLeaves = leaveDao.getPendingLeaves();

                    if (pendingLeaves.isEmpty()) {
                        System.out.println("No pending leave requests.");
                        break;
                    }

                    System.out.println("\nPending Leave Requests:");
                    System.out.println("----------------------------------------");
                    for (LeaveRequest leave : pendingLeaves) {
                        System.out.printf("Leave ID: %s\nEmployee ID: %d\nType: %s\nFrom: %s\nTo: %s\nReason: %s\n",
                                leave.getId(),
                                leave.getEmployeeId(),
                                leave.getLeaveType(),
                                leave.getStartDate(),
                                leave.getEndDate(),
                                leave.getReason());
                        System.out.println("----------------------------------------");
                    }

                    System.out.println("Enter Leave ID to process (or 0 to skip): ");
                    sc.nextLine();
                    String leaveId = sc.nextLine();

                    if (!leaveId.equals("0")) {
                        System.out.println("Enter Status (APPROVED/REJECTED): ");
                        String status = sc.nextLine();

                        System.out.println("Enter Comments: ");
                        String comments = sc.nextLine();

                        leaveDao.updateLeaveStatus(leaveId, status, comments);
                    }
                    break;


                case 10:
                    System.out.println("Thank you for using our Application !!!");
                    System.exit(0);
                default:
                    System.out.println("Enter valid choice !");
                    break;


            }

        } while (true);

    }

}