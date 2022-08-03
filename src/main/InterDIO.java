package main;

import dominios.Conta;
import dominios.Genero;
import negocios.Transacao;
import negocios.Validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

public class InterDIO {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Transacao transacao = new Transacao();

        List<Conta> contas = new ArrayList<Conta>();

        String opcao = "";

        boolean sair = false;

        Scanner teclado = new Scanner(System.in);

        System.out.println("*------------------------------------------------------------*");
        System.out.println("|Bem vindo ao INTERDIO!                                        |");
        System.out.println("|MENU: criar conta, depositar, sacar e transferir.             |");
        System.out.println("*------------------------------------------------------------*");

        try {
            do {
                System.out.println("*------------------------------------------------------------*");
                System.out.println("|0 - Sair                                                    |");
                System.out.println("|1 - Criar Conta                                             |");
                System.out.println("|2 - Listar Contas                                           |");
                System.out.println("|3 - Exibir Dados pelo CPF                                   |");
                System.out.println("|4 - Visualizar Saldo Pelo CPF                               |");
                System.out.println("|5 - Solicitar Cartão                                        |");
                System.out.println("|6 - Sacar                                                   |");
                System.out.println("|7 - Depositar                                               |");
                System.out.println("|8 - Transferir                                              |");
                System.out.println("*------------------------------------------------------------*");

                System.out.print("Escolhe uma opção: ");
                opcao = teclado.nextLine();

                switch (opcao) {
                    case "0":
                        System.out.println("*------------------------------------------------------------*");
                        System.out.println("|FINALIZANDO PROGRAMA...                                     |");
                        System.out.println("|OBRIGADA!                                                   |");
                        System.out.println("*------------------------------------------------------------*");
                        sair = true;

                        break;
                    case "1":
                        System.out.println("-> CRIAR CONTA");

                        Conta c1 = new Conta();
                        System.out.print("Informe Nome: ");
                        c1.getCliente().setNome(teclado.nextLine());

                        System.out.print("Informe CPF: ");
                        c1.getCliente().setCpf(teclado.nextLine());

                        System.out.print("Informe Gênero -> (Masculino ou Feminino): ");
                        c1.getCliente().setGenero(Genero.modificarStringToGenero(teclado.nextLine()));

                        System.out.print("Informe Data de Nascimento: ");
                        String dataNascimento = teclado.nextLine();

                        if(!dataNascimento.isEmpty()) {
                            c1.getCliente().setDataNascimento(simpleDateFormat.parse(dataNascimento));
                        }

                        if(Validacao.isPossivelCadastrarConta(c1)) {
                            contas.add(c1);

                            System.out.println("Conta criada com sucesso!");
                        }

                        break;
                    case "2":
                        System.out.println("-> LISTAR CONTAS");

                        if(!contas.isEmpty()) {
                            for (Conta conta : contas) {
                                System.out.println("Número:" + conta.getNumero() + "Agência: " + conta.getCodigo());
                            }
                        }

                        break;
                    case "3":
                        System.out.println("-> DADOS BANCÁRIOS");

                        System.out.print("Informe CPF: ");
                        String cpfDados = teclado.nextLine();

                        if(Validacao.isContaExistente(contas, cpfDados)) {
                            for (Conta conta : contas) {
                                if(conta.getCliente().getCpf().equals(cpfDados)) {
                                    transacao.exibirDadosBancarios(conta);
                                }
                            }
                        }

                        break;
                    case "4":
                        System.out.println("-> SALDO");

                        System.out.print("Informe CPF: ");
                        String cpfSaldo = teclado.nextLine();

                        if(Validacao.isContaExistente(contas, cpfSaldo)) {
                            for (Conta conta : contas) {
                                if(conta.getCliente().getCpf().equals(cpfSaldo)) {
                                    System.out.println("O Saldo de: R$ " + conta.getSaldo());
                                }
                            }
                        }
                        break;
                    case "5":
                        System.out.println("-> SOLICITAR CARTÃO");

                        System.out.print("Informe CPF: ");
                        String cpfCartao = teclado.nextLine();

                        if(Validacao.isContaExistente(contas, cpfCartao)) {
                            for (Conta conta : contas) {
                                if(conta.getCliente().getCpf().equals(cpfCartao)) {
                                    transacao.solictarCartao(conta);
                                }
                            }
                        }

                        break;
                    case "6":
                        System.out.println("-> SAQUE");

                        System.out.print("Informe CPF: ");
                        String cpfSacar = teclado.nextLine();

                        if(Validacao.isContaExistente(contas, cpfSacar)) {
                            String valor;

                            System.out.print("Informe Valor de Saque: R$ ");
                            valor = teclado.nextLine();

                            for (Conta conta : contas) {
                                if(conta.getCliente().getCpf().equals(cpfSacar)) {
                                    transacao.sacar(conta, Double.parseDouble(valor));
                                }
                            }
                        }

                        break;
                    case "7":
                        System.out.println("-> DEPÓSITO");

                        System.out.print("Informe CPF: ");
                        String cpfDeposito = teclado.nextLine();

                        if(Validacao.isContaExistente(contas, cpfDeposito)) {
                            String valor;

                            System.out.print("Informe Valor de Depósito: R$ ");
                            valor = teclado.nextLine();

                            for (Conta conta : contas) {
                                if(conta.getCliente().getCpf().equals(cpfDeposito)) {
                                    transacao.depositar(conta, Double.parseDouble(valor));
                                }
                            }
                        }

                        break;
                    case "8":
                        System.out.println("-> TRANSFERÊNCIA");

                        System.out.print("Informe CPF do depositante: ");
                        String cpfDepositante = teclado.nextLine();

                        System.out.print("Informe o CPF do recebedor: ");
                        String cpfRecebedor = teclado.nextLine();

                        if(Validacao.isContaExistente(contas, cpfDepositante)
                                && Validacao.isContaExistente(contas, cpfRecebedor)) {
                            Conta contaDepositante = null, contaRecebedor = null;
                            String valor;

                            System.out.print("Informe Valor do Depósito: ");
                            valor = teclado.nextLine();

                            for (Conta conta : contas) {
                                if(conta.getCliente().getCpf().equals(cpfDepositante)) {
                                    contaDepositante = conta;
                                }else if (conta.getCliente().getCpf().equals(cpfRecebedor)){
                                    contaRecebedor = conta;
                                }
                            }

                            transacao.transferir(contaDepositante, contaRecebedor, Double.parseDouble(valor));
                        }

                        break;
                    default:
                        break;
                }
            }while(!sair);

        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            teclado.close();
        }
    }
}
