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
import fr.treeptik.model.Client;
import fr.treeptik.service.ClientService;

@ManagedBean(name= "clientMB")
@SessionScoped
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value= "#{clientService}")
	private ClientService clientService;
	
	private ListDataModel<Client> listDataModel;
	
	private Client client = new Client();
	
	private List<SelectItem> itemClient = new ArrayList<>();
	
	
	public String addClient() throws ServiceException{
		
		clientService.update(client);
		client = new Client();
		return "list-clients";
		
	}
	
	public String updateClient() throws ServiceException{
		
		client = listDataModel.getRowData();
		
		return "enregistrer-client";
	}
	
	public String deleteClient() throws ServiceException{
		client = listDataModel.getRowData();
		getClientService().remove(client);
		client = new Client();
		return "list-clients";
	}
	
	public String listClients(){
		return "list-clients";
	}
	
	public ListDataModel<Client> getClientList() throws ServiceException{
		listDataModel = new ListDataModel<Client>(getClientService().findAll());
		return listDataModel;
	}

	public Client getClient(){
		return client;
	}
	
	public void setClient (Client client){
		this.setClientService(clientService);
	}
	
	public List<SelectItem> getItemClient() throws ServiceException{
		List<Client> allClients = getClientService().findAll();
		
		for (Client client : allClients) {
			itemClient.add(new SelectItem(client.getId(), client.getNom() + " - " + client.getPrenom() + " - " + client.getMail()));
			
		}
		return itemClient;
	}


	public void setItemClient(List<SelectItem> itemClient){
		this.itemClient = itemClient;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}











}
