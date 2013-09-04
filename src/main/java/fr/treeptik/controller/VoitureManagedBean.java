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
import fr.treeptik.model.Voiture;
import fr.treeptik.service.VoitureService;

@ManagedBean(name="voitureMB")
@SessionScoped
public class VoitureManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value = "#{voitureService}")
	private VoitureService voitureService;
	
	
	private ListDataModel<Voiture> listDataModel;
	
	private Voiture voiture = new Voiture();
	
	private List<SelectItem> itemVoiture = new ArrayList<>();
	
	
	
	public String addVoiture() throws ServiceException{
		
		voitureService.update(voiture);
		voiture = new Voiture();
		
		return "list-voitures";
	}
	
	public String updateVoiture() throws ServiceException{
		voiture = listDataModel.getRowData();
		return "enregistrer-voiture";
	}
	
	public String deleteVoiture() throws ServiceException{
		
		voiture = listDataModel.getRowData();
		voitureService.remove(voiture);
		voiture = new Voiture();
		return "list-voitures";
		
	}
	
	public String listVoitures(){
		
		return "list-voitures";
	}
	
	public ListDataModel<Voiture> getVoitureList() throws ServiceException{
		
		listDataModel = new ListDataModel<Voiture>(voitureService.findAll());
		
		return listDataModel;
		
	}
	
	public Voiture getVoiture(){
		return voiture;
	}
	
	public void setVoiture (Voiture voiture){
		this.voiture = voiture;
	}
	
	public VoitureService getVoitureService(){
		
		return voitureService;
	}
	
	
	public void setVoitureService (VoitureService voitureService){
		this.voitureService = voitureService;
	}

	public List<SelectItem> getItemVoiture() throws ServiceException {
		
		List<Voiture> allVoitures = voitureService.findAll();
		
		for (Voiture voiture : allVoitures) {
			
			itemVoiture.add(new SelectItem(voiture.getId(), voiture.getMarque() + " - " + voiture.getModele()));
		}
		
		return itemVoiture;
	}

	public void setItemVoiture(List<SelectItem> itemVoiture) {
		this.itemVoiture = itemVoiture;
	}
	
	

}
