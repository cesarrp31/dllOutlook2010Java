package org.legislaturachaco.com.exchange.tareas;

import com.sun.jna.Library;

public interface IGestorTareas extends Library{
	
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

    public boolean isTareaLeida(int idx);

    public boolean isTareaModificada(int idx);

    public String getEstadoTarea(int idx);

    public boolean guardarModificacionesTarea(int idx);
    
    public boolean borrarTarea(int idx);

    public int getPorcentajeTareaCompletada(int idx);

    public boolean isTareaConflicto(int idx);

    public boolean isTareaRecurrente(int idx);

    public String getImportanciaTarea(int idx);

    public String getIdTarea(int idx);

    public String getCategoriaTarea(int idx);
    
    public boolean setCategoriaTarea(int idx, String nombre);
    
    public boolean marcarTareaCompletada(int idx);
    
}
