
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mueez
 */
public class DatabaseClass {
    
    public Connection con = null;
    public String db_url = "";
    public Statement st;
    public PreparedStatement ps;
    public String query = "";
    public ResultSet rs;
    
    
    public DatabaseClass()
    {
        try{
                    Class.forName("com.mysql.jdbc.Driver");
                    db_url = "jdbc:mysql://localhost/studylifedb";
                    con = DriverManager.getConnection(db_url, "root", "");
                    st = con.createStatement();
                    
//                    query = "Select * from classtable";
//                    rs = st.executeQuery(query);
//                    while(rs.next())
//                    {
//                        System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t");
//                    }

                }catch(Exception e){
                    System.out.println("My Error in DatabaseClass constructor: " + e);
                }
    }
    
    
    public void insertDbClass(String classId, String className)
    {
        try {
            query = "Insert into classtable(class_id, class_name) values(?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, classId);
            ps.setString(2, className);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error in DatabaseClass inserDbtClass: " + ex);
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ResultSet getDbClass()
    {
        
        try{
            query = "select * from classtable";
            rs = st.executeQuery(query);
            
//            while(rs.next())
//                    {
//                        System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t");
//                    }

                }catch(Exception e){
                    System.out.println("My Error in getDbClass: " + e);
                }
        return rs;
    }
    public ResultSet getDbClass(String classId)
    {
        query = "select * from classtable where class_id = ?";
        ResultSet rsTemp = null;
        String temp = "";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, classId);
            rsTemp = ps.executeQuery();
//            rsTemp.first();
//            temp = rsTemp.getString(2);
        } catch (SQLException ex) {
            
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsTemp;
    }
    
    
//    public void insertTask(String taskName, int taskStatus, String taskType, String classId, String taskDescription, String dueDate, int pinStatus, String grade)
    public void insertDbTask(String taskName, int taskStatus, String description, String classId, String taskType, String dueDate)
    {
        try {
            query = "Insert into tasktable(task_name, task_status, task_type, class_id, description, due_date, pin_status, grade) values(?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, taskName);
            ps.setInt(2, taskStatus);
            ps.setString(3, taskType);
            ps.setString(4, classId);
            ps.setString(5, description);
            
            if(dueDate.equals("null"))
                ps.setString(6, null);
            else ps.setString(6, dueDate);

            ps.setInt(7, 0);   // default set of Pin Status
            ps.setString(8, "");    // default set of Grade
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error in DatabaseClass insertDbTask: " + ex);
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


 public ResultSet getDbTask()
    {
        
        try{
            query = "select * from tasktable where task_status=0 and due_date != '0000-00-00' order by due_date";
            rs = st.executeQuery(query);
            
//            while(rs.next())
//                    {
//                        System.out.println(rs.getString(1) + "\t");
//                    }

                }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
                }
        return rs;
    }
 
 
 public ResultSet getDbTask(String classId)
    {
        ResultSet rsTemp = null;
        try{
            query = "select * from tasktable where class_id = ? ans task_status=0 and due_date != '0000-00-00' order by due_date";
            ps = con.prepareStatement(query);
            ps.setString(1, classId);
            rsTemp = ps.executeQuery();
            
            
//            while(rs.next())
//                    {
//                        System.out.println(rs.getString(1) + "\t");
//                    }

                }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
                }
        return rsTemp;
    }
 
    public ResultSet getDbTaskFromThisDate(String date)
    {
//        ResultSet rsTemp = null;
        
        try{

            query = "select * from tasktable where due_date>=? and task_status=0 order by due_date";
            ps = con.prepareStatement(query);
            ps.setString(1, date);
            rs = ps.executeQuery();
                        

//        System.out.println("lala 4: " + rs.next());
//        while(rs.next())
//                    {
//                        System.out.println(rs.getString(3) + "\t");
//                    }

        }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
        }
        return rs;
    }
 
    
    public ResultSet getDbTaskOnThisDate(String date)
    {
//        ResultSet rsTemp = null;
        
        try{

            query = "select * from tasktable where due_date=? and task_status=0";
            ps = con.prepareStatement(query);
            ps.setString(1, date);
            rs = ps.executeQuery();
                        

//        System.out.println("lala 4: " + rs.next());
//        while(rs.next())
//                    {
//                        System.out.println(rs.getString(3) + "\t");
//                    }

        }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
        }
        return rs;
    }
    
    
    public ResultSet getDbTaskCourseDate(String classId, String date)
    {
//        ResultSet rsTemp = null;
        
        try{

            query = "select * from tasktable where class_id=? and due_date>=? and task_status=0 order by due_date";
            ps = con.prepareStatement(query);
            ps.setString(1, classId);
            ps.setString(2, date);
            rs = ps.executeQuery();
                        

//        System.out.println("lala 4: " + rs.next());
//        while(rs.next())
//                    {
//                        System.out.println(rs.getString(3) + "\t");
//                    }

        }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
        }
        return rs;
    }
    
    public void taskComplete(int taskId)
    {
        try{
            
            query = "UPDATE tasktable SET task_status=1 WHERE tasktable.task_id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, taskId);
            ps.executeUpdate();
        }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
        }
    }
    
    public boolean hasTask(String date)
    {
        ResultSet rstemp1;
        boolean temp=false;
        try{
            
            query = "select * from tasktable WHERE tasktable.due_date=?";
            ps = con.prepareStatement(query);
            ps.setString(1, date);
            rstemp1 = ps.executeQuery();
            if(rstemp1.next())
                temp = true;
        }catch(Exception e){
                    System.out.println("My Error in getDbTask: " + e);
        }
        return temp;
    }
}