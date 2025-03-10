
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private int loginAttempts = 0; // Batasan percobaan login

    public LoginForm() {
        frame = new JFrame("Halaman Login");
        frame.setSize(350, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("Username :"));
        usernameField = new JTextField();
        frame.add(usernameField);

        frame.add(new JLabel("Password :"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim().toLowerCase();
                String password = new String(passwordField.getPassword()).trim().toLowerCase();

                if (username.equals("pbo") && password.equals("if-d")) {
                    frame.dispose();
                    new InputForm();  
                } else {
                    loginAttempts++;
                    JOptionPane.showMessageDialog(frame, "Login Gagal! Percobaan ke-" + loginAttempts, "Error", JOptionPane.ERROR_MESSAGE);
                    
                    if (loginAttempts >= 3) {
                        JOptionPane.showMessageDialog(frame, "Terlalu banyak percobaan! Aplikasi akan keluar.", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
