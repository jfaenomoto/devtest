import java.util.ArrayList;
import java.util.List;


public class LeitorLivros {
    
    private List<String> livrosLidos;
    private int horasDeLeitura = 0;
    
    public LeitorLivros() {
        this.livrosLidos = new ArrayList<>();
    }

    void ler(String livro, Integer horas) {
        if (!this.livrosLidos.contains(livro)) {
            horasDeLeitura += horas;
            this.livrosLidos.add(livro);
        }
    }
    
    Integer horasDeLeitura() {
        return horasDeLeitura;
    }
}
