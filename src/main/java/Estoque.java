import java.util.HashMap;
import java.util.Map;

public class Estoque {
    
    private Map<String, Integer> produtosEstocados;
    
    public Estoque() {
        this.produtosEstocados = new HashMap<>();
    }

    void adiciona(Integer quantidade, String item) {
        if (!this.produtosEstocados.containsKey(item)) {
            this.produtosEstocados.put(item, 0);
        }
        this.produtosEstocados.put(item, this.produtosEstocados.get(item) + quantidade);
    }
    
    Integer quantidadeDe(String item) {
        if (!this.produtosEstocados.containsKey(item)) {
            return 0;
        }
        return this.produtosEstocados.get(item);
    }
    
    void retirar(Integer quantidade, String item) throws ProdutoNaoCadastradoException, EstoqueInsuficienteException {
        if (!this.produtosEstocados.containsKey(item)) {
            throw new ProdutoNaoCadastradoException();
        }
        Integer quantidadeEmEstoque = this.produtosEstocados.get(item);
        if (quantidadeEmEstoque < quantidade) {
            throw new EstoqueInsuficienteException();
        }
        this.produtosEstocados.put(item, quantidadeEmEstoque - quantidade);
    }
}

class ProdutoNaoCadastradoException extends Exception {
    
}

class EstoqueInsuficienteException extends Exception {
    
}