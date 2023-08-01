import javax.swing.*;

public class Usuario {
    private JTextArea textArea1;
    private JPanel usuario;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Usuario");
        frame.setContentPane(new Usuario().usuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
