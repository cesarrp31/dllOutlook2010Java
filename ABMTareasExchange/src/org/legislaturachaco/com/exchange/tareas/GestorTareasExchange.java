package org.legislaturachaco.com.exchange.tareas;

import java.io.File;

import com.sun.jna.Native;

public class GestorTareasExchange {
	private IGestorExchange ge;
	private IGestorTareas gt;
	
	private static final String nombreDLL= "Outlook2010Csharp", 
						separador = File.separator;
	
	private static String pathCompleto= "lib"+separador;
	
	private String arqOS, usuario= "", contrasena;
	
	public GestorTareasExchange(String usuario, String contrasena){
		this("amd64", usuario, contrasena);		
	}
	
	public GestorTareasExchange(String arqOS, String usuario, String contrasena){
		this.arqOS= arqOS;
		this.setCredenciales(usuario, contrasena);
		
		pathCompleto+= (this.arqOS.equals("amd64")?"dll64":"dll32")+separador;		
		String pathDLL= pathCompleto+nombreDLL;
		
		final File midll = new File(pathDLL + ".dll");
		System.load(midll.getAbsolutePath());
		
		gt= (IGestorTareas) Native.loadLibrary(pathDLL, IGestorTareas.class);
		ge= (IGestorExchange) Native.loadLibrary(pathDLL, IGestorExchange.class);
		
		controlUsuarioInexistente();
		getGestorExchange().setCredencialesUsuario(usuario, contrasena);
	}
	
	private void controlUsuarioNulo(){
		if((usuario==null)||(usuario.equals(""))) 
			throw new RuntimeException("Nombre de usuario inválido");		
	}
	
	private void controlUsuarioInexistente(){
		getGestorExchange().setCredencialesUsuario(usuario, contrasena);
		if(getGestorExchange().existeError())
			throw new RuntimeException(getGestorExchange().msgError());
		
		//System.out.println("hola1 " + this.usuario + this.getGestorExchange().existeError());

		getGestorExchange().actualizarListaTareas();
		//System.out.println("hola2 " + this.usuario + this.getGestorExchange().existeError());

		if(getGestorExchange().existeError())
			throw new RuntimeException(getGestorExchange().msgError());
		//System.out.println("hola3 " + this.usuario + this.getGestorExchange().existeError());
	}
	
	public IGestorExchange getGestorExchange(){
		return ge;
	}
	
	public IGestorTareas getGestorTareas(){
		return gt;
	}
	
	public void setCredenciales(String usuario, String contrasena){
		this.usuario= usuario;
		controlUsuarioNulo();		
		this.contrasena= contrasena;
		
		if(getGestorExchange()!=null){
			getGestorExchange().setCredencialesUsuario(usuario, contrasena);
			controlUsuarioInexistente();
		}	
	}
	
	public static void main(String[] args) {
		try{
			GestorTareasExchange gte= new GestorTareasExchange("cfoperalta", "");
			
			System.out.println(gte.getGestorExchange().getNombreGestor());
			System.out.println(gte.getGestorTareas().toString());
			System.out.println(gte.getGestorTareas().getTotalTareas());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
