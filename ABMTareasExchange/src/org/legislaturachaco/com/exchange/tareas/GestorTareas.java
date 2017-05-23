package org.legislaturachaco.com.exchange.tareas;

import java.io.File;

import com.sun.jna.Native;

class GestorTareas {
	private static String nombreDll;
	private static String pathCompleto;
	private static String separador = File.separator;

	private static IGestorExchange ge;
	private static IGestorTareas gt;

	private GestorTareas() {}

	public static String getNombreDll() {
		return nombreDll;
	}

	public static void setNombreDll(String nombreDll) {
		GestorTareas.nombreDll = nombreDll;
	}

	public static String getPathCompleto() {
		return pathCompleto;
	}

	public static void setPathCompleto(String pathCompleto) {
		GestorTareas.pathCompleto = pathCompleto;
	}

	public static IGestorTareas getInstanciaGT() {
		if (gt == null) {
			cargarDLL();
			//gt = getInstanciaGE().getGestorTareas();
			gt= (IGestorTareas) Native.loadLibrary(getNombreDll(), IGestorTareas.class);
		}
		return gt;
	}
	
	public static IGestorExchange getInstanciaGE() {
		if (ge == null) {
			cargarDLL();
			ge = (IGestorExchange) Native.loadLibrary(getNombreDll(), IGestorExchange.class);
		}
		return ge;
	}
	
	private static void cargarDLL(){
		final File midll = new File(getPathCompleto() + separador + getNombreDll() + ".dll");
		System.load(midll.getAbsolutePath());
	}

	public static void main(String[] args) {
		try{
			final String s = separador, projecto = "Outlook2010Csharp", pathUsuario = System.getProperty("user.home") + s,
					pathProjsVS = "Documents" + s + "Visual Studio 2015" + s + "Projects" + s,
					pathProjVS = projecto + s + projecto + s + "bin" + s + "Debug" + s,
					pathCompleto = pathUsuario + pathProjsVS + pathProjVS;

			String os = System.getProperty("os.name"), arq = System.getProperty("os.arch"), 
					osd= System.getProperty("user.dir"), osv= System.getProperty("os.version");

			System.out.println("os.name " + os);
			System.out.println("os.arch " + arq);
			System.out.println("os.version " + osv);
			System.out.println("user.dir " + osd);

			if (os.startsWith("Win")) {
				GestorTareas.setNombreDll(projecto);
				GestorTareas.setPathCompleto(pathCompleto);
				
				System.out.println("Versión dll utilizado: " + getInstanciaGE().getVersionDLL());
				System.out.println("Nombre del sw utilizado: " + getInstanciaGE().getNombreGestor());
				System.out.println("Versión del sw utilizado: " + getInstanciaGE().getVersionGestor());
				
				System.out.println("Lista categorias: " + getInstanciaGE().getCategoriasTarea());		
				
				tareasUsuario("coperaltsa");
				tareasUsuario("coperalta");				
				tareasUsuario("cesar.op85");				
			} else {
				System.out.println("SO: "+os+", no se puede continuar.");
			}

			System.out.println("FIN");
		}catch(Error er){
			System.out.println(er.getLocalizedMessage());
			er.printStackTrace();
		}
	}

	private static void tareasUsuario(String usuario){
		gt= null;
		
		try {
			getInstanciaGE().setCredencialesUsuario(usuario,"");
			
			if(getInstanciaGE().existeError()) throw new RuntimeException("Error en dll");
			
			getInstanciaGE().actualizarListaTareas();
			
			if(getInstanciaGE().existeError()) throw new RuntimeException("Error en dll");
			
			System.out.print("\nUsuario utilizado: " + getInstanciaGE().getNombreUsuario());
			System.out.println(". Total de Tareas en Outlook: " + getInstanciaGT().getTotalTareas());

			System.out.println("\nLista de Tareas");
			for (int idx = 0; idx <= getInstanciaGT().getTotalTareas(); idx++) {
				System.out.println("Tarea Nº " + idx + ". Asunto: " + getInstanciaGT().getAsuntoTarea(idx));
				System.out.println("Tarea Nº " + idx + ". Body: " + getInstanciaGT().getCuerpoTarea(idx));
				System.out.println(
						"Tarea Nº " + idx + ". FechaCreacionTarea: " + getInstanciaGT().getFechaCreacionTarea(idx));
				System.out.println("Tarea Nº " + idx + ". FechaVencimientoTarea: "
						+ getInstanciaGT().getFechaVencimientoTarea(idx));
				System.out.println(
						"Tarea Nº " + idx + ". isTareaCompletada: " + getInstanciaGT().isTareaCompletada(idx));
				System.out.println("Tarea Nº " + idx + ". FechaTareaCompletada: "
						+ getInstanciaGT().getFechaTareaCompletada(idx));
				System.out.println("Tarea Nº " + idx + ". Propietario: " + getInstanciaGT().getPropietarioTarea(idx));

				System.out.println("Tarea Nº " + idx + ". EstadoPropietario: "
						+ getInstanciaGT().getEstadoPropietarioTarea(idx));
				System.out.println("Tarea Nº " + idx + ". Delegador: " + getInstanciaGT().getDelegadorTarea(idx));
				System.out.println("Tarea Nº " + idx + ". isLeida: " + getInstanciaGT().isTareaLeida(idx));
				System.out.println("Tarea Nº " + idx + ". isModificada: " + getInstanciaGT().isTareaModificada(idx));
				System.out.println("Tarea Nº " + idx + ". Estado: " + getInstanciaGT().getEstadoTarea(idx));
				// System.out.println("Tarea Nº " + idx + ". Propietario: "
				// + test.guardar(idx));
				System.out.println("Tarea Nº " + idx + ". PorcentajeCompletada: "
						+ getInstanciaGT().getPorcentajeTareaCompletada(idx));
				System.out.println("Tarea Nº " + idx + ". isConflicto: " + getInstanciaGT().isTareaConflicto(idx));
				System.out.println("Tarea Nº " + idx + ". isRecurrente: " + getInstanciaGT().isTareaRecurrente(idx));
				System.out.println("Tarea Nº " + idx + ". isCompletada: " + getInstanciaGT().isTareaCompletada(idx));
				System.out.println("Tarea Nº " + idx + ". Importancia: " + getInstanciaGT().getImportanciaTarea(idx));
				System.out.println("Tarea Nº " + idx + ". Id: " + getInstanciaGT().getIdTarea(idx));
				System.out.println("Tarea Nº " + idx + ". Categoria: " + getInstanciaGT().getCategoriaTarea(idx));
				System.out.println("\n------------------------------------------------------------------");
			}
			/*
			System.out.println(getInstancia().marcarTareaCompletada(16));
			System.out.println(getInstancia().setCategoriaTarea(16, "Categoría amarilla"));*/
		} catch (Exception e) {
			System.err.println("Se ha producido un error: " + e.getLocalizedMessage());
			
			System.err.println("Existe error: " + getInstanciaGE().existeError());
			System.err.println("Clase de error: " + getInstanciaGE().claseError());
			System.err.println("Mensaje de error: " + getInstanciaGE().msgError());
			System.err.println("Código de error: " + getInstanciaGE().codigoError());
			
			e.printStackTrace();
		}
	}	
}
