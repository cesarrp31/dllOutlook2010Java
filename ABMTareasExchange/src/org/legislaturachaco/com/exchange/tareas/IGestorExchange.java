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
	
    /**
     * Setea el usuario y contrase�a (si es necesario). Hay controlar si se produce un error 
     * con el m�todo existeError() al no encontrar el usuario.
     * @param usuario Nombre de usuario
     * @param contrasena Contrase�a de usuario; puede ser vac�o.
     * @return 
     */
	public boolean setCredencialesUsuario(String usuario, String contrasena);
	
	/**
	 * Se realiza la busqueda de las tareas para el usuario seteado con el m�todo "setCredencialesUsuario".
	 * Hay controlar si se produce un error con el m�todo existeError().
	 */
	public void actualizarListaTareas();
	
	public String getNombreUsuario();
	
	public String getNombreGestor();
		
	public String getVersionGestor();
	
	public String getCategoriasTarea();
	
}
