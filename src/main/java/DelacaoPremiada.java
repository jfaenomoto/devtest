import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DelacaoPremiada {

    Map<String, List<String>> delatadosPor;
    
    public DelacaoPremiada() {
        this.delatadosPor = new HashMap<>();
    }
    
    void carregarLista(String...delacoes) {
        for (String delacao : delacoes) {
            String[] pessoas = delacao.split(">");
            if (!this.delatadosPor.containsKey(pessoas[0])) {
                this.delatadosPor.put(pessoas[0], new ArrayList<>());
            }
            this.delatadosPor.get(pessoas[0]).add(pessoas[1]);
        }
    }
    
    List<String> delatadosPor(String delator) {
        if (this.delatadosPor.containsKey(delator)) {
            return this.delatadosPor.get(delator);
        }
        return new ArrayList<>();
    }
    
    boolean eCulpado(String suspeito) {
        return this.delatadosPor.get(suspeito) == null;
    }
    
    List<String> culpadosSegundo(String delator) {
        List<String> culpados = new ArrayList<>();
        List<String> investigados = this.delatadosPor(delator);
        while (!investigados.isEmpty()) {
            List<String> proximosInvestigados = new ArrayList<>();
            for (String investigado : investigados) {
                if (eCulpado(investigado)) {
                    culpados.add(investigado);
                } else {
                    proximosInvestigados.addAll(this.delatadosPor(investigado));
                }
            }
            investigados = proximosInvestigados;
        }
        return culpados;
    }
    
}
