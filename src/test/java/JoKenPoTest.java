import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class JoKenPoTest {

    private JoKenPo joKenPo;
    
    @Test
    public void testaVitoriaJogadorA() {
        String vencedor = this.joKenPo.ganhador("Tesoura-Papel");
        assertEquals("Jogador A", vencedor);
    }
    
    @Test
    public void testaVitoriaJogadorB() {
        String vencedor = this.joKenPo.ganhador("Pedra-Papel");
        assertEquals("Jogador B", vencedor);
    }
    
    @Test
    public void testaEmpate() {
        String vencedor = this.joKenPo.ganhador("Pedra-Pedra");
        assertEquals("Empate", vencedor);
    }
    
    @Test
    public void testaVencedorVariasPartidas() {
        String vencedor = this.joKenPo.ganhador("Tesoura-Tesoura", "Pedra-Papel", "Papel-Tesoura");
        assertEquals("Jogador B", vencedor);
    }
    
    @Test
    public void testaEmpateVariasPartidas() {
        String vencedor = this.joKenPo.ganhador("Tesoura-Papel", "Pedra-Papel");
        assertEquals("Empate", vencedor);
    }
    
    @Before
    public void setup() {
        this.joKenPo = new JoKenPo();
    }
}
