package negocios;

import dominios.Conta;

import java.util.List;
import java.util.Stack;

public class Validacao {

    public static boolean isPossivelCadastrarConta(Conta conta){
        if(!verificarDadosCliente(conta)){
            System.out.println("A cota não pode ser cadastrada!");
            return false;
        }
        return true;
    }

    public static boolean verificarDadosCliente(Conta conta){
        if(conta == null){
            System.out.println("Preencha a conta!");
            return false;
        }

        if(conta.getCliente().getNome().isEmpty()){
            System.out.println("Preencha o Nome!");
            return false;
        }

        if(conta.getCliente().getCpf().isEmpty()){
            System.out.println("Preencha o CPF!");
            return false;
        }

        if(conta.getCliente().getGenero().isEmpty()){
            System.out.println("Preencha o Gênero!");
            return false;
        }

        if(conta.getCliente().getDataNascimento() == null){
            System.out.println("Preencha a data de nascimento!");
            return false;
        }

        if(conta.getNumero().isEmpty()){
            System.out.println("Preencha o número da conta!");
            return false;
        }
        return true;
    }

    public static boolean isPossivelFazerOperacaoBancaria(Conta conta, double valor){
        if(valor <= conta.getSaldo()){
            return true;
        }
        else{
            System.out.println("Verifique o valor, saldo insuficiente!");
            return false;
        }
    }

    public static boolean isPossivelRealizarDeposito(double valor) {
        if(valor > 0) {
            return true;
        } else {
            System.out.println("Valor inválido!");

            return false;
        }
    }

    public static boolean isPossivelPedirCartao(Conta conta){
        if(conta.getSaldo() > 300){
            return true;
        }
        else{
            System.out.println("Solicitação Negada, saldo insuficiente!");
            return false;
        }
    }

    public static boolean isContaExistente(List<Conta> contas, String cpf){
        if(!contas.isEmpty()){
            for(Conta conta : contas){
                if(conta.getCliente().getCpf().equals(cpf)){
                    return true;
                }
            }
        }
        System.out.println("Nenhuma conta encontrada para esse CPF!");
        return false;
    }
}
