package org.legislaturachaco.com.exchange.tareas;

import com.sun.jna.Library;

public interface IGestorTareas extends Library{
	/*
	public static final int NO_EXISTE_ERROR = 0;
	public static final int USUARIO_NO_ENCONTRADO = 1;
	public static final int OTRO_ERROR = 10;*/
	
	/*public String getVersionDLL();
	
	/*public void setCredencialesUsuario(String usuario, String contrasena);
	
	public String getNombreSWFuente();
		
	public String getVersionSWFuente();
	
	public boolean getSetUsuario(String usuario, String contrasena);
	
	public String getNombreUsuario();*/
	
	public String getCategoriasTarea();
	
	public int getTotalTareas();
	
	public String getAsuntoTarea(int idx);
	
	public String getCuerpoTarea(int idx);
	
	public String getFechaCreacionTarea(int idx);
	
	public String getFechaVencimientoTarea(int idx);
	
	public boolean isTareaCompletada(int idx);
	
	public String getFechaTareaCompletada(int idx);
	
	public String getPropietarioTarea(int idx);
	
	public String getEstadoPropietarioTarea(int idx);

    public String getDelegadorTarea(int idx);

    public Boolean isTareaLeida(int idx);

    public Boolean isTareaModificada(int idx);

    public String getEstadoTarea(int idx);

    public Boolean guardarModificacionesTarea(int idx);
    
    public Boolean borrarTarea(int idx);

    public int getPorcentajeTareaCompletada(int idx);

    public Boolean isTareaConflicto(int idx);

    public Boolean isTareaRecurrente(int idx);

    public String getImportanciaTarea(int idx);

    public String getIdTarea(int idx);

    public String getCategoriaTarea(int idx);
    
    public Boolean setCategoriaTarea(int idx, String nombre);
    
    public Boolean marcarTareaCompletada(int idx);
    
    /*public Boolean existeError();
    
    public String msgError();
    
    public String claseError();
    
    public int codigoError();*/
}
