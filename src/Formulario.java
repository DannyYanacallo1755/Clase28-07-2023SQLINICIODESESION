import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario {


    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton registrarButton;
    private JPanel registro;
    private JPasswordField passwordField1;

    public Formulario() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre=textField1.getText();
                String apeliido=textField2.getText();
                String usuario=textField3.getText();
                String contraseña= new String(passwordField1.getPassword());

                JOptionPane.showMessageDialog(registro, "Registro exitoso.");
            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario().registro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
