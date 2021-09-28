package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.dominio.Usuario;
import com.google.gson.Gson;


@Named("usu")
@SessionScoped
@NamedEvent
public class usuarios implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuNombre;
	private String usuPassword;
	private Usuario usu;
	
	


	public String getUsuNombre() {
		return usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuPassword() {
		return usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}




	public String confirmarusuario() {

		Usuario usua = new Usuario();
		usua = obtenerUsuario(usuNombre);
		usu = usua;
		//System.out.println(usua.getUsuNombre() + "  " + usua.getUsuPassword() );
		String nom, pass;
		
		String retur = null;
		if (usua==null) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion!",
					  "Usuario y/o Contraseña Incorrecta"));
		
			retur =  "index.xhtml";
		}else {
			nom = usua.getUsuNombre();
			pass = usua.getUsuPassword();
			if (usuNombre.equals(usua.getUsuNombre()) && usuPassword.equals(usua.getUsuPassword())) {
				if(usua.getPerfile().getPerfNombre().equals("Admin")) {
					retur = "ABMProducto.xhtml";
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usua);
				}
				if(usua.getPerfile().getPerfNombre().equals("Supervisor")) {
					retur = "ABMMovimiento.xhtml";
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usua);
				}
				if(usua.getPerfile().getPerfNombre().equals("Operador")) {
					retur = "reporteAlmacen.xhtml";
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usua);
				}
				
				
			}else{
					System.out.println("Mal ingresado los datos");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion!",
							  "Usuario y/o Contraseña Incorrecta"));
			}
		}
		return retur;
	}
	
		
	

	public static String readJsonFromUrl(String url){

	    InputStream is = null;
		try {
			is = new URL(url).openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	     
	      String jsonText = null;
		try {
			jsonText = readAll(rd);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	     
	      return jsonText;
	    } finally {
	      try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	  }

	private static String readAll(Reader rd) throws IOException {

	    StringBuilder sb = new StringBuilder();
	    int cp;
	    System.out.println(rd.toString());
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	      
	    }
	    return sb.toString();
	 }
	
	/// Verifico que este bien ingresado usuario y contraseña
	
	public Usuario obtenerUsuario (String nom) {
		//URL servidor
		String urlusu = "http://dominio.ddns.net:8080/ProyectoRest/rest/logeo/";
		Gson gson = new Gson();
		Usuario usu = gson.fromJson(readJsonFromUrl(urlusu+nom), Usuario.class);
		return usu;
	}
	

	public String getUserN () {
		String s = usu.getUsuNombre() + " " + usu.getUsuApellido();
		System.out.println(s);
		return s;
	}
	
	public String getUserP() {
		String s = usu.getPerfile().getPerfNombre();
		System.out.println(s);
		return s;
		
	}
	public String getUserC() {
		String s = usu.getUsuCorreo();
		System.out.println(s);
		return s;
		
	}
	
	public void ContactMensaje() {
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contacto", "\n"+"Por cualquier consulta diríjase a"+"\n"+" mauro.mp@outlook.com");
         
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	}
	public void ContactNosotros() {
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nosotros", "\n"+"estamos, contactenos");
        
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	}

}


