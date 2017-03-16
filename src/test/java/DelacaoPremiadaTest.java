
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * O judiciário decidiu implantar um novo sistema para fazer o acompanhamento
 * de um grande processo contra corrupção que está acontecendo no país. Como
 * existem várias pessoas envolvidas no processo e cada uma aponta pessoas
 * envolvidas no esquema fica difícil dizer exatamente quem é o mandante
 * dessa grande quadrilha.
 * 
 * Cada um dos suspeitos envolvidos no esquema decidiu aderir a um esquema de
 * delação premiada, onde a pessoa se declara culpada por participação mas
 * ajuda a polícia a indicar os demais envolvidos em troca de atenuação de
 * sua pena. Um suspeito pode delatar zero ou n pessoas no seguinte formato:
 * 
 *      "Fulano>Ciclano"
 * 
 * que representa que Fulano aponta Ciclano como a pessoa que o incluiu na
 * quadrilha.
 * 
 * Dado um conjunto de delações, o sistema deve encontrar os culpados segundo
 * um dos delatores. Um culpado é uma pessoa que não delatou nenhuma outra
 * pessoa e que direta ou indiretamente foi delatada por uma pessoa.
 * 
 * Exemplo:
 * Dadas as delações:
 * 
 *      "Alberto>Bruna", "Bruna>Claudio", "Bruna>Danilo"
 * 
 * os culpados do esquema segundo Alberto são Cláudio e Danilo, pois ele
 * delatou Bruna, e Bruna por sua vez delatou Claudio e Danilo que não
 * delataram ninguém.
 * 
 * A polícia sabe de antemão que delações circulares não vão acontecer direta
 * ou indiretamente (i.e. "Alberto>Bruna", "Bruna>Alberto" é impossível;
 * "Alberto>Bruna", "Bruna>Claudio", "Claudio>Alberto" também é impossível).
 * 
 * Escreva um sistema que atenda aos testes abaixo, são alterá-los, para que
 * a polícia consiga encontrar os mandantes dos crimes.
 *
 */
public class DelacaoPremiadaTest {

    private DelacaoPremiada delacao;
    
    @Test
    public void listarDelatados() {
        this.delacao.carregarLista("Sergio>Rogerio", "Sergio>Antonio", "Antonio>Marco");
        List<String> delatados = this.delacao.delatadosPor("Sergio");
        assertEquals(2, delatados.size());
        assertTrue(delatados.contains("Rogerio"));
        assertTrue(delatados.contains("Antonio"));
    }
    
    @Test
    public void adicionarDelatados() {
        this.delacao.carregarLista("Alberto>Maria");
        this.delacao.carregarLista("Alberto>Daniel", "Alberto>Luis", "Marcos>Alberto");
        List<String> delatados = this.delacao.delatadosPor("Alberto");
        assertEquals(3, delatados.size());
        assertTrue(delatados.contains("Maria"));
        assertTrue(delatados.contains("Daniel"));
        assertTrue(delatados.contains("Luis"));
    }
    
    @Test
    public void semDelatados() {
        List<String> delatados = this.delacao.delatadosPor("Roberval");
        assertEquals(0, delatados.size());
    }
    
    @Test
    public void verificarCulpado() {
        this.delacao.carregarLista("Jose>Antonieta");
        assertTrue(this.delacao.eCulpado("Antonieta"));
    }
    
    @Test
    public void verificarNaoCulpado() {
        this.delacao.carregarLista("Marcelo>Ricardo", "Andre>Marcelo");
        assertFalse(this.delacao.eCulpado("Marcelo"));
    }
    
    @Test
    public void acharCulpados() {
        this.delacao.carregarLista("Alberto>Bruna", "Bruna>Claudio", "Bruna>Danilo");
        List<String> culpados = this.delacao.culpadosSegundo("Alberto");
        assertEquals(2, culpados.size());
        assertTrue(culpados.contains("Claudio"));
        assertTrue(culpados.contains("Danilo"));
    }
    
    @Test
    public void acharApenasCulpadosSegundoAlguem() {
        this.delacao.carregarLista("Rafael>Bruno", "Rafael>Denis", "Roberval>Daniela");
        List<String> culpados = this.delacao.culpadosSegundo("Rafael");
        assertEquals(2, culpados.size());
        assertFalse(culpados.contains("Daniela"));
    }
    
    @Test
    public void naoAcharCulpadosSeNaoHouveDelacao() {
        this.delacao.carregarLista("Lisandro>Altina", "Pedro>Sandra");
        List<String> culpados = this.delacao.culpadosSegundo("Andre");
        assertEquals(0, culpados.size());
    }
    
    @Before
    public void setup() {
        this.delacao = new DelacaoPremiada();
    }
}
