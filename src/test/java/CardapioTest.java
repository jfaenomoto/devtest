import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Um importante restaurante está tentando modernizar seu serviço
 * tentando oferecer um cardápio online onde os clientes consigam ver
 * facilmente quais os pratos disponíveis. O sistema deve permitir que
 * o gerente cadastre facilmente novas receitas bem como seus ingredientes.
 * A idéia é que a pessoa que vai jantar consiga buscar todas as receitas
 * que possuem um determinado ingrediente (caso ela queira comer algo
 * mais específico) ou que não contenha um ingrediente (ex: vegetarianos
 * que querem ver os pratos sem carne).
 * 
 * O nome de um prato é representado por uma String. Os ingredientes de
 * uma receita também são representados por Strings e uma receita pode ter
 * 1 ou mais ingredientes.
 * 
 * Uma consultoria foi contratada para escrever os casos de teste a
 * seguir, com as regras de negócio que o sistema de cardápio deve seguir.
 * 
 * Implemente um sistema Cardapio que atenda a todos os casos de teste
 * a seguir. Os casos de teste abaixo não devem ser alterados.
 */
public class CardapioTest {
    
    private Cardapio cardapio;

    @Test
    public void pegarReceitaCadastrada() {
        this.cardapio.cadastraReceita("Cachorro-quente", "Pão", "Salsicha");
        
        assertTrue(this.cardapio.receitas().contains("Cachorro-quente"));
    }
    
    @Test
    public void pegarVariasReceitasCadastradas() {
        this.cardapio.cadastraReceita("Limonada", "Água", "Limão", "Açúcar");
        this.cardapio.cadastraReceita("Suco de laranja", "Laranja");
        this.cardapio.cadastraReceita("Água com gás", "Água", "Gás");
        
        assertTrue(this.cardapio.receitas().contains("Limonada"));
        assertTrue(this.cardapio.receitas().contains("Suco de laranja"));
        assertTrue(this.cardapio.receitas().contains("Água com gás"));
    }
    
    @Test
    public void pegarIngredientesReceita() throws ReceitaNaoCadastradaException {
        this.cardapio.cadastraReceita("Guacamole", "Abacate", "Tomate", "Cebola", "Limão");
        
        assertTrue(this.cardapio.ingredientesDe("Guacamole").contains("Abacate"));
        assertTrue(this.cardapio.ingredientesDe("Guacamole").contains("Tomate"));
        assertTrue(this.cardapio.ingredientesDe("Guacamole").contains("Cebola"));
        assertTrue(this.cardapio.ingredientesDe("Guacamole").contains("Limão"));
    }
    
    @Test(expected = ReceitaNaoCadastradaException.class)
    public void erroAoPegarIngredientesReceitaNaoCadastrada() throws ReceitaNaoCadastradaException {
        this.cardapio.cadastraReceita("Chili", "Carne", "Feijão", "Molho de tomate");
        
        this.cardapio.ingredientesDe("Quesadilla");
    }
    
    @Test
    public void pegarTodasReceitasComIngrediente() {
        this.cardapio.cadastraReceita("Hamburguer", "Pão", "Carne");
        this.cardapio.cadastraReceita("Cheeseburguer", "Pão", "Carne", "Queijo");
        this.cardapio.cadastraReceita("Misto quente", "Pão", "Presunto", "Queijo");
        this.cardapio.cadastraReceita("Pão na chapa", "Pão", "Manteiga");
        
        assertTrue(this.cardapio.receitasCom("Queijo").contains("Cheeseburguer"));
        assertTrue(this.cardapio.receitasCom("Queijo").contains("Misto quente"));
    }
    
    @Test
    public void pegarTodasReceitasSemIngrediente() {
        this.cardapio.cadastraReceita("Hamburguer", "Pão", "Carne");
        this.cardapio.cadastraReceita("Cheeseburguer", "Pão", "Carne", "Queijo");
        this.cardapio.cadastraReceita("Misto quente", "Pão", "Presunto", "Queijo");
        this.cardapio.cadastraReceita("Pão na chapa", "Pão", "Manteiga");
        
        assertTrue(this.cardapio.receitasSem("Carne").contains("Misto quente"));
        assertTrue(this.cardapio.receitasSem("Carne").contains("Pão na chapa"));
    }
    
    @Before
    public void setup() {
        this.cardapio = new Cardapio();
    }
    
}
