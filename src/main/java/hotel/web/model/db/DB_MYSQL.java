/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mitch
 */
public class DB_MYSQL implements DBAccessor {

    private Connection connection;

    @Override
    public void openConnection(String driverClassName, String url, String username, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException {
        if (url == null || url.length() == 0) {
            throw new IllegalArgumentException("invalid url");
        }
        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public List findAllRecordsInTable(String tableName) throws SQLException, Exception {
        Statement statement = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final List records = new ArrayList();
        Map record = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from " + tableName);
            metaData = rs.getMetaData();
            final int columns = metaData.getColumnCount();
            while (rs.next()) {
                record = new HashMap();
                for (int i = 1; i <= columns; i++) {
                    try {
                        record.put(metaData.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {
                    }
                }
                records.add(record);
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
        
        return records;
    }

    @Override
    public Map getRecordById(String table, String primaryKey, Object keyValue) throws SQLException, Exception {
        Statement statement = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final Map record = new HashMap();
        
        try {
            statement = connection.createStatement();
            String keyVal;
            if (keyValue instanceof String) {
                keyVal = "= '" + keyValue + "'";
            } else {
                keyVal = "=" + keyValue;
            }
            rs = statement.executeQuery("SELECT * FROM " + table + " WHERE " + primaryKey + keyVal);
            metaData = rs.getMetaData();
            metaData.getColumnCount();
            final int columns = metaData.getColumnCount();
            if (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    record.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
        
        return record;
    }

    @Override
    public boolean insertRecord(String table, List colDescriptors, List colValues) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        int recsUpdated = 0;
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO ");
            (sql.append(table)).append(" (");
            final Iterator i1 = colDescriptors.iterator();
            while (i1.hasNext()) {
                (sql.append((String) i1.next())).append(", ");
            }
            sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ") VALUES (");
            for (int j = 0; j < colDescriptors.size(); j++) {
                sql.append("?, ");
            }
            pstmt = connection.prepareStatement((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ")");
            int index = 1;
            final Iterator i2 = colValues.iterator();
            while (i2.hasNext()) {
                final Object obj = i2.next();
                if (obj instanceof String) {
                    pstmt.setString(index++, (String) obj);
                } else if (obj instanceof Integer) {
                    pstmt.setInt(index++, ((Integer) obj));
                } else if (obj instanceof Long) {
                    pstmt.setLong(index++, ((Long) obj));
                } else if (obj instanceof Double) {
                    pstmt.setDouble(index++, ((Double) obj));
                } else if (obj instanceof java.sql.Date) {
                    pstmt.setDate(index++, (java.sql.Date) obj);
                } else if (obj instanceof Boolean) {
                    pstmt.setBoolean(index++, ((Boolean) obj));
                } else {
                    if (obj != null) {
                        pstmt.setObject(index++, obj);
                    }
                }
            }
            recsUpdated = pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
        if (recsUpdated == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int updateRecords(String table, List colDescriptors, List colValues, String whereField, Object whereValue) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        int recsUpdated = 0;
        try {
            StringBuffer sql = new StringBuffer("UPDATE ");
            (sql.append(table)).append(" SET ");
            final Iterator i1 = colDescriptors.iterator();
            while (i1.hasNext()) {
                (sql.append((String) i1.next())).append(" = ?, ");
            }
            sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
            ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
            pstmt = connection.prepareStatement(sql.toString());
            final Iterator i2 = colValues.iterator();
            int index = 1;
            boolean doWhereValueFlag = false;
            Object obj = null;

            while (i2.hasNext() || doWhereValueFlag) {
                if (!doWhereValueFlag) {
                    obj = i2.next();
                }
                if (obj instanceof String) {
                    pstmt.setString(index++, (String) obj);
                } else if (obj instanceof Integer) {
                    pstmt.setInt(index++, ((Integer) obj));
                } else if (obj instanceof Long) {
                    pstmt.setLong(index++, ((Long) obj));
                } else if (obj instanceof Double) {
                    pstmt.setDouble(index++, ((Double) obj));
                } else if (obj instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(index++, (java.sql.Timestamp) obj);
                } else if (obj instanceof java.sql.Date) {
                    pstmt.setDate(index++, (java.sql.Date) obj);
                } else if (obj instanceof Boolean) {
                    pstmt.setBoolean(index++, ((Boolean) obj));
                } else {
                    if (obj != null) {
                        pstmt.setObject(index++, obj);
                    }
                }
                if (doWhereValueFlag) {
                    break;
                }
                if (!i2.hasNext()) {
                    doWhereValueFlag = true;
                    obj = whereValue;
                }
            }
            recsUpdated = pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
        return recsUpdated;
    }

    @Override
    public int deleteRecords(String table, String whereField, Object whereValue) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        int recsDeleted = 0;
        
        try {
            final StringBuffer sql=new StringBuffer("DELETE FROM ");
		sql.append(table);
		if(whereField != null ) {
			sql.append(" WHERE ");
			(sql.append( whereField )).append(" = ?");
		}
		pstmt =  connection.prepareStatement(sql.toString());
            if (whereField != null) {
                if (whereValue instanceof String) {
                    pstmt.setString(1, (String) whereValue);
                } else if (whereValue instanceof Integer) {
                    pstmt.setInt(1, ((Integer) whereValue));
                } else if (whereValue instanceof Long) {
                    pstmt.setLong(1, ((Long) whereValue));
                } else if (whereValue instanceof Double) {
                    pstmt.setDouble(1, ((Double) whereValue));
                } else if (whereValue instanceof java.sql.Date) {
                    pstmt.setDate(1, (java.sql.Date) whereValue);
                } else if (whereValue instanceof Boolean) {
                    pstmt.setBoolean(1, ((Boolean) whereValue));
                } else {
                    if (whereValue != null) {
                        pstmt.setObject(1, whereValue);
                    }
                }
            }
            recsDeleted = pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
        
        return recsDeleted;
    }

}

