
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class InputForm {
    private JFrame frame;
    private JTextField firstNameField, lastNameField;
    private JRadioButton priaRadioButton, wanitaRadioButton;
    private JTextArea textArea;

    public InputForm() {
        frame = new JFrame("Halaman Input Data");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1; 
        gbc.weighty = 0; 
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        frame.add(new JLabel("Nama Depan:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        firstNameField = new JTextField();
        frame.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        frame.add(new JLabel("Nama Belakang:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        lastNameField = new JTextField();
        frame.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        frame.add(new JLabel("Jenis Kelamin:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        priaRadioButton = new JRadioButton("Pria");
        wanitaRadioButton = new JRadioButton("Wanita");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(priaRadioButton);
        genderGroup.add(wanitaRadioButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(priaRadioButton);
        genderPanel.add(wanitaRadioButton);
        frame.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        JButton saveButton = new JButton("Simpan");
        frame.add(saveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1; 
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, gbc);

        gbc.gridy = 5;
        gbc.weighty = 0; 
        JButton convertButton = new JButton("Convert to Txt File");
        frame.add(convertButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String gender = priaRadioButton.isSelected() ? "Pria" : wanitaRadioButton.isSelected() ? "Wanita" : "Tidak dipilih";

                if (firstName.isEmpty() || lastName.isEmpty() || gender.equals("Tidak dipilih")) {
                    JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String output = firstName + " " + lastName + " | Gender : " + gender + "\n";
                textArea.append(output);

                try (FileWriter writer = new FileWriter("data.txt", true)) {
                    writer.write(output);
                    JOptionPane.showMessageDialog(frame, "Data berhasil disimpan", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan saat menyimpan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "File telah dikonversi ke TXT", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InputForm::new);
    }
}
