package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistroForm extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegistro;
    private JButton btnCancelar;
    private JPanel RegistrePainel;

    // Atributo para armazenar o usuário registrado
    public User user;

    // Construtor da classe RegistroForm
    public RegistroForm(JFrame parent) {
        super(parent);
        setTitle("Criar nova conta");
        setContentPane(RegistrePainel);
        setMinimumSize(new Dimension(1000, 720));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Adiciona um ActionListener ao botão "Registrar"
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser(); // Chama o método para registrar um novo usuário
            }
        });

        // Adiciona um ActionListener ao botão "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de registro
            }
        });

        // Exibe a janela de registro
        setVisible(true);
    }

    // Método para registrar um novo usuário
    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        // Verifica se todos os campos foram preenchidos
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor, insira todos os campos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Verifica se as senhas são iguais
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null,
                    "As senhas não estão iguais",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Adiciona o usuário ao banco de dados
        user = addUserToDatabase(name, email, password);

        // Verifica se o usuário foi adicionado com sucesso
        if (user != null) {
            dispose(); // Fecha a janela de registro
        } else {
            JOptionPane.showMessageDialog(null,
                    "Falha em registrar um novo usuário",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
    }

    // Método para adicionar um usuário ao banco de dados
    private User addUserToDatabase(String name, String email, String password) {
        User user = null;

        // Informações de conexão ao banco de dados.
        final String url = "jdbc:mysql://localhost/biblioteca";
        final String Username = "";
        final String Password = "";

        try {
            Connection conn = DriverManager.getConnection(url, Username, Password);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users(name,email,password)" + "values (?,?,?)";

            // Cria e executa a instrução SQL
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            // Verifica se linhas foram adicionadas
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.email = email;
                user.Password = password;
            }

            // Fecha as conexões
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // Método principal para iniciar o formulário de registro
    public static void main(String[] args) {
        RegistroForm myForm = new RegistroForm(null);
        User user = myForm.user;

        if (user != null) {
            System.out.println("Sucesso no registro, " + user.name);

            // Abre a janela de login
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Login();
                }
            });
        } else {
            System.out.println("Registro cancelado");
        }
    }
}
