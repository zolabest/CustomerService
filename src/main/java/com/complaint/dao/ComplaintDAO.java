package com.complaint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.complaint.models.Complaint;
import com.complaint.models.User;

public class ComplaintDAO extends BaseDAO {
	public static void main(String[] args) {
		ComplaintDAO dao = new ComplaintDAO();
		/*
		Complaint c = new Complaint ();
		c.setAddress("123 Main");
		c.setId(0);
		c.setName("BT");
		c.setTelephone("456456");
		c.setStatus("Testing");
		c.setTypeOfProblem("top");
		dao.create(c);
		*/
		List<String>list=dao.getFeedback(1,1);
		for (String f:list)
			System.out.println(f);
		//System.out.println ((String)list.get(0));
		// int retval = dao.feedback(1, "feedback test");
		//int retval = dao.insertPinCode(2, "12346", 2);
		// System.out.println ("retval: "+retval);
		// dao.setStatus(1,"Resolced");
		/*
		 * List<Complaint> list = dao.retrieveForManagerId(1); for (Complaint c : list)
		 * { System.out.println(c); }
		 */
		/*
		 * Complaint c = new Complaint(); c.setAddress("123 Main"); c.setName("tester");
		 * c.setPinCode("12346"); c.setStatus("Active"); c.setTelephone("5551212");
		 * c.setTypeOfProblem("Won't start"); c.setUserId(1); dao.create(c);
		 */
	}

	private static String selectAll =
			"SELECT * FROM complaint";
	private static String selectByUserId = "SELECT * FROM complaint WHERE userid = %d";
	private static String selectActive = "SELECT * FROM complaint WHERE status='Active'";
	private static String selectByAssignee = "SELECT * FROM complaint WHERE assignee_id = %d";
	private static String selectByManager = "SELECT * FROM complaint_by_manager WHERE manager_id = %d";
	
