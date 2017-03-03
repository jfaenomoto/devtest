import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cardapio {
    
    private Map<String, List<String>> receitas;
    
    public Cardapio() {
        receitas = new HashMap<>();
    }

    void cadastraReceita(String nome, String... ingredientes) {
        this.receitas.put(nome, Arrays.asList(ingredientes));
    }
    
    List<String> receitas() {
        return new ArrayList<>(this.receitas.keySet());
    }
    
    List<String> ingredientesDe(String receita) throws ReceitaNaoCadastradaException {
        if (!this.receitas.containsKey(receita)) {
            throw new ReceitaNaoCadastradaException();
        }
        return this.receitas.get(receita);
    }
    
    List<String> receitasCom(String ingrediente) {
        List<String> receitasComIngrediente = new ArrayList<>();
        for (String nomeReceita : this.receitas.keySet()) {
            List<String> ingredientesReceita = this.receitas.get(nomeReceita);
            for (String ingredienteNaReceita : ingredientesReceita) {
                if (ingrediente.equals(ingredienteNaReceita)) {
                    receitasComIngrediente.add(nomeReceita);
                }
            }
        }
        return receitasComIngrediente;
    }
    
    List<String> receitasSem(String ingrediente) {
        List<String> receitasComIngrediente = new ArrayList<>();
        for (String nomeReceita : this.receitas.keySet()) {
            List<String> ingredientesReceita = this.receitas.get(nomeReceita);
            boolean temIngrediente = false;
            for (String ingredienteNaReceita : ingredientesReceita) {
                if (ingrediente.equals(ingredienteNaReceita)) {
                    temIngrediente = true;
                    break;
                }
            }
            if (!temIngrediente) {
                receitasComIngrediente.add(nomeReceita);
            }
        }
        return receitasComIngrediente;
    }
    
}

class ReceitaNaoCadastradaException extends Exception {
    
}
