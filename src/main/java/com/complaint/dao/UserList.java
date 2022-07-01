package com.complaint.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.complaint.models.User;

public class UserList extends BaseList{
	ArrayList<User>list;
	public UserList() {list = new ArrayList<User>();}
	public UserList(String query) {
		this.query=query;
		list = new ArrayList<User>();
	}
	public void add(ResultSet rs)
	{
		try {
			if (rs==null||!rs.next())
			{
				return;
			}
/*
 * 
 int id, String username,
			String password,  
			int roleId, int status
 */
			do{
				User u = new 
						User(rs.getInt(1), 
								rs.getString(2),
								rs.getString(3),
								 
						  rs.getInt(4),
						  rs.getInt(5));
				list.add (u);
			}while (rs.next());
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public ArrayList<User>getList(){return list;}
	public User get(int idx)
	{
		try {
			if(list==null||list.size()<=idx)
				return null;
		
			return list.get(idx);
		}catch (Exception ex) {
			System.err.printf("UserList.getList(%d): size: %d ", idx, size());
			ex.printStackTrace();
			return null;
		}
	}
	public int size() {return list==null?0:list.size();}
}
