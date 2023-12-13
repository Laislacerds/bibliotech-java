package Controller;

import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UserController {
    // Conexão com o banco de dados!!!
    private Connection conexao;

    // Construtor que inicializa a conexão com o banco de dados
    public UserController() {
        this.conexao = getConnection();
    }

    // Método para estabelecer uma conexão com o banco de dados MySQL
    public Connection getConnection() {
        try {
            // Carrega o driver JDBC para o MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Conecta ao banco de dados 'biblioteca' com o nome de usuário e senha especificados
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblioteca", "", "");
        } catch (ClassNotFoundException ex) {
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
        return null;
    }

    // Método para consultar usuários com base nos critérios de busca
    public LinkedList<User> consulta(String busca) {
        LinkedList<User> users = new LinkedList<User>();
        User user;
        try {
            // Consulta SQL para selecionar todos os registros da tabela 'users'
            String consulta = "SELECT * FROM users";

            // Cria um statement e executa a consulta
            Statement stm = conexao.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            // Processa os resultados e preenche a lista 'users'
            while (resultado.next()) {
                user = new User();
                user.setId(resultado.getInt("id"));
                user.setName(resultado.getString("name"));
                user.setEmail(resultado.getString("email"));
                user.setPassword(resultado.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            // Trata exceções relacionadas ao SQL
            ex.printStackTrace();
        }
        return users;
    }

    // Método para adicionar um novo usuário ao banco de dados
    public void adicionar(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores do usuário
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            // Executa a inserção no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Método para alterar um usuário existente no banco de dados
    public void alterar(User user) {
        String sql = "UPDATE users SET name=?, email=?, password=? WHERE id=?";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores do usuário
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());

            // Executa a atualização no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Método para remover um usuário do banco de dados
    public void remover(User user) {
        try {
            // Cria um PreparedStatement e define o parâmetro com o ID do usuário a ser removido
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM users WHERE id=?");
            stmt.setLong(1, user.getId());

            // Executa a exclusão no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }
}
