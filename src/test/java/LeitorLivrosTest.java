import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Um pai quer que a filha leia a maior quantidade de livros possível.
 * Ele acredita que através de leitura ela poderá se tornar uma pessoa
 * melhor e viver experiências diferentes (mesmo que no mundo da
 * fantasia). Para tanto ele que criar um pequeno sistema onde a filha
 * cadastra qual livro ela leu e quantas horas ela levou lendo o livro.
 * Assim que ela atingir uma quantidade determinada de horas de leitura
 * ele prometeu levá-la para uma viagem na Disney.
 * 
 * Como o objetivo do pai é maximizar o número de livros diferentes que
 * a filha vai ler, caso a filha cadastre que releu um livro o número
 * de horas de leitura que ela dedicou na segunda leitura em diante deve
 * ser desconsiderada.
 * 
 * O pai é um desenvolvedor de sistemas e escreveu os casos de teste a
 * seguir para delimitar o que o sistema deve ou não fazer.
 * 
 * Sem alterar oo código dessa classe, implemente uma classe LeitorLivros
 * que atenda a todas os casos de uso descritos abaixo.
 */
public class LeitorLivrosTest {

    private LeitorLivros leitor;
    
    @Test
    public void lerUmLivro() {
        this.leitor.ler("A hora da estrela", 12);
        assertEquals(12, (int) this.leitor.horasDeLeitura());
    }
    
    @Test
    public void lerMaisDeUmLivro() {
        this.leitor.ler("A sociedade do anel", 12);
        this.leitor.ler("As duas torres", 10);
        this.leitor.ler("O retorno do rei", 11);
        assertEquals(33, (int) this.leitor.horasDeLeitura());
    }
    
    @Test
    public void lerLivroRepetido() {
        this.leitor.ler("Cinquenta tons de cinza", 15);
        this.leitor.ler("Cinquenta tons de cinza", 3);
        assertEquals(15, (int) this.leitor.horasDeLeitura());
    }
    
    @Test
    public void naoLeuNenhumLivro() {
        assertEquals(0, (int) this.leitor.horasDeLeitura());
    }
    
    @Before
    public void setup() {
        this.leitor = new LeitorLivros();
    }
}
