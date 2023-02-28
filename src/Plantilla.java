import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Plantilla {
    String nombre;
    String apellido;
    String fecha;

    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;

    public Plantilla(String nombre, String apellido, String fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;

        documento = new Document();
        titulo = new Paragraph("Minimarket EL BÚHO");
    }
    public void crearPlantilla(){
        try{
            archivo = new FileOutputStream(nombre + ".pdf");
            PdfWriter.getInstance(documento,archivo);
            documento.open();
            titulo.setAlignment(1);

            documento.add(titulo);
            documento.add(new Paragraph("Nombre: " + nombre));
            documento.add(new Paragraph("Apellido: " + apellido));
            documento.add(new Paragraph("Fecha: " + fecha));
            documento.add(Chunk.NEWLINE);

            documento.close();
        }catch (FileNotFoundException | DocumentException ex){

        }
    }
}
