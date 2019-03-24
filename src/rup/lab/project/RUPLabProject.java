package rup.lab.project;
import javax.swing.*;
import java.sql.*;

public class RUPLabProject {

    /**
     * @param args the command line arguments
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/javastuff";
    static Connection conn = null;
    static Statement stmt = null;
    
    static String eid_global = "";
    
    
    public static boolean loginAuth(String eid, String pwd){
    try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM employee WHERE eid="+eid;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()==false)
                    return false;

            String pwd_db = rs.getString("password");
            if (pwd.equals(pwd_db)){
                    eid_global = eid;
                    return true;
            }
            else
                    return false;
        }
        catch(Exception e){
            System.out.println("this happened : "+e);
        }
        return false;
    }
    
    public static int getType(String eid){
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT type FROM employee WHERE eid="+eid;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String type = rs.getString("type");
            return Integer.parseInt(type);
        }
        catch(Exception e){
            System.out.println("this happened : "+e);
        }
        return -1;
	}

    public static void addEmployee(String name, String eid, String pwd, String govtorg, String type){
        try{
                stmt = conn.createStatement();
                String sql;
                sql = "insert into employee values('"+ name +"', "+ eid +", '" + pwd +"', '"+ govtorg +"', "+type+")";
                stmt.executeUpdate(sql);
        }
        catch(Exception e){
                System.out.println("this happened : "+e);
        }
    }
    
    public static void addAdhaar(String name, String aid, String addr, String dob, String other){
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Adhaar values('"+ name +"', "+ aid +", '" + addr +"', '"+ dob +"', '"+ other +"')";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            System.out.println("this happened : "+e);
        }
    }
    
    public static boolean deleteEmployee(String eid){
        try{
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM employee WHERE eid="+eid;
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()==false)
                        return false;
                sql = "delete from employee where eid="+eid;
                stmt.executeUpdate(sql);
                
        }
        catch(Exception e){
//                System.out.println("this happened : "+e);
        }
        return true;
    }
    
    public static String retrieveAdhaar(String aid){
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Adhaar WHERE aid="+aid;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()==false)
                    return "None";
            do{
                //Retrieve by column name
                String name  = rs.getString("name");
//                int aid = rs.getInt("aid");
                String addr = rs.getString("addr");
                String dob = rs.getString("dob");
                String other = rs.getString("other");
                System.out.println("eid_global = "+eid_global);
                
                double ran=Math.random()*100000;
                if(ran<0.099999)
                    ran=ran+0.1;
                int id=(int)ran;
                sql = "insert into History values("+id+", "+aid+", "+eid_global+")";
                stmt.executeUpdate(sql);
                return "Entry Found\nName=\t"+name+"\nid=\t"+aid+"\nAddress=\t"+addr+"\nDate of Birth=\t"+dob+"\nOther Details=\t"+other;
            }while(rs.next());
        }
        catch(Exception e){
                System.out.println("this happened : "+e);
        }
        return "None";
    }

    public static boolean deleteAdhaar(String aid){
            try{
                    stmt = conn.createStatement();
                    String sql;
                    sql = "SELECT * FROM Adhaar WHERE aid="+aid;
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()==false)
                            return false;
                    sql = "delete from Adhaar where aid="+aid;
                    stmt.executeUpdate(sql);
                    
            }
            catch(Exception e){
                    System.out.println("this happened : "+e);
            }
            return true;
    }
    
    public static void main(String[] args) {
        
        try{
                System.out.println("hi");
                String classpath = System.getProperty("java.class.path");
                System.out.println(classpath);

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL,"testuser","password");
                System.out.println("connected");
        }
        catch (Exception e){
                System.out.println("Failed to connect : \n"+e);
        }
        
        
        LogIn obj=new LogIn();
//        AddGovtEmployee obj = new AddGovtEmployee();
        obj.setVisible(true);
        // TODO code application logic here
    }
    
}
