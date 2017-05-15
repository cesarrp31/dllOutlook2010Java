package org.legislaturachaco.com.exchange.tareas;

import java.io.File;

import com.sun.jna.Native;

public class GestorTareas {
	private static String nombreDll;
	private static String pathCompleto;
	private static String separador = File.separator;

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

	public static IGestorTareas getInstancia() {
		if (gt == null) {
			final File midll = new File(getPathCompleto() + separador + getNombreDll() + ".dll");
			System.load(midll.getAbsolutePath());
			gt = (IGestorTareas) Native.loadLibrary(getNombreDll(), IGestorTareas.class);
		}
		return gt;
	}

	public static void main(String[] args) {
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

			try {
				System.out.println("Nombre del sw utilizado: " + getInstancia().getNombreSWFuente());
				System.out.println("Versión del sw utilizado: " + getInstancia().getVersionSWFuente());
				System.out.println("Usuario utilizado: " + getInstancia().getNombreUsuario());
				System.out.println("Lista categorias: " + getInstancia().getCategoriasTarea());
				System.out.println("Total de Tareas en Outlook: " + getInstancia().getTotalTareas());

				System.out.println("\nLista de Tareas");
				for (int idx = 0; idx <= getInstancia().getTotalTareas(); idx++) {
					System.out.println("Tarea Nº " + idx + ". Asunto: " + getInstancia().getAsuntoTarea(idx));
					System.out.println("Tarea Nº " + idx + ". Body: " + getInstancia().getCuerpoTarea(idx));
					System.out.println(
							"Tarea Nº " + idx + ". FechaCreacionTarea: " + getInstancia().getFechaCreacionTarea(idx));
					System.out.println("Tarea Nº " + idx + ". FechaVencimientoTarea: "
							+ getInstancia().getFechaVencimientoTarea(idx));
					System.out.println(
							"Tarea Nº " + idx + ". isTareaCompletada: " + getInstancia().isTareaCompletada(idx));
					System.out.println("Tarea Nº " + idx + ". FechaTareaCompletada: "
							+ getInstancia().getFechaTareaCompletada(idx));
					System.out.println("Tarea Nº " + idx + ". Propietario: " + getInstancia().getPropietarioTarea(idx));

					System.out.println("Tarea Nº " + idx + ". EstadoPropietario: "
							+ getInstancia().getEstadoPropietarioTarea(idx));
					System.out.println("Tarea Nº " + idx + ". Delegador: " + getInstancia().getDelegadorTarea(idx));
					System.out.println("Tarea Nº " + idx + ". isLeida: " + getInstancia().isTareaLeida(idx));
					System.out.println("Tarea Nº " + idx + ". isModificada: " + getInstancia().isTareaModificada(idx));
					System.out.println("Tarea Nº " + idx + ". Estado: " + getInstancia().getEstadoTarea(idx));
					// System.out.println("Tarea Nº " + idx + ". Propietario: "
					// + test.guardar(idx));
					System.out.println("Tarea Nº " + idx + ". PorcentajeCompletada: "
							+ getInstancia().getPorcentajeTareaCompletada(idx));
					System.out.println("Tarea Nº " + idx + ". isConflicto: " + getInstancia().isTareaConflicto(idx));
					System.out.println("Tarea Nº " + idx + ". isRecurrente: " + getInstancia().isTareaRecurrente(idx));
					System.out.println("Tarea Nº " + idx + ". isCompletada: " + getInstancia().isTareaCompletada(idx));
					System.out.println("Tarea Nº " + idx + ". Importancia: " + getInstancia().getImportanciaTarea(idx));
					System.out.println("Tarea Nº " + idx + ". Id: " + getInstancia().getIdTarea(idx));
					System.out.println("Tarea Nº " + idx + ". Categoria: " + getInstancia().getCategoriaTarea(idx));
					System.out.println("\n------------------------------------------------------------------");
				}
				
				System.out.println(getInstancia().marcarTareaCompletada(16));
				System.out.println(getInstancia().setCategoriaTarea(16, "Categoría amarilla"));
			} catch (Exception e) {
				System.err.println("Se ha producido un error: " + e.getLocalizedMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("SO: "+os+", no se puede continuar.");
		}

	}

}
