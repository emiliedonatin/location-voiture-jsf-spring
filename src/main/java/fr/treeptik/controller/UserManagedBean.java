package fr.treeptik.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.User;
import fr.treeptik.service.UserService;

//@Component(value = "userMB")
//@Scope(value = "request")
@ManagedBean(name="userMB")
@SessionScoped
public class UserManagedBean implements Serializable {	
//	managedBean est l'équivalent du controller dans SpringMVC

	private static final long serialVersionUID = 1L;

//	@Autowired
//	private UserService userService;

//	on injecte le userService  
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	
//	permet de récupere tous les objets contenus dans un tableau
	private ListDataModel<User> listDataModel;

	private User user = new User();
	
	private List<SelectItem> itemUser = new ArrayList<>();

	
	
//	cette methode est appelée directement depuis la page pour ajouter directement un utilisateur (on appelle pas d'URL)
	public String addUser() throws Exception {

		userService.addUser(user);

		return "list-users";
	}

	public String deleteUser() throws Exception{
		
		user = listDataModel.getRowData();
		
		userService.deleteUser(user.getId());
		
		return "list-users";
	}
	
	
	
	public String listUsers() {
//		la méthode va aller directement à la page list-users.xhtml
		return "list-users";
	}

	public String reset() {
		this.setUser(new User());
		return "list-users";
	}

	
	
	
	public ListDataModel<User> getUserList() throws Exception {
		
		listDataModel = new ListDataModel<User>(userService.getAllUsers());
		
		return listDataModel;
	}

	
	
	public User getUser() {
		return user;
	}

	
	
	public void setUser(User user) {
		this.user = user;
	}

	
	
	public UserService getUserService() {
		return userService;
	}

	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public List<SelectItem> getItemUser() throws ServiceException{
		List<User> allUsers = userService.getAllUsers();
		
		for (User user : allUsers) {
			itemUser.add(new SelectItem(user.getId(), user.getFirstName() + " - " + user.getLastName()));
		}
		return itemUser;
	}
	
	public void setItemUser(List<SelectItem> itemUser){
		this.itemUser = itemUser;
	}

}