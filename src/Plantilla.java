import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

public  class  Plantilla {
    String nombre;
    String apellido;
    String fecha;

    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;

    public Plantilla(JTextField nombre, JTextField apellido, String fecha) {

        this.nombre = nombre.getText();
        this.apellido = apellido.getText();
        this.fecha = fecha;

        documento = new Document();
        titulo = new Paragraph("Minimarket EL BÚHO");
    }
    public void crearPlantilla(){
        try{
            archivo = new FileOutputStream("Factura"+nombre+".pdf");
            PdfWriter.getInstance(documento,archivo);
            documento.open();
            titulo.setAlignment(1);

            documento.add(titulo);
            documento.add(new Paragraph("Nombre: " + nombre));
            documento.add(new Paragraph("Apellido: " + apellido));
            documento.add(new Paragraph("Fecha: " + fecha));
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("CI");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("edad");
            try {
                Connection con;
                con = getConection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM DATOS");
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    do{
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));

                    }while(rs.next());
                    documento.add(tabla);
                }
            }catch (Exception x){
                System.out.println(x);
            }

            documento.close();
        }catch (FileNotFoundException | DocumentException ex){
        }
    }
    public static Connection getConection() {
        Connection con = null;
        String base = "VEHICULOS";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "Pelota2002";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
}
