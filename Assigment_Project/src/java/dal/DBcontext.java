/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IBaseModel;

/**
 *
 * @author FPTSHOP
 */
public abstract class DBcontext<T extends IBaseModel> {
    protected Connection connection;
    public DBcontext() {
        try {
            String url = "jdbc:sqlserver://MSI\\SQLHOANGANH:1433;databaseName=Ass-project";
            String user = "sa";
            String password = "1";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
