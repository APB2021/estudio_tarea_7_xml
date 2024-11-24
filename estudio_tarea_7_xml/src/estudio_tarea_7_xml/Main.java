package estudio_tarea_7_xml;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		GestorAlumnos gestor = new GestorAlumnos();
		File ficheroXML = gestor.solicitarFicheroXML();
		//gestor.generaXMLdesdeListaDatosComoEtiquetas(gestor.leeAlumnos(), ficheroXML);
		
		gestor.generaXMLdesdeListaDatosComoAtributos(gestor.leeAlumnos(), ficheroXML);
	}
}