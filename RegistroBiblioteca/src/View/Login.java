package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JTextField tfLogin;
    private JPasswordField ptPassword;
    private JButton entrarButton;
    private JPanel LoginPainel;

    // Construtor da classe Login
    public Login() {
        setContentPane(LoginPainel);
        setTitle("Login");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona um ActionListener ao botão "Entrar"
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin(); // Chama o método para realizar o login
            }
        });

        // Exibe a janela de login
        setVisible(true);
    }

    // Método para realizar o login
    private void realizarLogin() {
        String email = tfLogin.getText();
        String senha = String.valueOf(ptPassword.getPassword());

        // Informações de conexão ao banco de dados, não esqueça de trocar a senha e o user do root.
        final String url = "jdbc:mysql://localhost/biblioteca";
        final String username = "";
        final String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Consulta SQL para verificar as credenciais do usuário
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, senha);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Se as credenciais estiverem corretas, exibe uma mensagem de boas-vindas
                        String nomeUsuario = resultSet.getString("name");
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!\nBem-vindo, " + nomeUsuario + "!");

                        // Abre a tela de livros e fecha a janela de login
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new TelaLivros();
                            }
                        });
                        dispose();
                    } else {
                        // Se as credenciais estiverem incorretas, exibe uma mensagem de erro
                        JOptionPane.showMessageDialog(null, "Login falhou. Verifique suas credenciais.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
        }
    }

    // Método principal para iniciar o aplicativo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login(); // Inicia a janela de login
            }
        });
    }
}
