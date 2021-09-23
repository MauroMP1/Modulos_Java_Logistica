package com.services;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dominio.Usuario;


@Named("cusu")
@ViewScoped
//@NamedEvent(shortName="cus")
public class controlUsu  implements Serializable {
	
	
	

	Usuario usu;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void verificarSession() { 
		
		System.out.println("Verifico");
		FacesContext context = FacesContext.getCurrentInstance();
		usu = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		try {
							
			if(usu == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion!",
						  "Debe ingresar con usuario"));
			}
			
		}catch (Exception ej){
			System.out.println(ej);			
		}
		
		
	}
	
	public String logout (){
		usu = null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "index.jsf";
	}
	
	
	
}
