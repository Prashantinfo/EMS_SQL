package org.example;




import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class DBConnection {
    static Connection con;


    public static Connection createDBConnetion(){
        Scanner sc=new Scanner(System.in);

        try{
            //load driver
//            Class.forName("com.mysql.jdbc.Driver");
            //get connection
//            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Enter your db name :");
            String dbname=sc.nextLine();

            String url="jdbc:mysql://localhost:3306/"+dbname+"?useSSL=false";
            System.out.println("enter your db username :");

            String username=sc.nextLine();
            System.out.println("enter your db password :");
            String password=sc.nextLine();
            con= DriverManager.getConnection(url,username,password);
            System.out.println("connect hogya");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return con;

    }



}