package com.complaint.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

public class BaseDAO {
    // JDBC driver name and database URL
	public static void main (String[]args)
	{
		BaseDAO db = new BaseDAO(1);
		try {
			ResultSet rs = db.getResultSet("SELECT * FROM users ");

			if (rs==null)
			{
				System.out.println ("null");
				return;
			}
			if (rs.next())
			{
				System.out.println (rs.getString(2));
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();	
		}
	}

    static final String JDBC_DRIVER = 
    		"com.mysql.jdbc.Driver";
    static final String DB_URL = 
    		"jdbc:mysql://localhost/test";
    private Connection conn = null;
    private ResultSet resultSet = null;
    private int connIdx=1;
    static final String[][] connections = {
        {"com.mysql.cj.jdbc.Driver",
        	"jdbc:mysql://localhost:3307/test", 
        	"root", ""},
        {"com.mysql.cj.jdbc.Driver", 
        		"jdbc:mysql://localhost/training", 
        		"root", "maria1"},
        {"org.sqlite.JDBC", "jdbc:sqlite:/Users/roberttimlin/Documents/Students/SQLite/db/data.db","",""}
    };

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public BaseDAO() {
        conn = getConn(0);
    }

    public BaseDAO(int i) {
        connIdx=i-1;
        conn = getConn(i-1);
    }

    public BaseDAO(int driver, String server, String database, String usr, String pwd) {

    }
    public static Connection getConnection() {return getConn(0);}
    public static Connection getConn(int idx) {
        try {
            Class.forName(connections[idx][0]);

            //STEP 3: Open a connection
            if (connections[idx][0].equals("org.sqlite.JDBC"))
            {
            	return DriverManager.getConnection(connections[idx][1]);
            }else {
            	return DriverManager.getConnection(connections[idx][1], connections[idx][2], connections[idx][3]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public PreparedStatement prepare(String preparedSql) {

      //  String preparedSql = String.format(stmt);

        try {
        	if(conn==null)
        		getConnection();
            return conn.prepareStatement(preparedSql);
        } catch (Exception e) {
            // use cstmt here
            return null;
        }
    }

    public  CallableStatement prepareCall(String spName) {

        String preparedSql = String.format("{call %s}", spName);

        try {
            return getConn(connIdx).prepareCall(preparedSql);
        } catch (Exception e) {
            // use cstmt here
            return null;
        }
    }
    public BaseList query(BaseList list)
    {
    	try {
    		ResultSet rs = getResultSet(list.getQuery());
    		if(rs==null)
    			return null;
    		list.add(rs);
    		return list;
    	}catch (Exception ex) {
    		
    	}
    	return list;
    }
    public int execute(String sql, Object... values) {
        try {
            String preparedSql = String.format("{call %s}", sql);
            PreparedStatement stmt = getConn(connIdx).prepareStatement(preparedSql);
            int i = 0;
            for (Object obj : values) {
                stmt.setObject(++i, obj);
            }
            stmt.execute();

            return 1;
        } catch (Exception e) {
            // use cstmt here
            return -1;
        }
    }

    public int query(String query) {
        try {
            // String preparedSql = String.format("{call %s}", query);
            Statement stmt = getConn(connIdx).createStatement();

            resultSet = stmt.executeQuery(query);
            return 1;
        } catch (Exception e) {
            // use cstmt here
            e.printStackTrace();
            return -1;
        }
    }

    public boolean next() {
        try {
            return resultSet.next();
        } catch (Exception e) {
            return false;
        }
    }

    public String getString(int i) {
        try {
            return resultSet.getString(i);
        } catch (Exception e) {
            return null;
        }
    }

    public String getString(String s) {
        try {
            return resultSet.getString(s);
        } catch (Exception e) {
            return null;
        }
    }
    public int getInt(int i) {
        try {
            return resultSet.getInt(i);
        } catch (Exception e) {
            return -1;
        }
    }
    public int getInt(String s) {
        try {
            return resultSet.getInt(s);
        } catch (Exception e) {
            return -1;
        }
    }
    public ResultSet getResultSet(String query) {
        try {
            // String preparedSql = String.format("{call %s}", query);
            Statement stmt = getConn(connIdx).createStatement();

            return stmt.executeQuery(query);
        } catch (Exception e) {
            // use cstmt here.
            return null;
        }
    }
    public static int parseInt(String val, int alt)
    {
        try {
            return Integer.parseInt(val);
        }
        catch (Exception e)
        {
            return alt;
        }
    }
    public void close ()
    {
    	  try {
              conn.close();
              conn=null;
          }
          catch (Exception e)
          {
              return;
          }
    }
}//end FirstExample
class BaseList{
	String query;
	public BaseList(){}
	public BaseList(String q) {
		this.query=q;
	}
	public void add(ResultSet rs)
	{
		
	}
	public String getQuery() {return query;}
	
}
