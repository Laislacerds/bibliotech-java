package Controller;

import Model.Emprestimo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class EmprestimosController {
    // Conexão com o banco de dados
    private Connection conexao;

    // Construtor que inicializa a conexão com o banco de dados
    public EmprestimosController() {
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

    // Método para consultar empréstimos com base nos critérios de busca
    public LinkedList<Emprestimo> consulta(String busca) {
        LinkedList<Emprestimo> emprestimos = new LinkedList<Emprestimo>();
        Emprestimo emprestimo;
        try {
            // Consulta SQL para selecionar todos os registros da tabela 'emprestimos'
            String consulta = "SELECT * FROM emprestimos";

            // Cria um statement e executa a consulta
            Statement stm = conexao.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            // Processa os resultados e preenche a lista 'emprestimos'
            while (resultado.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(resultado.getInt("id"));
                emprestimo.setIdLivro(resultado.getInt("id_livro"));
                emprestimo.setDataEmprestimo(resultado.getDate("data_emprestimo"));
                emprestimo.setDataDevolucao(resultado.getDate("data_devolucao"));
                emprestimo.setIdUsuario(resultado.getInt("id_usuario"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException ex) {
            // Trata exceções relacionadas ao SQL
            ex.printStackTrace();
        }
        return emprestimos;
    }

    // Método para adicionar um novo empréstimo ao banco de dados
    public void adicionar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (id, id_livro, data_emprestimo, data_devolucao, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores do empréstimo
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, emprestimo.getId());
            stmt.setInt(2, emprestimo.getIdLivro());
            stmt.setDate(3, emprestimo.getDataEmprestimo());
            stmt.setDate(4, emprestimo.getDataDevolucao());
            stmt.setInt(5, emprestimo.getIdUsuario());

            // Executa a inserção no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Método para alterar um empréstimo existente no banco de dados
    public void alterar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET id=?, id_livro=?, data_emprestimo=?, data_devolucao=?, id_usuario=? WHERE id=?";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores do empréstimo
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, emprestimo.getId());
            stmt.setInt(2, emprestimo.getIdLivro());
            stmt.setDate(3, emprestimo.getDataEmprestimo());
            stmt.setDate(4, emprestimo.getDataDevolucao());
            stmt.setInt(5, emprestimo.getIdUsuario());
            stmt.setInt(6, emprestimo.getId());

            // Executa a atualização no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Método para remover um empréstimo do banco de dados
    public void remover(Emprestimo emprestimo) {
        try {
            // Cria um PreparedStatement e define o parâmetro com o ID do empréstimo a ser removido
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM emprestimos WHERE id=?");
            stmt.setLong(1, emprestimo.getId());

            // Executa a exclusão no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }
}
