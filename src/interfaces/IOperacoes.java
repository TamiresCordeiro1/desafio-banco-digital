package interfaces;

import dominios.Conta;

import java.text.ParseException;

public interface IOperacoes {

    public void depositar(Conta conta, double valor);

    public void sacar(Conta conta, double valor);

    public void transferir(Conta suaConta, Conta constaDestino, double valor);

    public void solictarCartao(Conta conta) throws ParseException;

    public void exibirDadosBancarios(Conta conta);
}
