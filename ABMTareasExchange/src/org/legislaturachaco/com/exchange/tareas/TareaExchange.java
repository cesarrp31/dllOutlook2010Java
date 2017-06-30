package org.legislaturachaco.com.exchange.tareas;

public class TareaExchange implements ITareaExchange {
	private int idx;
	private IGestorTareas gt;
	
	public TareaExchange(int idx, IGestorTareas gt) {
		if((idx<0)||(idx>=gt.getTotalTareas())) throw new IndexOutOfBoundsException("Fuera de rango");
		this.idx= idx;
		this.gt= gt;
	}
	
	@Override
	public int getTotalTareas() {
		return gt.getTotalTareas();
	}

	@Override
	public String getAsuntoTarea() {
		return gt.getAsuntoTarea(idx);
	}

	@Override
	public String getCuerpoTarea() {
		return gt.getCuerpoTarea(idx);		
	}

	@Override
	public String getFechaCreacionTarea() {
		return gt.getFechaCreacionTarea(idx);
	}

	@Override
	public String getFechaVencimientoTarea() {
		return gt.getFechaVencimientoTarea(idx);
	}

	@Override
	public boolean isTareaCompletada() {
		return gt.isTareaCompletada(idx);
	}

	@Override
	public String getFechaTareaCompletada() {
		return gt.getFechaTareaCompletada(idx);
	}

	@Override
	public String getPropietarioTarea() {
		return gt.getPropietarioTarea(idx);
	}

	@Override
	public String getEstadoPropietarioTarea() {
		return gt.getEstadoPropietarioTarea(idx);
	}

	@Override
	public String getDelegadorTarea() {
		return gt.getDelegadorTarea(idx);
	}

	@Override
	public boolean isTareaLeida() {
		return gt.isTareaLeida(idx);
	}

	@Override
	public boolean isTareaModificada() {
		return gt.isTareaModificada(idx);
	}

	@Override
	public String getEstadoTarea() {
		return gt.getEstadoTarea(idx);
	}

	@Override
	public boolean guardarModificacionesTarea() {
		return gt.guardarModificacionesTarea(idx);
	}

	@Override
	public boolean borrarTarea() {
		return gt.borrarTarea(idx);
	}

	@Override
	public int getPorcentajeTareaCompletada() {
		return gt.getPorcentajeTareaCompletada(idx);
	}

	@Override
	public boolean isTareaConflicto() {
		return gt.isTareaConflicto(idx);
	}

	@Override
	public boolean isTareaRecurrente() {
		return gt.isTareaRecurrente(idx);
	}

	@Override
	public String getImportanciaTarea() {
		return gt.getImportanciaTarea(idx);
	}

	@Override
	public String getIdTarea() {
		return gt.getIdTarea(idx);
	}

	@Override
	public String getCategoriaTarea() {
		return gt.getCategoriaTarea(idx);
	}

	@Override
	public boolean setCategoriaTarea(String nombre) {
		return gt.setCategoriaTarea(idx, nombre);
	}

	@Override
	public boolean marcarTareaCompletada() {
		return gt.marcarTareaCompletada(idx);
	}
	
	@Override
	public String toString(){
		return this.getCuerpoTarea();
	}
	
}