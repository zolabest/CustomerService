package com.complaint.service;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.complaint.dao.BaseDAO;
import com.complaint.dao.ComplaintDAO;
import com.complaint.models.Complaint;
import com.complaint.models.User;


public class ComlaintService {
	ComplaintDAO dao = new ComplaintDAO();

	public static void main(String[] args) {
		
		
		ComlaintService fs = new ComlaintService();
		List<Complaint> list= fs.retrieve();
		System.out.println(list.get(0));
		
		//List<Flight> flights = fs.search("", "", "", 0);
		for (Complaint f : list) {
			System.out.println(f);
		}
		
	}
	
	public ComlaintService() {

	}
	public List<String>getPins (int userId, int roleId)
	{
		return dao.getPins(userId, roleId);
	}
	public int addPin(int userId, int roleId, String pin)
	{
		dao.insertPinCode(userId, pin, roleId);
		return 1;
	}
	public Complaint retrieve(int id, User user) {
		Complaint c= dao.retrieve(id);
		if (user.getRoleId()==1||user.getRoleId()==3)
			return c;
		else if (c.getAssigneeId()==user.getId())
			return c;
		else if (c.getUserId()==user.getId())
			return c;
		else 
			return null;
		
	}
	public int create(Complaint complaint)
	{
		return dao.create(complaint);
	}
	public int update(Complaint complaint)
	{
		return dao.update(complaint);
	}
	public boolean delete (int id)
	{
		
		return dao.delete(id);
	}
	public int assign (int complaintId, int assigneeId)
	{
		
		return dao.assign(complaintId, assigneeId);
	}
	public int setStatus(int complaintId, 
			String status) 
	{
		return dao.setStatus(complaintId, status);
	}
		
	public List<Complaint>retrieve()
	{
		return dao.retrieve();
	}
	public List<Complaint> retrieveActive() {
		return dao.retrieveActive();
	}
	public List<Complaint>retrieveForCustomerId(int id)
	{
		return dao.retrieveForCustomerId(id);
	}
	public List<Complaint>retrieveForAssigneeId(int id)
	{
		return dao.retrieveForAssigneeId(id);
	}
	public List<Complaint> retrieveForManagerId(int id) {
		return dao.retrieveForManagerId(id);
	}
	public int insertPinCode (int id, 
			String pinCode, int type)
	{
		return dao.insertPinCode(id, pinCode, type);
	}
	public int feedback(int id, String comments, User user) {
		return dao.feedback(id, comments);
	}
	public List<String>getFeedback (int id, int roleId)
	{
		return dao.getFeedback(id, roleId);
	}
}
