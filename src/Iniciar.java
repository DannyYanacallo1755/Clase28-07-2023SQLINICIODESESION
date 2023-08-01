import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Iniciar {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel Inicio;
    private JButton iniciarSesionButton;
    private JButton DELETEButton;
    private JButton insertarButton;
    static final String DB_URL = "jdbc:mysql://localhost/EJERCICIO";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY = "SELECT * FROM estudiantes1 WHERE nombre=? AND contraseña=?";

    public Iniciar() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText();
                String contraseña = new String(passwordField1.getPassword());

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                     PreparedStatement stmt = conn.prepareStatement(QUERY)) {

                    // Establecemos los parámetros de la consulta.
                    stmt.setString(1, usuario);
                    stmt.setString(2, contraseña);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String cedula = rs.getString("cedula");
                        String ciudad = rs.getString("ciudad");

                        System.out.println("Datos del usuario:");
                        System.out.println("Nombre: " + nombre);
                        System.out.println("cedula: " + cedula);
                        System.out.println("ciudad: " + ciudad);


                        JOptionPane.showMessageDialog(Inicio, "Inicio de sesión exitoso.");

                    } else {
                        JOptionPane.showMessageDialog(Inicio, "Credenciales incorrectas. Inténtalo de nuevo.",
                                "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException sq) {
                    sq.printStackTrace();
                    JOptionPane.showMessageDialog(Inicio, "Error al intentar conectarse a la base de datos.",
                            "Error de conexión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText();
                String contraseña = new String(passwordField1.getPassword());

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM estudiantes1 WHERE nombre=? AND contraseña=?")) {


                    stmt.setString(1, usuario);
                    stmt.setString(2, contraseña);

                    int BUSCAR = stmt.executeUpdate();

                    // Verificamos si algún usuario fue eliminado.
                    if (BUSCAR > 0) {
                        JOptionPane.showMessageDialog(Inicio, "Usuario eliminado exitosamente.");
                        textField1.setText("");
                        passwordField1.setText("");
                    }

                } catch (SQLException sq) {
                    sq.printStackTrace();
                    JOptionPane.showMessageDialog(Inicio, "Error al intentar conectarse a la base de datos.",
                            "Error de conexión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoID = "123";
                String nuevaNombre = "Danny Yanacallo ";
                String nuevaedad = "20";
                String nuevaCedula = "17555042213";
                String nuevaContraseña = "DNN";
                String nuevaCiudad = "Quito";

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO estudiantes1 (id, nombre, edad, ciudad, cedula, contraseña) VALUES (?, ?, ?, ?, ?, ?)")) {

                    stmt.setString(1, nuevoID);
                    stmt.setString(2, nuevaNombre);
                    stmt.setString(3, nuevaedad);
                    stmt.setString(4, nuevaCiudad);
                    stmt.setString(5, nuevaCedula);
                    stmt.setString(6, nuevaContraseña);

                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Nuevo usuario registrado exitosamente.");
                    } else {
                        System.out.println("Error al registrar el nuevo usuario.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error al intentar conectarse a la base de datos.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Iniciar");
        frame.setContentPane(new Iniciar().Inicio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
