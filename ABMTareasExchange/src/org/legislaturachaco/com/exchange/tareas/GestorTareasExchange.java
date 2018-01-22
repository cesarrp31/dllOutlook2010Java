package org.legislaturachaco.com.exchange.tareas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;

/**
 * 
 * @author A.U.S. Peralta, Cesar Omar
 *
 */

public class GestorTareasExchange {
	private IGestorExchange ge;
	private IGestorTareas gt;
	
	private final String version= "1.0";
	
	public String getVersion() {
		return version;
	}

	private static final String nombreDLL= "Outlook2010Csharp", 
						separador = File.separator;
	
	private String pathCompleto= System.getProperty("user.dir") +separador+"lib"+separador;
	
	private String arqOS, usuario= "", contrasena;
	
	/**
	 * Constructor para el gestor de tareas en el exchange 2003.
	 * @param arqOS Arquitectura obtenida a traves del método "System.getProperty("os.arch")".
	 */
	public GestorTareasExchange(String arqOS){		
		cargarDLL(arqOS);
	}
	
	/**
	 * Constructor para el gestor de tareas en el exchange 2003. Por defecto la arquitectura es "amd64".
	 * @param usuario Un usuario para obtener sus tareas.
	 * @param contrasena Una contraseña de usuario.
	 */
	public GestorTareasExchange(String usuario, String contrasena){
		this("amd64", usuario, contrasena);		
	}
	
	/**
	 * Constructor para el gestor de tareas en el exchange 2003.
	 * @param arqOS Arquitectura obtenida a traves del método "System.getProperty("os.arch")".
	 * @param usuario Un usuario para obtener sus tareas.
	 * @param contrasena Una contraseña de usuario.
	 */
	public GestorTareasExchange(String arqOS, String usuario, String contrasena){
		this.setCredenciales(usuario, contrasena);
		
		cargarDLL(arqOS);
		
		controlUsuarioInexistente();
		getGestorExchange().setCredencialesUsuario(usuario, contrasena);
	}
	
	private void cargarDLL(String arqOS){
		this.arqOS= arqOS;
		pathCompleto+= (this.arqOS.equals("amd64")?"dll64":"dll32")+separador;		
		String pathDLL= pathCompleto+nombreDLL;		
		File midll = new File(pathDLL + ".dll");		
		System.out.println("Dll a cargar: "+midll.getAbsolutePath());		
		System.load(midll.getAbsolutePath());
		
		gt= (IGestorTareas) Native.loadLibrary(pathDLL, IGestorTareas.class);
		ge= (IGestorExchange) Native.loadLibrary(pathDLL, IGestorExchange.class);
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
	
	IGestorTareas getGestorTareas(){
		return gt;
	}
	
	public List<ITareaExchange> getListaTareas(){
		List<ITareaExchange> lista= new ArrayList<>();
		int c= getGestorTareas().getTotalTareas();
        for(int i= 0; i < c; i++){                
        	lista.add(crearTarea(i));
        }
		return lista;
	}
	
	public List<ITareaExchange> getListaTareasNoTerminadas(){
		List<ITareaExchange> lista= new ArrayList<>();
		int c= getGestorTareas().getTotalTareas();
        for(int i= 0; i < c; i++){
        	if(getGestorTareas().isTareaCompletada(i)) continue;
            lista.add(crearTarea(i));
        }
		return lista;
	}
	
	private TareaExchange crearTarea(int i){
		return new TareaExchange(i, getGestorTareas());
	}
	
	public int getTotalTareasUsuario(){
		return getGestorTareas().getTotalTareas();
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
			GestorTareasExchange gte= new GestorTareasExchange("amd64");
			gte.setCredenciales("coperalta", "");
			gte.ge.actualizarListaTareas();
			
			System.out.println(gte.getGestorExchange().getNombreGestor());
			System.out.println(gte.getGestorTareas().toString());
			System.out.println(gte.getGestorTareas().getTotalTareas());
			
			List<ITareaExchange> lista= gte.getListaTareas();
			
			String r;
			for(ITareaExchange te: lista){
				r= te.getCuerpoTarea();
				System.out.println(r);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}