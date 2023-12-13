package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoLivro {
    // Informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost/biblioteca";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    // Método para obter o ID de um livro com base no título
    public int obterIdLivroPorTitulo(String tituloLivro) {
        int livroId = -1; // Valor padrão se não encontrar

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Consulta SQL para selecionar o ID do livro com o título fornecido
            String sql = "SELECT id FROM livro WHERE titulo = ?";

            // Cria um PreparedStatement para evitar SQL injection
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, tituloLivro);

                // Executa a consulta e processa o resultado
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Se encontrar, atribui o ID do livro encontrado à variável
                        livroId = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livroId;
    }
}
