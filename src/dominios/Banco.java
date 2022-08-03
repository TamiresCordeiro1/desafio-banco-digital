package dominios;

import java.util.Date;

public class Banco {

    private String nome;

    private String cnpj;

    private Date dataCriacao;

    private String codigo;

    public Banco() {
        this.nome = "(InterDio)";
        this.cnpj = "32176274-0001/01";
        this.dataCriacao = new Date();
        this.codigo = "0001";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
