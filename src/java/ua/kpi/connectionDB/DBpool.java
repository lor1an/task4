/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.connectionDB;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * Соединение с пулом соединений 
 * Использует шаблон Singleton для невозможности инициализации другого объекта этого класса 
 */
public class DBpool {

    private static DataSource dataSource;

    private DBpool() {
    }

    public static synchronized DataSource getInstance() throws SQLException {
        if (dataSource == null) {
            try {
                javax.naming.Context initialContext = new javax.naming.InitialContext();
             
                dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/web_lib");
           
            } catch (NamingException ex) {
              
            }
        }
        return dataSource;
    }
}
