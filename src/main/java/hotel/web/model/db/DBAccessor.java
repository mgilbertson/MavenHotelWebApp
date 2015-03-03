/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mitch
 */
public interface DBAccessor {
    
    public abstract void openConnection(String driverClassName, String url, String username, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException;
        
    public abstract List findAllRecordsInTable(String tableName) throws SQLException, Exception;
    
    public abstract Map getRecordById(String table, String primaryKeyField, Object keyValue) throws SQLException, Exception;
    
    public abstract boolean insertRecord(String table, List colDescriptors, List colValues) throws SQLException, Exception;

    public abstract int updateRecords(String table, List colDescriptors, List colValues, String whereField, Object whereValue) throws SQLException, Exception;
    
    public abstract int deleteRecords(String table, String whereField, Object whereValue) throws SQLException, Exception;
}
