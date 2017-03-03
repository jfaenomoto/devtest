import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Você foi escalado para fazer um sistema de cupons de um site de compras coletivas. A idéia é que
 * um cliente interessado em uma oferta gere um cupom e o estabelecimento comercial cadastrado dê baixa
 * nesse cupom usando um sistema. Os códigos dos cupons gerados devem ser no seguinte formato:
 * 
 *      oferta + "-" + número
 * 
 * onde oferta é uma string representando a oferta do site e o número é um contador específico para aquela
 * oferta.
 * 
 * O sistema precisa ser a prova de fraudes: não deve ser possível validar um cupom gerado na mão sem usar o
 * sistema, ou permitir que um cupom seja usado duas vezes.
 * 
 * Um desenvolvedor foi contratado para especificar os casos de teste que esse sistema deve passar para que
 * o trabalho seja considerado entregue. Crie uma classe SistemaCupom que atenda a todos os casos, sem alterar
 * o código dessa classe de teste
 */
public class SistemaCupomTest {
    
    private SistemaCupom sistemaCupom;

    @Test
    public void gerarCupomOferta() {
        String cupom = this.sistemaCupom.gerarCupom("Barbearia");
        
        assertEquals("Barbearia-1", cupom);
    }
    
    @Test
    public void gerarVariosCuponsOferta() {
        this.sistemaCupom.gerarCupom("Massagem");
        this.sistemaCupom.gerarCupom("Massagem");
        String cupom = this.sistemaCupom.gerarCupom("Massagem");
        
        assertEquals("Massagem-3", cupom);
    }
    
    @Test
    public void gerarCupomMaisDeUmaOferta() {
        this.sistemaCupom.gerarCupom("Hotel");
        String cupom = this.sistemaCupom.gerarCupom("Restaurante");
        
        assertEquals("Restaurante-1", cupom);
    }
    
    @Test
    public void consumirCupomGerado() {
        String cupom = this.sistemaCupom.gerarCupom("Hotel");
        try {
            this.sistemaCupom.consumirCupom(cupom);
        } catch (CupomInexistenteException e) {
            fail("Não deveria falhar ao consumir cupom gerado");
        }
    }
    
    @Test(expected = CupomInexistenteException.class)
    public void erroAoConsumirCupomNaoGerado() throws CupomInexistenteException {
        this.sistemaCupom.consumirCupom("Spa-15");
    }
    
    @Test(expected = CupomInexistenteException.class)
    public void erroAoConsumirCupomJaConsumido() throws CupomInexistenteException {
        String cupom = this.sistemaCupom.gerarCupom("Eletrodoméstico");
        try {
            this.sistemaCupom.consumirCupom(cupom);
        } catch (CupomInexistenteException e) {
            // não fazer nada, cupom foi usado com sucesso
        }
        this.sistemaCupom.consumirCupom(cupom);
    }
    
    @Before
    public void setup() {
        this.sistemaCupom = new SistemaCupom();
    }
}
