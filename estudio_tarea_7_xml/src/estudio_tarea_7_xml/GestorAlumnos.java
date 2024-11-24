package estudio_tarea_7_xml;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class GestorAlumnos {

	private static Scanner sc = new Scanner(System.in);

	private String solicitarDatoString(String datoSolicitado) {
		System.out.print(datoSolicitado);
		datoSolicitado = sc.nextLine();
		return datoSolicitado;
	}

	public File solicitarFicheroXML() {
		String rutaFicheroBinario = solicitarDatoString("Ruta: ");
		String nombreFicheroBinario = solicitarDatoString("Nombre fichero(.xml): ");
		File ficheroBinario = new File(rutaFicheroBinario, nombreFicheroBinario);
		return ficheroBinario;
	}

	public Alumno leeAlumno() {

		System.out.print("Nia: ");
		int nia = sc.nextInt();
		sc.nextLine();

		String nombre = solicitarDatoString("Nombre: ");
		String apellidos = solicitarDatoString("Apellidos: ");

		System.out.print("Género: ");
		char genero = sc.nextLine().toUpperCase().charAt(0);

		String fechaNacimientoString = solicitarDatoString("Fecha de nacimiento: ");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimientoString, formato);

		String ciclo = solicitarDatoString("Ciclo: ");
		String curso = solicitarDatoString("Curso: ");
		String grupo = solicitarDatoString("Grupo: ");

		Alumno alumno = new Alumno(nia, nombre, apellidos, genero, fechaNacimientoLocalDate, ciclo, curso, grupo);

		return alumno;
	}

	public List<Alumno> leeAlumnos() {
		List<Alumno> alumnos = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Alumno alumno = leeAlumno();
			alumnos.add(alumno);
		}
		return alumnos;
	}

	public void generaXMLdesdeListaDatosComoEtiquetas(List<Alumno> listaAlumnos, File ficheroXML) {

		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("alumnos");
			document.appendChild(root);

			for (Alumno objetoAlumno : listaAlumnos) {

				Element alumno = document.createElement("alumno");
				root.appendChild(alumno);

				crearElementoAlumno("nia", Integer.toString(objetoAlumno.getNia()), alumno, document);
				crearElementoAlumno("nombre", objetoAlumno.getNombre(), alumno, document);
				crearElementoAlumno("apellidos", objetoAlumno.getApellidos(), alumno, document);
				crearElementoAlumno("genero", Character.toString(objetoAlumno.getGenero()), alumno, document);
				crearElementoAlumno("fechaNacimiento", objetoAlumno.getFechaNacimiento().toString(), alumno, document);
				crearElementoAlumno("ciclo", objetoAlumno.getCiclo(), alumno, document);
				crearElementoAlumno("curso", objetoAlumno.getCurso(), alumno, document);
				crearElementoAlumno("grupo", objetoAlumno.getGrupo(), alumno, document);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			// StreamResult result = new StreamResult(new File("alumnos.xml"));
			StreamResult result = new StreamResult((ficheroXML));

			transformer.transform(source, result);

			System.out.println("El archivo XML ha sido generado exitosamente.");

		} catch (Exception e) {
			System.err.println("Error al generar el archivo XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void generaXMLdesdeListaDatosComoAtributos(List<Alumno> listaAlumnos, File ficheroXML) {

		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("alumnos");
			document.appendChild(root);

			for (Alumno objetoAlumno : listaAlumnos) {

				Element alumno = document.createElement("alumno");
				root.appendChild(alumno);
				alumno.setAttribute("nia", Integer.toString(objetoAlumno.getNia()));
				alumno.setAttribute("nombre", objetoAlumno.getNombre());
				alumno.setAttribute("apellidos", objetoAlumno.getApellidos());
				alumno.setAttribute("genero", Character.toString(objetoAlumno.getGenero()));
				alumno.setAttribute("fechaNacimiento", objetoAlumno.getFechaNacimiento().toString());
				alumno.setAttribute("ciclo", objetoAlumno.getCiclo());
				alumno.setAttribute("curso", objetoAlumno.getCurso());
				alumno.setAttribute("grupo", objetoAlumno.getGrupo());
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			// StreamResult result = new StreamResult(new File("alumnos.xml"));
			StreamResult result = new StreamResult((ficheroXML));

			transformer.transform(source, result);

			System.out.println("El archivo XML con atributos ha sido generado exitosamente.");

		} catch (Exception e) {
			System.err.println("Error al generar el archivo XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void crearElementoAlumno(String datoAlumno, String valor, Element alumno, Document document) {
		Element element = document.createElement(datoAlumno);
		Text text = document.createTextNode(valor);
		element.appendChild(text);
		alumno.appendChild(element);
	}

}