	public List<String>getPins (int userId, int roleId)
	{
		List<String>list= new ArrayList<String>();
		String query="";
		if (roleId==3) // Manager
		{
			query ="SELECT * FROM `view_manager_pin_code` WHERE id="+userId;
		}else if(roleId==4)// Engineer
		{
			query ="SELECT * FROM `view_engineer_pin_code` WHERE id = "+userId;
		}
		try (Connection connection = getConnection();) {
			ResultSet rs = 
					connection.prepareStatement(query).executeQuery();
			// getResultSet(query);
			if (rs == null || !rs.next())
				return null;
			/*
			 * String name, String address, String pinCode, String telephone, String
			 * typeOfProblem, String status
			 */
			do {
				list.add(rs.getString(3));
			} while (rs.next());
			return list;
		} catch (SQLException e) {
			// System.err.println ("Feedback Error: "+e.getMessage())
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	public List<String>getFeedback (int id, int roleId)
	{
		List<String>list= new ArrayList<String>();
		String query="SELECT * FROM feedback WHERE complaint_id="+id;
		
		try (Connection connection = getConnection();) {
			ResultSet rs = 
					connection.prepareStatement(query).executeQuery();
			// getResultSet(query);
			if (rs == null || !rs.next())
				return null;
			/*
			 * String name, String address, String pinCode, String telephone, String
			 * typeOfProblem, String status
			 */
			do {
				list.add(rs.getString(3));
			} while (rs.next());
			return list;
		} catch (SQLException e) {
			// System.err.println ("Feedback Error: "+e.getMessage())
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	public int insertPinCode(int id, 
			String pinCode, int type) {
		int retval = 1;
		String insert = "";
		if (type == 3)// Manager
			insert = "INSERT INTO manager_pin_code (manager_id, pin_code)" + "VALUES (?,?)";
		else // Engineer
			insert = "INSERT INTO engineer_pin_code (engineer_id, pin_code)" + "VALUES (?,?)";
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(insert);

			ps.setInt(1, id);
			ps.setString(2, pinCode);
			ps.execute();
		} catch (SQLException e) {
			// System.err.println ("Feedback Error: "+e.getMessage())
			e.printStackTrace();
			return -1;
		} finally {
			close();
		}
		return retval;
	}

	public int feedback(int id, String comments) {
		System.out.println("FEEDBACK: " + comments);
		/*
		 * CREATE TABLE feedback ( id int primary key AUTO_INCREMENT, complaint_id INT,
		 * comments VARCHAR(4000), feedback_dt DATETIME )
		 * 
		 */
		int retval = 1;
		String insert = "INSERT INTO feedback (complaint_id, comments, "
				+ "feedback_dt)" + "VALUES (?,?, SYSDATE())";
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(insert);

			ps.setInt(1, id);
			ps.setString(2, comments);
			ps.execute();
		} catch (SQLException e) {
			// System.err.println ("Feedback Error: "+e.getMessage())
			e.printStackTrace();
			return -1;
		} finally {
			close();
		}
		return retval;
	}

	public int create(Complaint complaint) {
		int id = 0;
		String insert = "INSERT INTO complaint"
				+ " (id,userId,name,address,pin_code,"
				+ " phone,type_of_problem,  status)"
				+ " VALUES(?,?,?,?,?,?,?,?)";
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(insert);

			ps.setInt(1, complaint.getId());
			ps.setInt(2, complaint.getUserId());
			ps.setString(3, complaint.getName());
			ps.setString(4, complaint.getAddress());
			ps.setString(5, complaint.getPinCode());
			ps.setString(6, complaint.getTelephone());
			ps.setString(7, complaint.getTypeOfProblem());
			
			ps.setString(8,complaint.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return id;
	}

	public int assign(int complaintId, int assigneeId) {
		String update = "UPDATE complaint SET assignee_id = ? WHERE id=?";
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(update);

			ps.setInt(1, assigneeId);
			ps.setInt(2, complaintId);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 1;
	}

	public int setStatus(int complaintId, 
			String status) {
		String update = "UPDATE complaint SET status = ? WHERE id=?";
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(update);

			ps.setString(1, status);
			ps.setInt(2, complaintId);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 1;
	}

	public List<Complaint> retrieve() {
		return retrieve(selectAll);
	}

	public List<Complaint> retrieveActive() {
		return retrieve(selectActive);
	}

	public List<Complaint> retrieveForCustomerId(int id) {
		return retrieve(String.format(selectByUserId, id));
	}

	public List<Complaint> retrieveForAssigneeId(int id) {
		return retrieve(String.format(selectByAssignee, id));
	}

	public List<Complaint> retrieveForManagerId(int id) {
		return retrieve(String.format(selectByManager, id));
	}

	public Complaint retrieve(int id) {
		Complaint c = null;
		String query = "SELECT * FROM complaint WHERE id=" + id;
		try (Connection connection = getConnection();) {
			ResultSet rs = connection.prepareStatement(query).executeQuery();
			// getResultSet(query);

			if (rs == null || !rs.next())
				return null;
			/*
			 * String name, String address, String pinCode, String telephone, String
			 * typeOfProblem, String status
			 */

			c = new Complaint(rs.getString("name"), rs.getString("address"), rs.getString("pin_code"),
					// rs.getString("telephone"),
					"", rs.getString("type_of_problem"),

					rs.getString("status"));
			c.setId(rs.getInt(1));
			c.setUserId(rs.getInt(2));
			c.setAssigneeId(rs.getInt("assignee_id"));
			c.setTelephone(rs.getString("phone"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return c;
	}

	public List<User> getEngineersByPIN(String pin) {
		List<User> users = new ArrayList<User>();

		return users;
	}

	public List<Complaint> retrieve(String query) {
		List<Complaint> list = new ArrayList<Complaint>();

		try (Connection connection = getConnection();) {
			ResultSet rs = connection.prepareStatement(query).executeQuery();
			// getResultSet(query);
			if (rs == null || !rs.next())
				return null;
			/*
			 * String name, String address, String pinCode, String telephone, String
			 * typeOfProblem, String status
			 */
			do {
				Complaint c = new Complaint(rs.getString("name"), rs.getString("address"), rs.getString("pin_code"),
						// rs.getString("telephone"),
						"", rs.getString("type_of_problem"), rs.getString("status"));
				c.setId(rs.getInt(1));
				c.setUserId(rs.getInt(2));
				list.add(c);
			} while (rs.next());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int update(Complaint complaint) {
		int id = 0;

		String update = "UPDATE complaint SET type_of_problem = ?, address=?, pin_code=?, assignee_id=?, status=? WHERE id=?";
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(update);

			ps.setString(1, complaint.getTypeOfProblem());
			ps.setString(2, complaint.getAddress());
			ps.setString(3, complaint.getPinCode());
			ps.setInt(4, complaint.getAssigneeId());
			ps.setString(5, complaint.getStatus());
			ps.setInt(6, complaint.getId());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// return 1;

		return id;
	}

	public boolean delete(int id) {
		String delete = "DELETE FROM complaint WHERE id = " + id;
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection.prepareStatement(delete);

			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}
};
