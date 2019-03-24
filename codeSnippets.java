// LOGIN /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {                                         
    String uname=txtUserName.getText();
    String pass=new String(txtPassword.getPassword());
    
    boolean validate = RUPLabProject.loginAuth(uname, pass);
    int type = RUPLabProject.getType(uname);

    if(type==0 && validate) //open admin
    {
        dispose();
        WelcomeAdmin obj=new WelcomeAdmin();
        obj.setVisible(true);
        
    }
    else if(type==1 && validate) //open employee
    {
        dispose();
        WelcomeEmployee obj=new WelcomeEmployee();
        obj.setVisible(true);
    }
    else if(type==2 && validate) //open worker
    {
        dispose();
        WelcomeWorker obj=new WelcomeWorker();
        obj.setVisible(true);
    }
    else if(!validate)
        JOptionPane.showMessageDialog(null,"Record Not Found");    
}

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


// ADD GOVERNMENT EMPLOYEE //////////////////////////////////////////////////////////////////////////////////////////////////////
private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                          
    String name, uname, pass, gorgn, type;
    name=txtName.getText();
    uname=txtUserName.getText();
    pass=new String(txtPassword.getPassword());
    gorgn=txtOrgn.getText();
    type=txtType.getText();
    if(!name.equals("") && !uname.equals("") && !pass.equals("") && !gorgn.equals(""))
    {
        RUPLabProject.addEmployee(name, uname, pass, gorgn, type);
    }
    else
        JOptionPane.showMessageDialog(null,"Invalid Input");
    JOptionPane.showMessageDialog(null,"Government Employee Authorized!");
    dispose();
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


// DELETE AADHAR RECORD //////////////////////////////////////////////////////////////////////////////////////////////////////
private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
    String id=txtAdhaarNumber.getText();
    boolean isDeleted = RUPLabProject.deleteAdhaar(id);
    if(!isDeleted)
        JOptionPane.showMessageDialog(null, "Adhaar ID not found");
    else
    {
        JOptionPane.showMessageDialog(null, "Adhaar entry deleted");
        dispose();
    }  
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


// RETRIEVE AADHAR RECORD //////////////////////////////////////////////////////////////////////////////////////////////////////
private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
    String name=txtName.getText();
    String id=txtAdhaarNo.getText();
    String str = RUPLabProject.retrieveAdhaar(id);
    if(str.equals("None")){
        JOptionPane.showMessageDialog(new JFrame(), "Inavlid Input, try again", "Dialog",JOptionPane.ERROR_MESSAGE);
        
    }
    else{
        JOptionPane.showMessageDialog(null, str);
        
        dispose();
        EmployeePayment obj=new EmployeePayment();
        obj.setVisible(true);
    }
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
            String name  = rs.getString("name");
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


// DEAUTHORZE GOVT EMPLOYEE //////////////////////////////////////////////////////////////////////////////////////////////////////
private void btnDeauthorizeActionPerformed(java.awt.event.ActionEvent evt) {                                               

    String uname=txtUserName.getText();
    
    boolean isDeleted = RUPLabProject.deleteEmployee(uname);
    
    if(!isDeleted)
        JOptionPane.showMessageDialog(null, "No such employee present");
    else
    {
        JOptionPane.showMessageDialog(null, "Employee deauthorized");
        dispose();
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
            System.out.println("this happened : "+e);
    }
    return true;
}
