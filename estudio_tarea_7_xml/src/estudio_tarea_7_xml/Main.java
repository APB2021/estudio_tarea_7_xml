package estudio_tarea_7_xml;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		GestorAlumnos gestor = new GestorAlumnos();
		File ficheroXML = gestor.solicitarFicheroXML();
		gestor.generaXMLdesdeLista(gestor.leeAlumnos(), ficheroXML);
	}
}