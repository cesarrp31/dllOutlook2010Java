package org.legislaturachaco.com.exchange.tareas;

import com.sun.jna.Library;

public interface IGestorExchange extends Library{
	
	public static final int NO_EXISTE_ERROR = 0;
	public static final int USUARIO_NO_ENCONTRADO = 1;
	public static final int OTRO_ERROR = 10;
	
	public Boolean existeError();
    
    public String msgError();
    
    public String claseError();
    
    public int codigoError();
    
    public String getVersionDLL();
	
	public boolean setCredencialesUsuario(String usuario, String contrasena);
	
	public void actualizarListaTareas();
	
	public String getNombreUsuario();
	
	public String getNombreGestor();
		
	public String getVersionGestor();
	
	//public IGestorTareas getGestorTareas();
	
	public String getCategoriasTarea();
	
	//public boolean getSetUsuario(String usuario, String contrasena);
}
