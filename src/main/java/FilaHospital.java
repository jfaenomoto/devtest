import java.util.ArrayList;
import java.util.List;

public class FilaHospital {
    
    int contador = 1;
    int contadorPreferencial = 1;
    
    private List<String> senhas;
    private List<String> senhasPreferenciais;
    
    public FilaHospital() {
        senhas = new ArrayList<>();
        senhasPreferenciais = new ArrayList<>();
    }

    String pegarSenha() {
        String senha = String.format("PAC%03d", contador++);
        senhas.add(senha);
        return senha;
    }
    
    String pegarSenhaPreferencial() {
        String senha = String.format("PRF%03d", contadorPreferencial++);
        senhasPreferenciais.add(senha);
        return senha;
    }
    
    String chamarSenha() throws NinguemNaFilaException {
        if (!this.senhasPreferenciais.isEmpty()) {
            return this.senhasPreferenciais.remove(0);
        }
        if (!this.senhas.isEmpty()) {
            return this.senhas.remove(0);
        }
        throw new NinguemNaFilaException();
    }
}

class NinguemNaFilaException extends Exception {
    
}