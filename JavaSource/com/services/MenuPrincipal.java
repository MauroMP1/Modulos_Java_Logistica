package com.services;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.dominio.Usuario;

@Named("menu")
@SessionScoped
@NamedEvent
public class MenuPrincipal implements Serializable{

	/**
	 * 
	 */
	Usuario usu;
	private static final long serialVersionUID = 1L;
	
	
 
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

		
	
	public String AbmProd(){
		String link = "index.xhtml";
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			usu = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			System.out.println(usu.getPerfile().getPerfNombre());
			if(usu.getPerfile().getPerfNombre().equals("Admin")) {
				link = "ABMProducto.xhtml";
				//context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion!",
					//	  "No tiene permiso para esta función"));
			}else {
				if(usu.getPerfile().getPerfNombre().equals("Supervisor")) {
				link = "ABMMovimiento.xhtml";
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención!",
						"No tiene permiso para la función productos"));
			}
				if(usu.getPerfile().getPerfNombre().equals("Operador")) {
					link = "reporteAlmacen.xhtml";
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención!",
							"No tiene permiso para la función productos"));					
				}
			}		
			
		}catch (Exception e){
			
		}
		return link;
	}
	
	

	public String abmMovi(){
		String link = "index.xhtml";
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			usu = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			System.out.println(usu.getPerfile().getPerfNombre());
		if(usu.getPerfile().getPerfNombre().equals("Supervisor")||usu.getPerfile().getPerfNombre().equals("Admin")) {
			link = "ABMMovimiento.xhtml";
			//context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion!",
				//	  "No tiene permiso para esta función"));
		}else {
			link = "reporteAlmacen.xhtml";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención!",
					"No tiene permiso para la función Movimientos"));
		}
		}catch (Exception e){
			
		}
		return link;
	}
	
	public String reporte() {
		String link = "index.xhtml";
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			usu = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			System.out.println(usu.getPerfile().getPerfNombre());
	
		if(usu.getPerfile().getPerfNombre().equals("Operador")||usu.getPerfile().getPerfNombre().equals("Supervisor")||usu.getPerfile().getPerfNombre().equals("Admin")) {
			link = "reporteAlmacen.xhtml";
			//context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion!",
				//	  "No tiene permiso para esta función"));
		}
		}catch (Exception e){
			
		}
		return link;
	}
	

}
