import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GeradorNumerosTest {

    private GeradorNumeros gerador;
    
    @Test
    public void testa5() {
        assertEquals(5, (int) this.gerador.numeroMagico("5"));
    }
    
    @Test
    public void testa98() {
        assertEquals(3, (int) this.gerador.numeroMagico("98"));
    }
    
    @Test
    public void testa186() {
        assertEquals(1, (int) this.gerador.numeroMagico("186"));
    }
    
    @Before
    public void setup() {
        this.gerador = new GeradorNumeros();
    }
}
