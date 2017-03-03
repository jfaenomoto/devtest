import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaCupom {
    
    private Map<String, Integer> cupomContador;
    private List<String> cupoms;
    
    public SistemaCupom() {
        this.cupomContador = new HashMap<>();
        this.cupoms = new ArrayList<>();
    }

    String gerarCupom(String oferta) {
        if (oferta == null) {
            throw new IllegalArgumentException();
        }
        if (!this.cupomContador.containsKey(oferta)) {
            this.cupomContador.put(oferta, 1);
        }
        Integer proximoNumeroCupom = this.cupomContador.get(oferta);
        String cupom = oferta + "-" + proximoNumeroCupom;
        this.cupoms.add(cupom);
        this.cupomContador.put(oferta, proximoNumeroCupom + 1);
        return cupom;
    }
    
    void consumirCupom(String codigoCupom) throws CupomInexistenteException {
        if (codigoCupom == null) {
            throw new IllegalArgumentException();
        }
        if (!cupoms.contains(codigoCupom)) {
            throw new CupomInexistenteException();
        }
        cupoms.remove(codigoCupom);
    }
}

class CupomInexistenteException extends Exception {
    
}
