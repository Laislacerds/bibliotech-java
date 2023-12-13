package Model;

import java.sql.Date;

public class Livro {

    // Atributos da classe Livro
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private int quantidadeDisponivel;
    private Date dataPublicacao;
    private double preco;

    // Construtor vazio necessário para a criação de objetos Livro
    public Livro() {
        // Construtor vazio
    }

    // Construtor com parâmetros para inicializar os atributos do livro
    public Livro(int id, String titulo, String autor, String isbn, String genero, int quantidadeDisponivel, Date dataPublicacao, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.dataPublicacao = dataPublicacao;
        this.preco = preco;
    }

    // Construtor simplificado para criar objetos Livro com informações mínimas
    public Livro(int idLivro, String tituloLivro, String autorLivro, double precoLivro) {
        // Utilizei esse construtor para criar objetos com informações essenciais
        // O restante dos atributos podem ser ajustados conforme necessário
    }

    // Métodos Getter e Setter para acessar e modificar os atributos do livro
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
