package com.complaint.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.complaint.models.User;

public class UserDAO extends BaseDAO {
	private static final String GC_SQL_INSERT =
			"INSERT INTO users (username,`password`,role_id) VALUES (?,PASSWORD(?),?)";
	public static void main (String[]args)
	{
		UserDAO dao = new UserDAO();
		//ArrayList<User> users= dao.engineers("12346");// ("testerb","Test1234");
		
		//System.out.println(users);
	}
	public int create(User user) {
		BaseDAO db = new BaseDAO(1);
		try {
			PreparedStatement ps = 
					db.prepare(GC_SQL_INSERT);
			//user.setPassword("PASSWORD(Test1234)");
			//user.setRoleId(1);
			user.setStatus(1);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());

			ps.setInt(3, user.getRoleId());
			//ps.setInt(4, user.getStatus());

			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return -1;
	}
	private static final String GC_SQL_SELECT = 
			"SELECT id,username,password,role_id,1 AS status FROM users ";
	public User retrieve(int id) { // retrieve by id
		BaseDAO db = new BaseDAO(1);
		try {
			UserList ul = new UserList(GC_SQL_SELECT+" WHERE id=" + id);

			ul = (UserList) db.query(ul);

			return ul.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return null;
	}
	
	public User login(String un, String pw) {
		BaseDAO db = new BaseDAO(1);
		try {
			String sql = String.format(							 
					"SELECT id,username,password,role_id  FROM users WHERE username='%s' AND password"
							+ " = PASSWORD('%s')",un,pw);
			ResultSet rs =db.getResultSet(sql);

			if (rs==null || !rs.next())
				return null;
			
			//int id=rs.getInt(1);
			User u = new 
					User(rs.getInt(1), 
							rs.getString(2),
							rs.getString(3),
							 
					  rs.getInt(4),1);
			return u;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return null;
	}
	public User retrieve(String un) {
		BaseDAO db = new BaseDAO(2);
		try {
			UserList ul = new UserList(String.format(GC_SQL_SELECT+
					"WHERE username='%s'",un));

			ul = (UserList) db.query(ul);

			return ul.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return null;
	}

	public ArrayList<User> retrieve() {
		BaseDAO db = new BaseDAO(1);
		try {

			UserList ul = new UserList(GC_SQL_SELECT);
			ul = (UserList) db.query(ul);
			return ul.getList();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return null;
	}
	private static final String GC_GET_ENGINEERS = 
			"SELECT id, username, pin_code,1,1 FROM view_engineer_pin_code WHERE pin_code = '%s'";
	public UserList engineers(String pin) {

		BaseDAO db = new BaseDAO(1);
		try {

			UserList ul = new UserList(
					String.format(GC_GET_ENGINEERS, pin));
			ul = (UserList) db.query(ul);
			return ul;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return null;
	}
	private static final String GC_SQL_UPDATE = 
			"UPDATE users SET password=PASSWORD(?), role_id=? WHERE id=?";
	public int update(User user) {

		BaseDAO db = new BaseDAO(1);
		try {
			PreparedStatement ps = 
			db.prepare(GC_SQL_UPDATE);
			ps.setString(1, user.getPassword());
			ps.setInt(2, user.getRoleId());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
			return user.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			db.close();
		}

	}
	public int delete(int id) {
		BaseDAO db = new BaseDAO(2);
		try {
			db.execute("DELETE FROM users WHERE id = " + id);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			db.close();
		}
	}
	public  int save(User user) {
		if (user.getId() == 0)
			return create(user);
		else
			return update(user);
	}

	public  int saveProc(User user) { // create and update
		BaseDAO db = new BaseDAO(2);
		try {
			CallableStatement cs = db.prepareCall("usp_user_save(?,?,?,?,?,?,?,?)");
			cs.setInt(1, user.getId());
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());

			cs.setInt(4, user.getRoleId());
			cs.setInt(5, user.getStatus());

			cs.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			db.close();
		}
		return 0;
	}


}

