package testes;

import dominios.Conta;
import negocios.Transacao;
import org.junit.Test;

import static org.junit.Assert.*;


public class TransacaoTeste {
    @Test
    public void testeSacar() {
        Conta conta = new Conta();
        conta.setSaldo(200.0);

        Transacao transacao = new Transacao();
        transacao.sacar(conta, 50.0);

        assertTrue(conta.getSaldo() == 150.0);
    }

    @Test
    public void testeNaoSacar() {
        Conta conta = new Conta();
        conta.setSaldo(10.0);

        Transacao transacao = new Transacao();
        transacao.sacar(conta, 60.0);

        assertTrue(conta.getSaldo() == 10.0);
    }

    @Test
    public void testeDepositar() {
        Conta conta = new Conta();

        Transacao transacao = new Transacao();
        transacao.depositar(conta, 200.0);

        assertTrue(conta.getSaldo() == 200.0);
    }

    @Test
    public void testeTransferir() {
        Conta conta1 = new Conta();
        conta1.setSaldo(200.0);

        Conta conta2 = new Conta();

        Transacao transacao = new Transacao();
        transacao.transferir(conta1, conta2, 50.0);

        assertTrue(conta1.getSaldo() == 150.0 && conta2.getSaldo() == 50.0);
    }

    @Test
    public void testeNaoTransferir() {
        Conta conta1 = new Conta();
        conta1.setSaldo(200.0);

        Conta conta2 = new Conta();

        Transacao transacao = new Transacao();
        transacao.transferir(conta1, conta2, 300.0);

        assertTrue(conta1.getSaldo() == 200.0 && conta2.getSaldo() == 0.0);
    }
}
