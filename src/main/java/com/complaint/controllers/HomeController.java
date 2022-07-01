package com.complaint.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.complaint.dao.ComplaintDAO;
import com.complaint.dao.UserDAO;
import com.complaint.dao.UserList;
import com.complaint.models.Complaint;
import com.complaint.models.User;
import com.complaint.service.ComlaintService;
import com.complaint.service.UserService;

@Controller
public class HomeController {
	ComlaintService complaintService = new ComlaintService();
	UserService userService = new UserService();
	@GetMapping ("/")
	public String index(Model model)
	{
	//System.out.println ("home");	
		//User user = BaseDAO.GetUser(1);
		//ArrayList<User> list = UserDAO.retrieve();
	//	model.addAttribute("userList", list);
		return "redirect:dashboard";
	}
	
	@GetMapping ("/search")
	public String searchPage(Model model)
	{
	//System.out.println ("home");	
		//User user = BaseDAO.GetUser(1);
		
		return "search";
	}
	
	@GetMapping ("/users")
	public String users(Model model,
			HttpServletRequest request)
	{
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		if(user.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
	//System.out.println ("home");	
		//User user = BaseDAO.GetUser(1);
		ArrayList<User> list = userService.retrieve();
		model.addAttribute("userList", list);
		return "users";
	}
	@GetMapping (path="engineers/{pin}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserList engineers (Model model, 
			@PathVariable(value="pin") 
			String pin)
	{
		UserList list = userService.engineers(pin);
		
		return list;
	}
	
	@GetMapping ("/user/{id}")
	public String user(
			Model model,HttpServletRequest request,
			@PathVariable(value="id") 
			String sid)
	{
	//System.out.println ("home");	
		int id=parseInt(sid,-1);
		//User user = BaseDAO.GetUser(1);
		User adminUser = (User)request.getSession().getAttribute("user");
		if(adminUser==null)
			return "redirect:/login";
		if(adminUser.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		User user;
		if(id==-1)
			user=userService.retrieve(sid);
		else if(id>0)
			user=userService.retrieve(id);
		else
			user = new User();
		
		
		model.addAttribute("user", user);
		//return user==null?"User ["+sid+": not found]":user.toString();
		if(user.getRoleId()>2)
		{
			List<String>pins = 
					complaintService.getPins(
							user.getId(),
							user.getRoleId());
			model.addAttribute("pins", pins);
		}
		return "user";
		
		//return user.toString();
	}
	@GetMapping ("/addPin/{uid}/{rid}/{pin}")
	@ResponseBody()
	public String  addPin (Model model, 
			HttpServletRequest request,
			@PathVariable(value="uid") String uid,
			@PathVariable(value="rid") String rid,
			@PathVariable(value="pin") String pin
			)
	{
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		if(user.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		int retval=complaintService.addPin(Integer.parseInt(uid), Integer.parseInt(rid), pin);
		
		return "status: "+retval;
	}
	@GetMapping ("/complaint/{id}")
	public String complaint(Model model, 
			HttpServletRequest request,
			@PathVariable(value="id") String sid)
	{
	//System.out.println ("home");	
		int id=parseInt(sid,-1);
		//User user = BaseDAO.GetUser(1);
		
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		/*
		if(user.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		*/
		model.addAttribute("user", user);
		Complaint c =complaintService.retrieve(id, user);
		if (c==null)
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		
		model.addAttribute("complaint", c);
		List<String>feedback=complaintService.getFeedback(c.getId(),
				user.getRoleId());
		model.addAttribute("feedback",feedback.get(0));
		//return user==null?"User ["+sid+": not found]":user.toString();
		if(user.getRoleId()==1||user.getRoleId()==3)
			return "editComplaint";
		else
			return "complaint";
	}
	@GetMapping ("/deleteComplaint/{id}")
	public String deleteComplaint(Model model, 
			HttpServletRequest request,
			@PathVariable(value="id") String sid)
	{
	//System.out.println ("home");	
		int id=parseInt(sid,-1);
		//User user = BaseDAO.GetUser(1);
		
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		if(user.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		complaintService.delete(id);
		return "redirect:/dashboard";
	}
	@GetMapping ("/deleteUser/{id}")
	
	public String deleteUser(Model model, 
			HttpServletRequest request,
			@PathVariable(value="id") String sid)
	{
	//System.out.println ("home");	
		int id=parseInt(sid,-1);
		//User user = BaseDAO.GetUser(1);
		
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		if(user.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		int status = userService.delete(id);
		return "redirect:dashboard";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model)
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
			HttpServletRequest request,Model model, 
			@RequestParam String username,
			@RequestParam String password)
	{
		User user = userService.login(username, password);
		if (user==null)
		{
			model.addAttribute("message", "incorrect login");
			return "login";
		}
		System.out.println(user);
		model.addAttribute("user", user);
		request.getSession().setAttribute("user", user);
		return "dashboard"+user.getRoleId();
	}
	@PostMapping("/feedback")
	@ResponseBody()
	public String feedback(
			HttpServletRequest request,Model model, 
			@RequestParam String complaintId,
			@RequestParam String feedback)
	{
		User user = (User)request.getSession().getAttribute("user");

		if (user==null)
		{
			model.addAttribute("message", "incorrect login");
			return "login";
		}
		System.out.println(user);
		complaintService.feedback(Integer.parseInt(complaintId), feedback, user);
		
		return "done";
	}
	@GetMapping("/create")
	public String newCompalintForm(HttpServletRequest request,Model model)
	{
		User user = (User)request.getSession().getAttribute("user");
		
		if (user==null)
		{
			model.addAttribute("message", "user not logged in");
			return "login";
		}
		model.addAttribute("user", user);
		Complaint c = new Complaint();
		c.setUserId(user.getId());
		c.setName(user.getUsername());
		c.setStatus("Raised");
		model.addAttribute("complaint", c);
		return "complaint";
	}
	@GetMapping("/userComplaints")
	public String userComplaints(
			HttpServletRequest request,
			Model model)
	{
		User user = (User)request.getSession().getAttribute("user");
		
		if (user==null)
		{
			model.addAttribute("message", "user not logged in");
			return "login";
		}
		model.addAttribute("user", user);
		List<Complaint>complaints = null;
		switch (user.getRoleId()) {
		case 1: // Admin
			complaints = complaintService.retrieve();
			break;
		case 2: // Customer
			complaints = 
			complaintService.retrieveForCustomerId(
					user.getId());
			break;
		case 3: // Manager
			complaints = 
			complaintService.retrieveForManagerId(user.getId());
			break;	
		case 4: // Engineer.
			complaints = 
			complaintService.retrieveForAssigneeId(user.getId());
			break;			
		}
		model.addAttribute("complaints", complaints);
		return "dashboard"+user.getRoleId();
	}
	@GetMapping("/logout")
	public String logout(
			HttpServletRequest request,
			Model model)
	{
		request.getSession().setAttribute("user", null);
		return "login";
	}
	@GetMapping("/dashboard")
	public String dashboard(
			HttpServletRequest request,
			Model model)
	{
		User user = (User)request.getSession().getAttribute("user");
		
		if (user==null)
		{
			model.addAttribute("message", "user not logged in");
			return "login";
		}
		model.addAttribute("user", user);
		List<Complaint>complaints = null;
		switch (user.getRoleId())
		{
		case 1: //admin
			complaints = complaintService.retrieve();
			break;
		case 2: // customer
			complaints = complaintService.retrieveForCustomerId(user.getId());
			break;
		case 3: // manager
			complaints = 
			complaintService.retrieveForManagerId(user.getId());
			break;
		case 4: // engineer
			complaints = 
			complaintService.retrieveForAssigneeId(user.getId());
			break;	
		}
		
		model.addAttribute("complaints", complaints);
		return "dashboard"+user.getRoleId();
	}
	@PostMapping("/saveComplaint")
	public String saveComplaint
	(@ModelAttribute("complaint")Complaint c, 
			HttpServletRequest request,
			Model model)
	{
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		System.out.printf ("Create Complaint: %s", c);
		if(c.getId()==0)
			complaintService.create(c);
		else {
			if(user.getRoleId()==1||user.getRoleId()==3)
				complaintService.update(c);
			else 
				complaintService.setStatus(c.getId(), c.getStatus());
		}
		return "confirm";
	}
	@GetMapping ("/newUser")
	public String complaint(Model model, 
			HttpServletRequest request)
	{
	//System.out.println ("home");	
		
		//User user = BaseDAO.GetUser(1);
		
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
			return "redirect:/login";
		if(user.getRoleId()!=1) // not admin
		{
			model.addAttribute("message", "not authorized");
			return "message";
		}
		model.addAttribute("user", new User());
		return "user";
	}
	@PostMapping("/saveUser")
	public String saveuser 
	(@ModelAttribute("user")User user, Model model)
	{
		
		System.out.printf ("SAVE: %s", user);
		int status=userService.save(user);
		//return users(model);
		return "redirect:/dashboard";
	}
	@GetMapping("/message")
	@ResponseBody()
	public String message()
	{
		return "hello world!!!";
	}
	public static int parseInt (String val, int def)
	{
		try {
			return Integer.parseInt(val);
		}catch (Exception e) {
			return def;
		}
	}
}