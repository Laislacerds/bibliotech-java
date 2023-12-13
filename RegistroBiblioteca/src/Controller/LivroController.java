package Controller;

import Model.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class LivroController {
    // Conexão com o banco de dados
    private Connection conexao;

    // Construtor que inicializa a conexão com o banco de dados
    public LivroController() {
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

    // Método para consultar livros com base nos critérios de busca
    public LinkedList<Livro> consulta(String busca) {
        LinkedList<Livro> livros = new LinkedList<Livro>();
        Livro livro;
        try {
            // Consulta SQL para selecionar todos os registros da tabela 'livro'
            String consulta = "SELECT * FROM livro";

            // Cria um statement e executa a consulta
            Statement stm = conexao.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            // Processa os resultados e preenche a lista 'livros'
            while (resultado.next()) {
                livro = new Livro();
                livro.setId(resultado.getInt("id"));
                livro.setTitulo(resultado.getString("titulo"));
                livro.setAutor(resultado.getString("autor"));
                livro.setIsbn(resultado.getString("isbn"));
                livro.setGenero(resultado.getString("genero"));
                livro.setQuantidadeDisponivel(resultado.getInt("quantidade_disponivel"));
                livro.setDataPublicacao(resultado.getDate("DataPublicacao"));
                livro.setPreco(resultado.getDouble("Preco"));
                livros.add(livro);
            }
        } catch (SQLException ex) {
            // Trata exceções relacionadas ao SQL
            ex.printStackTrace();
        }
        return livros;
    }

    // Método para adicionar um novo livro ao banco de dados
    public void adicionar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, isbn, genero, quantidade_disponivel, DataPublicacao, Preco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores do livro
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setString(4, livro.getGenero());
            stmt.setInt(5, livro.getQuantidadeDisponivel());
            stmt.setDate(6, livro.getDataPublicacao());
            stmt.setDouble(7, livro.getPreco());

            // Executa a inserção no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Método para alterar um livro existente no banco de dados
    public void alterar(Livro livro) {
        String sql = "UPDATE livro SET titulo=?, autor=?, isbn=?, genero=?, quantidade_disponivel=?, DataPublicacao=?, Preco=? WHERE id=?";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores do livro
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setString(4, livro.getGenero());
            stmt.setInt(5, livro.getQuantidadeDisponivel());
            stmt.setDate(6, livro.getDataPublicacao());
            stmt.setDouble(7, livro.getPreco());
            stmt.setInt(8, livro.getId());

            // Executa a atualização no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Método para remover um livro do banco de dados
    public void remover(Livro livro) {
        try {
            // Cria um PreparedStatement e define o parâmetro com o ID do livro a ser removido
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM livro WHERE id=?");
            stmt.setLong(1, livro.getId());

            // Executa a exclusão no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }
}
