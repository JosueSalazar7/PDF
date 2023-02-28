import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
public class Factura  {
    private JPanel panel1;
    private JTextField CEDULA;
    private JTextField NOMBRE;
    private JButton BUSCARButton;
    private JTextField APELLIDO;
    private JTextField EDAD;


    public Factura()  {
    BUSCARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con;
            PreparedStatement st;
            Statement ps;
            try {

                con = getConection();
                ps = con.createStatement();
                ResultSet rs;
                rs = ps.executeQuery("select * from DATOS where CI_DUENIO=" +  CEDULA.getText() + ";");
                while (rs.next()) {
                    NOMBRE.setText(rs.getString("NOM_DUENIO"));
                    APELLIDO.setText(rs.getString("APE_DUENIO"));
                    EDAD.setText((rs.getString("EDAD")));
                    Date date = new Date();
                    Long fecha = date.getTime();
                    Plantilla miPlantilla = new Plantilla(NOMBRE,APELLIDO,fecha.toString());
                    miPlantilla.crearPlantilla();
                }
            } catch (Exception s) {

            }
        }
    });

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
    public static void main(String[] args) {
        JFrame frame = new JFrame("Josue Salazar \t                            Vehiculos");
        frame.setContentPane(new Factura().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
