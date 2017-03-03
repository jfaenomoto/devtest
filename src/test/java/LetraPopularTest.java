import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class LetraPopularTest {

    private LetraPopular letra;
    
    @Test
    public void testaBatata() {
        assertEquals('a', (int) this.letra.letraPopular("batata"));
    }
    
    @Test
    public void testaVegetais() {
        assertEquals('u', (int) this.letra.letraPopular("rucula", "cenoura"));
    }
    
    @Before
    public void setup() {
        this.letra = new LetraPopular();
    }
}
