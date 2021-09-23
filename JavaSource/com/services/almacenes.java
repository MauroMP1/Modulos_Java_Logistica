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
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dominio.Almacenamiento;
import com.google.gson.Gson;

@Named("almas")
@ViewScoped
@NamedEvent
public class almacenes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url = "http://dominio.ddns.net:8080/ProyectoRest/rest/alma/";
	private String idi = "";
	private String idf = "";
	private List<Almacenamiento> listalmasb = new ArrayList<Almacenamiento>();
	private boolean vacio= false;
	
	
	
	public String getIdi() {
		return idi;
	}

	public void setIdi(String idi) {
		this.idi = idi;
	}

	public String getIdf() {
		return idf;
	}

	public void setIdf(String idf) {
		this.idf = idf;
	}

	
	
	public List<Almacenamiento> getListalmasb() {
		return listalmasb;
	}

	public void setListalmasb(List<Almacenamiento> listalmasb) {
		this.listalmasb = listalmasb;
	}

	public List<Almacenamiento> almacenamientos (String idi1, String idf1){
		
		List<Almacenamiento> lista = new ArrayList<Almacenamiento>();
		Gson gson = new Gson();
		Almacenamiento[] ralmas = gson.fromJson(readJsonFromUrl(url, idi1, idf1), Almacenamiento[].class);
		for (Almacenamiento m: ralmas) {
			   lista.add(m);
			}
		return lista;
		
	}
	
	public List<Almacenamiento> buscar (){
		
	
		List<Almacenamiento> ralmas = new ArrayList<Almacenamiento>();
		System.out.println(idi);
		System.out.println(idf);
		
		if (idi.equals("")&&idf.equals("")) {
		
		ralmas = almacenamientos("1", "99999");
		FacesContext.getCurrentInstance().addMessage(null, new
				  FacesMessage(FacesMessage.SEVERITY_INFO, "Los ids están vacíos, por favor ingrese un rango de ids para buscar",
				  ""));
        }
		
		
		if (idi.equals("")&&!idf.equals("")) {
					
			ralmas = almacenamientos("1", idf);
			FacesContext.getCurrentInstance().addMessage(null, new
			FacesMessage(FacesMessage.SEVERITY_INFO, "Uno de los ids está vacío, por favor ingrese un rango de ids para buscar",
					  ""));
	        }

		if(!idi.equals("")&&idf.equals("")) {
					
			ralmas = almacenamientos(idi, "99999");
			FacesContext.getCurrentInstance().addMessage(null, new
					FacesMessage(FacesMessage.SEVERITY_INFO, "Uno de los ids está vacío, por favor ingrese un rango de ids para buscar",
							  ""));
		}
		
		if(!idi.equals("")&&!idf.equals("")) {
			Long idin = Long.parseLong(idi);
			Long idif = Long.parseLong(idf);
			if(idin>idif) {
				FacesContext.getCurrentInstance().addMessage(null, new
						FacesMessage(FacesMessage.SEVERITY_INFO, "El id inicial debe ser menor que el id final",
								  ""));
				vacio = true;
			}else {					
			ralmas = almacenamientos(idi, idf);
			if(ralmas.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new
						FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron almacenes en el rango enviado",
								  ""));
				vacio = true;
				
			}
			}
		}
		
		
		listalmasb = ralmas; 
		idi = "";
		idf = "";
		return listalmasb;
	}
	
	
	public List<Almacenamiento> getAlmas (){
		
		/*
		 * if(vacio==true) { vacio = false; return listalmasb; }else {
		 * if(listalmasb.isEmpty()) { listalmasb = almacenamientos("0","99999"); return
		 * listalmasb; }else { return listalmasb; } }
		 */
		return listalmasb;
	}
	
	
		
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	      
	    }
	    return sb.toString();
	  }
	public static String readJsonFromUrl(String url, String id1, String id2){
		
	    InputStream is = null;
		try {
			is = new URL(url+id1+"/"+id2).openStream();			
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
	


}
	
	


