import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Um hospital está informatizando seus serviços para conseguir atender
 * melhor os pacientes que chegam. Devido a uma legislação recente, o
 * hospital é obrigado a atender pacientes preferenciais antes de todos os
 * demais, não importa quantas pessoas não preferenciais estejam na fila.
 * O controle de senhas antes era feito com papel, o que causava confusão
 * para os funcionários e reclamação por parte dos pacientes.
 * 
 * Um sistema foi solicitado pelo hospital para que esse controle seja
 * automático. Foi convencionado que as senhas regulares terão o formato:
 * 
 *          "PAC" + sequência de pacientes com 3 digitos
 * 
 * onde a sequência de pacientes é um número incremental. Para pacientes
 * preferenciais o formato da senha é:
 * 
 *          "PRF" + sequência de pacientes preferenciais com 3 digitos
 * 
 * Um dos desenvolvedores da empresa onde você trabalha mapeou as
 * necessidades do hospital nos seguintes casos de teste.
 * 
 * Implemente FilaHospital de modo a atender a todos os casos abaixo, sem
 * alterar os casos de teste.
 */
public class FilaHospitalTest {
    
    private FilaHospital filaHospital;
    
    @Test
    public void pegarSenha() {
        this.filaHospital.pegarSenha();
        String senha = this.filaHospital.pegarSenha();
        assertEquals("PAC002", senha);
    }
    
    @Test
    public void pegarSenhaPreferencial() {
        this.filaHospital.pegarSenhaPreferencial();
        this.filaHospital.pegarSenhaPreferencial();
        String senha = this.filaHospital.pegarSenhaPreferencial();
        assertEquals("PRF003", senha);
    }
    
    @Test
    public void pegarSenhasVariadas() {
        this.filaHospital.pegarSenha();
        this.filaHospital.pegarSenha();
        this.filaHospital.pegarSenha();
        this.filaHospital.pegarSenhaPreferencial();
        String senha = this.filaHospital.pegarSenhaPreferencial();
        assertEquals("PRF002", senha);
    }
    
    @Test
    public void chamarSenhaNormal() throws NinguemNaFilaException {
        this.filaHospital.pegarSenha();
        this.filaHospital.pegarSenha();
        String senha = this.filaHospital.chamarSenha();
        assertEquals("PAC001", senha);
        senha = this.filaHospital.chamarSenha();
        assertEquals("PAC002", senha);
    }
    
    @Test
    public void chamarSenhaPreferencial() throws NinguemNaFilaException {
        this.filaHospital.pegarSenhaPreferencial();
        this.filaHospital.pegarSenhaPreferencial();
        String senha = this.filaHospital.chamarSenha();
        assertEquals("PRF001", senha);
        senha = this.filaHospital.chamarSenha();
        assertEquals("PRF002", senha);
    }
    
    @Test
    public void chamarSenhaPreferencialSeTiverOutrasPessoas() throws NinguemNaFilaException {
        this.filaHospital.pegarSenha();
        this.filaHospital.pegarSenhaPreferencial();
        String senha = this.filaHospital.chamarSenha();
        assertEquals("PRF001", senha);
        senha = this.filaHospital.chamarSenha();
        assertEquals("PAC001", senha);
    }
    
    @Test(expected = NinguemNaFilaException.class)
    public void erroAoChamarSenhaSemNinguemNaFila() throws NinguemNaFilaException {
        this.filaHospital.pegarSenha();
        this.filaHospital.chamarSenha();
        this.filaHospital.chamarSenha();
    }
    
    @Before
    public void setup() {
        this.filaHospital = new FilaHospital();
    }

}