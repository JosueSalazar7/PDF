import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente");
        Date date = new Date();
        Long fecha = date.getTime();
        Plantilla miPlantilla = new Plantilla(nombre,apellido,fecha.toString());
        miPlantilla.crearPlantilla();

    }
}