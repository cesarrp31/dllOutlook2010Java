package org.legislaturachaco.com.exchange.tareas;

public interface ITareaExchange {
	
	public int getTotalTareas();
	
	public String getAsuntoTarea();
	
	public String getCuerpoTarea();
	
	public String getFechaCreacionTarea();
	
	public String getFechaVencimientoTarea();
	
	public boolean isTareaCompletada();
	
	public String getFechaTareaCompletada();
	
	public String getPropietarioTarea();
	
	public String getEstadoPropietarioTarea();

    public String getDelegadorTarea();

    public boolean isTareaLeida();

    public boolean isTareaModificada();

    public String getEstadoTarea();

    public boolean guardarModificacionesTarea();
    
    public boolean borrarTarea();

    public int getPorcentajeTareaCompletada();

    public boolean isTareaConflicto();

    public boolean isTareaRecurrente();

    public String getImportanciaTarea();

    public String getIdTarea();

    public String getCategoriaTarea();
    
    public boolean setCategoriaTarea(String nombre);
    
    public boolean marcarTareaCompletada();
    
    abstract String toString();
}
