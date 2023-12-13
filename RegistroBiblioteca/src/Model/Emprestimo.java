package Model;

import java.sql.Date;

public class Emprestimo {

    // Atributos da classe Emprestimo
    private int id;
    private int idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int idUsuario;

    // Construtor vazio necessário para a criação de objetos Emprestimo
    public Emprestimo() {
        // Construtor vazio
    }

    // Construtor com parâmetros para inicializar os atributos do empréstimo
    public Emprestimo(int id, int idLivro, Date dataEmprestimo, Date dataDevolucao, int idUsuario) {
        this.id = id;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.idUsuario = idUsuario;
    }

    // Métodos Getter e Setter para acessar e modificar os atributos do empréstimo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
