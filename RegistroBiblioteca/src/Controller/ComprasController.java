package Controller;

import Model.Compra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class ComprasController {
    // Conexão com o banco de dados
    private Connection conexao;

    // O construtor inicializa a conexão com o banco de dados
    public ComprasController() {
        this.conexao = getConnection();
    }

    // Estabelece uma conexão com o banco de dados MySQL
    public Connection getConnection() {
        try {
            // Carrega o driver JDBC para o MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Conecta ao banco de dados 'biblioteca' com o nome de usuário e senha especificados
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblioteca", "", "");
        } catch (ClassNotFoundException ex) {
            // Trata o caso em que o driver JDBC não é encontrado
            // Pode ser necessário adicionar o arquivo JAR do driver ao classpath
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
        return null;
    }

    // Recupera uma lista de compras com base nos critérios de busca
    public LinkedList<Compra> consulta(String busca) {
        LinkedList<Compra> compras = new LinkedList<Compra>();
        Compra compra;
        try {
            // Consulta SQL para selecionar todos os registros da tabela 'compras'
            String consulta = "SELECT * FROM compras";

            // Cria um statement e executa a consulta
            Statement stm = conexao.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            // Processa os resultados e preenche a lista 'compras'
            while (resultado.next()) {
                compra = new Compra();
                compra.setId(resultado.getInt("id"));
                compra.setUserId(resultado.getInt("user_id"));
                compra.setUserName(resultado.getString("user_name")); // Adicionado
                compra.setLivroId(resultado.getInt("livro_id"));
                compra.setValorTotal(resultado.getDouble("valor_total"));
                compra.setDataHora(resultado.getDate("data_hora"));
                compra.setQuantidade(resultado.getInt("quantidade"));
                compras.add(compra);
            }
        } catch (SQLException ex) {
            // Trata exceções relacionadas ao SQL
            ex.printStackTrace();
        }
        return compras;
    }

    // Adiciona uma compra ao banco de dados
    public void adicionar(Compra compra) {
        String sql = "INSERT INTO compras (user_id, user_name, livro_id, valor_total, data_hora, quantidade) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            // Verifica se o livro_id é válido antes de adicionar a compra
            if (livroIdValido(compra.getLivroId())) {
                PreparedStatement stmt = conexao.prepareStatement(sql);

                stmt.setInt(1, compra.getUserId());
                stmt.setString(2, compra.getUserName()); // Adicionado
                stmt.setInt(3, compra.getLivroId());
                stmt.setDouble(4, compra.getValorTotal());
                stmt.setTimestamp(5, new java.sql.Timestamp(compra.getDataHora().getTime()));
                stmt.setInt(6, compra.getQuantidade());

                // Executa a inserção no banco de dados
                stmt.execute();
                stmt.close();
            } else {
                // Exibe uma mensagem se o livro não for encontrado
                System.out.println("Livro não encontrado. Não é possível adicionar a compra.");
            }
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Verifica se o livro_id é válido
    private boolean livroIdValido(int livroId) {
        try {
            // Consulta SQL para contar o número de registros com o livro_id fornecido na tabela 'livro'
            String sql = "SELECT COUNT(*) FROM livro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, livroId);
            ResultSet resultado = stmt.executeQuery();

            // Retorna verdadeiro se houver pelo menos um registro, indicando que o livro_id é válido
            if (resultado.next()) {
                int count = resultado.getInt(1);
                return count > 0;
            }

            // Retorna falso se não houver registros, indicando que o livro_id não é válido
            return false;
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Altera a compra no banco de dados
    public void alterar(Compra compra) {
        String sql = "UPDATE compras SET user_id=?, user_name=?, livro_id=?, valor_total=?, data_hora=?, quantidade=? WHERE id=?";
        try {
            // Cria um PreparedStatement e define os parâmetros com os valores da compra
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, compra.getUserId());
            stmt.setString(2, compra.getUserName());
            stmt.setInt(3, compra.getLivroId());
            stmt.setDouble(4, compra.getValorTotal());
            stmt.setTimestamp(5, new java.sql.Timestamp(compra.getDataHora().getTime()));
            stmt.setInt(6, compra.getQuantidade());
            stmt.setInt(7, compra.getId());

            // Executa a atualização no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }

    // Remove a compra do banco de dados
    public void remover(Compra compra) {
        try {
            // Cria um PreparedStatement e define o parâmetro com o ID da compra a ser removida
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM compras WHERE id=?");
            stmt.setLong(1, compra.getId());

            // Executa a exclusão no banco de dados
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao SQL
            throw new RuntimeException(e);
        }
    }
}
