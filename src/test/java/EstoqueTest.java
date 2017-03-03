import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Os frequentes problemas com controle de estoque de um almoxarifado
 * de um hospital levaram os gestores a decidir pela implantação de um
 * sistema de controle de estoque.
 * 
 * O sistema deve permitir o cadastro de produtos (representados por uma
 * String), consulta da quantidade de um determinado item e a baixa de
 * itens retirados do estoque.
 * 
 * É importante informar ao funcionário usando o sistema se o produto
 * que ele está tentando retirar de estoque não foi previamente
 * cadastrado ou se a quantidade de itens que ele está tentando retirar
 * é maior do que o disponível em estoque.
 * 
 * Os casos de teste abaixo foram codificados por um colega de trabalho
 * que tentou captar os requisitos do hospital para o sistema.
 * 
 * Sem alterar os casos de teste abaixo, codifique a classe Estoque para
 * que atenda a todos os casos.
 */
public class EstoqueTest {

    private Estoque estoque;
    
    @Test
    public void adicionaEstoque() {
        this.estoque.adiciona(2, "Lixeira");
        assertEquals(2, (int) this.estoque.quantidadeDe("Lixeira"));
    }
    
    @Test
    public void adicionaEstoqueExistente() {
        this.estoque.adiciona(1, "Vassoura");
        this.estoque.adiciona(3, "Vassoura");
        assertEquals(4, (int) this.estoque.quantidadeDe("Vassoura"));
    }
    
    @Test
    public void adicionaEstoqueVariosItens() {
        this.estoque.adiciona(2, "Detergente");
        this.estoque.adiciona(3, "Pano de chão");
        this.estoque.adiciona(3, "Par de luvas");
        assertEquals(2, (int) this.estoque.quantidadeDe("Detergente"));
        assertEquals(3, (int) this.estoque.quantidadeDe("Pano de chão"));
        assertEquals(3, (int) this.estoque.quantidadeDe("Par de luvas"));
    }
    
    @Test
    public void quantidadeDeItemNaoCadastrado() {
        assertEquals(0, (int) this.estoque.quantidadeDe("Balde"));
    }
    
    @Test
    public void retirarEstoque() throws ProdutoNaoCadastradoException, EstoqueInsuficienteException {
        this.estoque.adiciona(3, "Vassoura");
        this.estoque.retirar(1, "Vassoura");
        assertEquals(2, (int) this.estoque.quantidadeDe("Vassoura"));
    }
    
    @Test(expected = ProdutoNaoCadastradoException.class)
    public void erroAoRetirarProdutoNaoCadastrado() throws ProdutoNaoCadastradoException, EstoqueInsuficienteException {
        this.estoque.retirar(2, "Pá");
    }
    
    @Test(expected = EstoqueInsuficienteException.class)
    public void erroAoRetirarProdutoSemEstoqueSuficiente() throws ProdutoNaoCadastradoException, EstoqueInsuficienteException {
        this.estoque.adiciona(2, "Saco de lixo");
        this.estoque.retirar(3, "Saco de lixo");
    }
    
    @Before
    public void setup() {
        this.estoque = new Estoque();
    }
}
