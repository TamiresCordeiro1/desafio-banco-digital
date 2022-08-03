package negocios;

import dominios.Cartao;
import dominios.Conta;
import interfaces.IOperacoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Transacao implements IOperacoes {
    @Override
    public void depositar(Conta conta, double valor) {
        if(Validacao.isPossivelRealizarDeposito(valor)){
            double novoSaldo = conta.getSaldo() + valor;
            System.out.println("Deposito de: R$ " + valor);
        }
    }

    @Override
    public void sacar(Conta conta, double valor){
        if(Validacao.isPossivelFazerOperacaoBancaria(conta, valor)){
            double novoSaldo = conta.getSaldo() - valor;
            conta.setSaldo(novoSaldo);
        }
    }

    @Override
    public void transferir(Conta suaConta, Conta constaDestino, double valor) {
        if(Validacao.isPossivelFazerOperacaoBancaria(suaConta, valor)){
            sacar(suaConta, valor);
            depositar(constaDestino, valor);
            System.out.println("Tranferência de: R$ " + suaConta.getSaldo() + "Para: " + constaDestino.getCliente().getNome());
            System.out.println("Saldo atual: R$ " + suaConta.getSaldo());
        }
    }

    @Override
    public void solictarCartao(Conta conta) throws ParseException {
        if(Validacao.isPossivelPedirCartao(conta)){
            Cartao cartao = new Cartao();
            conta.setCartao(cartao);
            System.out.println("Solicitação aceita!");
        }
    }

    @Override
    public void exibirDadosBancarios(Conta conta) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("Nome: " + conta.getCliente().getNome());
        System.out.println("CPF: " + conta.getCliente().getCpf());
        System.out.println("Gênero: " + conta.getCliente().getGenero());
        System.out.println("Data Nasc.: " + conta.getCliente().getDataNascimento());
        System.out.println("Banco: " + conta.getNome());
        System.out.println("Agência: " + conta.getCodigo());
        System.out.println("Conta: " + conta.getNumero());
    }
}
