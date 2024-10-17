package org.example;


import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDaoIntrf {
    Connection con;

    @Override
    public void createEmployee(Employee emp) {
        // Establish the database connection
        con = DBConnection.createDBConnetion();
        String query = "INSERT INTO employees (firstName, middleName, lastName, salary, department, position, DOJ, phone, email, address, manager, DOB, age, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, emp.getFirstName());
            pstm.setString(2, emp.getMiddleName());
            pstm.setString(3, emp.getLastName());
            pstm.setDouble(4, emp.getSalary());
            pstm.setString(5, emp.getDepartment());
            pstm.setString(6, emp.getPosition());

            // Convert the Date object to java.sql.Date for the DOJ field
            Date doj = new Date(emp.getDateOfJoining().getTime());
            pstm.setDate(7, doj);

            pstm.setString(8, emp.getPhoneNumber());
            pstm.setString(9, emp.getEmail());
            pstm.setString(10, emp.getAddress());
            pstm.setString(11, emp.getManager());

            // Convert the Date object to java.sql.Date for the DOB field
            Date dob = new Date(emp.getDOB().getTime());
            pstm.setDate(12, dob);

            pstm.setInt(13, emp.getAge());
            pstm.setString(14, emp.getStatus().name());

            // Execute the update
            int cnt = pstm.executeUpdate();
            if (cnt != 0) {
                // Retrieve generated keys (ID)
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1);
                        System.out.println("Employee Inserted Successfully! New Employee ID: " + id);
                    } else {
                        System.out.println("Employee inserted, but no ID was returned.");
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //    @Override
//    public void updateEmployee(int id, String firstName, String middleName, String lastName, String department, String position, String dojInput, String phone, String email, String address, String manager, String DOB, int age, String isactive) {
//        con=DBConnection.createDBConnetion();
//        String query="update employee set name=? where id=?";
//        try{
//            PreparedStatement pstm=con.prepareStatement(query);
//            pstm.setString(1,name);
//            pstm.setInt(2,id);
//            int cnt=pstm.executeUpdate();
//            if(cnt!=0)
//                System.out.println("Employee Details updated successfully !!");
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }
    @Override
    public void updateEmployee(int id, String firstName, String middleName, String lastName, String department, String position, String dojInput, String phone, String address, String manager, String DOB, int age, String isactive) {
        con = DBConnection.createDBConnetion();

        // Build the update query dynamically based on provided fields
        StringBuilder queryBuilder = new StringBuilder("UPDATE employees SET ");

        boolean hasPreviousField = false;

        if (firstName != null) {
            queryBuilder.append("firstName = ?, ");
            hasPreviousField = true;
        }
        if (middleName != null) {
            queryBuilder.append("middleName = ?, ");
            hasPreviousField = true;
        }
        if (lastName != null) {
            queryBuilder.append("lastName = ?, ");
            hasPreviousField = true;
        }
        if (department != null) {
            queryBuilder.append("department = ?, ");
            hasPreviousField = true;
        }
        if (position != null) {
            queryBuilder.append("position = ?, ");
            hasPreviousField = true;
        }
        if (dojInput != null) {
            queryBuilder.append("DOJ = ?, ");
            hasPreviousField = true;
        }
        if (phone != null) {
            queryBuilder.append("phone = ?, ");
            hasPreviousField = true;
        }
        if (address != null) {
            queryBuilder.append("address = ?, ");
            hasPreviousField = true;
        }
        if (manager != null) {
            queryBuilder.append("manager = ?, ");
            hasPreviousField = true;
        }
        if (DOB != null) {
            queryBuilder.append("DOB = ?, ");
            hasPreviousField = true;
        }
        if (age >= 0) {
            queryBuilder.append("age = ?, ");
            hasPreviousField = true;
        }
        if (isactive != null) {
            queryBuilder.append("isActive = ? ");
        }

        // Remove the last comma and add the WHERE clause
        String query = queryBuilder.toString().replaceAll(", $", "") + " WHERE id = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(query);
            int index = 1; // For parameter index

            // Set parameters for the prepared statement
            if (firstName != null) {
                pstm.setString(index++, firstName);
            }
            if (middleName != null) {
                pstm.setString(index++, middleName);
            }
            if (lastName != null) {
                pstm.setString(index++, lastName);
            }
            if (department != null) {
                pstm.setString(index++, department);
            }
            if (position != null) {
                pstm.setString(index++, position);
            }
            if (dojInput != null) {
                // Convert the string to Date if needed
                Date doj = Date.valueOf(dojInput);
                pstm.setDate(index++, doj);
            }
            if (phone != null) {
                pstm.setString(index++, phone);
            }
            if (address != null) {
                pstm.setString(index++, address);
            }
            if (manager != null) {
                pstm.setString(index++, manager);
            }
            if (DOB != null) {
                // Convert the string to Date if needed
                Date dob = Date.valueOf(DOB);
                pstm.setDate(index++, dob);
            }
            if (age >= 0) {
                pstm.setInt(index++, age);
            }
            if (isactive != null) {
                pstm.setString(index++, isactive);
            }

            // Set the id for the WHERE clause
            pstm.setInt(index, id);

            // Execute the update
            int cnt = pstm.executeUpdate();
            if (cnt != 0) {
                System.out.println("Employee details updated successfully!");
            } else {
                System.out.println("No employee found with the provided ID.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @Override

    public void showAllEmployee() {
        String query = "SELECT * FROM employees WHERE isActive = 'Active'";
        System.out.println("Employee Details:");
        System.out.println("---------------------------------------------");

        // Update the header to reflect actual columns
//        System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
//                "ID", "First Name", "Middle Name", "Last Name", "Position", "DOJ",
//                "Phone", "Email", "Address", "Manager", "Age", "Active");
        System.out.println("---------------------------------------------");

        try (Connection con = DBConnection.createDBConnetion();
             Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {
                System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%s\n",
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("middleName"),
                        result.getString("lastName"),
                        result.getString("position"),
                        result.getDate("DOJ"), // Use result.getDate for DATE type
                        result.getString("phone"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("manager"),
                        result.getInt("age"),
                        result.getString("isActive"));
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    @Override
    public void showEmployeeBasedOnID(int id) {
        Connection con = null;  // Ensure con is local to this method for better resource management
        String query = "SELECT * FROM employees WHERE id = ? AND isActive = 'Active'";

        try {
            con = DBConnection.createDBConnetion();  // Create the connection
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                // Format output to match the columns of the employees table
//            System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
//                    "ID", "First Name", "Middle Name", "Last Name", "Position", "DOJ",
//                    "Phone", "Email", "Address", "Manager", "Age", "Active");
                System.out.println("---------------------------------------------");

                System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%s\n",
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("middleName"),
                        result.getString("lastName"),
                        result.getString("position"),
                        result.getDate("DOJ"), // Format if needed
                        result.getString("phone"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("manager"),
                        result.getInt("age"),
                        result.getString("isActive"));
            } else {
                System.out.println("No employee found with ID: " + id);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //
//
//    public void deleteEmployee() {
//        deleteEmployee(0);
//    }

    //
//
    @Override
    public void deactivateEmployee(int id) {
        con = DBConnection.createDBConnetion();
        String query = "UPDATE employees SET isActive = 'Inactive' WHERE id = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            int cnt = pstm.executeUpdate();

            if (cnt != 0) {
                System.out.println("Employee status updated to Inactive successfully for ID: " + id);
            } else {
                System.out.println("No employee found with ID: " + id);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    @Override
    public void deleteEmployee(int id) {
        con = DBConnection.createDBConnetion();
        String query = "DELETE FROM employees WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            int cnt = pstm.executeUpdate();
            if (cnt != 0)
                System.out.println("Employee Deleted Successfully!!! " + id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
