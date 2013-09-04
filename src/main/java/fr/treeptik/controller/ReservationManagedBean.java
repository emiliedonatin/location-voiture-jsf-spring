package fr.treeptik.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.ReservationService;

@ManagedBean(name="reservationMB")
@SessionScoped
public class ReservationManagedBean implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value= "#{reservationService}")
	private ReservationService reservationService;
	
	private ListDataModel<Reservation> listDataModel;
	
	private Reservation reservation;
	
	public ReservationManagedBean() {
		reservation = new Reservation();
		reservation.setClient(new Client());
		reservation.setVoiture(new Voiture());
	}
	
	
	public String addReservation() throws ServiceException{
		
		reservationService.update(reservation);
		reservation = new Reservation();
		
		return "list-reservations";
	
	}
	
	public String updateReservation() throws ServiceException{
		
		reservation = listDataModel.getRowData();
		
		return "enregistrer-reservation";
	}
	
	
	public String deleteReservation() throws ServiceException{
		reservation = listDataModel.getRowData();
		reservationService.remove(reservation);
		return "list-reservations";
	}
	
	public String listReservations(){
		return "list-reservations";
	}
	
	public ListDataModel<Reservation> getReservationList() throws ServiceException{
		listDataModel = new ListDataModel<Reservation>(reservationService.findAll());
		return listDataModel;
	}
	
	public Reservation getReservation(){
		return reservation;
	}
	
	public void setReservation (Reservation reservation){
		this.reservation = reservation;
	}
	
	public ReservationService getReservationService(){
		return reservationService;
	}
	
	public void setReservationService (ReservationService reservationService){
		this.reservationService = reservationService;
	}

}
