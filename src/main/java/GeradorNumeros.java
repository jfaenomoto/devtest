/**
 * Todo número inteiro possui um número "mágico" associado a ele. Um
 * número mágico de um número inteiro é considerado o resto da divisão
 * da soma de todos os seus algarismos por 7.
 * 
 * Por exemplo:
 *      - o número mágico de "5" é 5, pois o resto da divisão de 5
 *          por 7 é 5
 *      - o número mágico de "98" é 3, pois 9 + 8 = 17, e o resto da
 *          divisão de 17 por 7 é 3
 *      - o número mágico de "186" é 1, pois 1 + 8 + 6 = 15, e o resto
 *          da divisão de 15 por 7 é 1
 * 
 * Escreva um código que dada a representação de um número inteiro
 * estritamente positivo em forma de string retorne o seu número mágico.
 * O parâmetro da função será composto exclusivamente por caracteres
 * númericos (0-9), sem pontos ou espaços.
 */
public class GeradorNumeros {

    Integer numeroMagico(String numero) {
        int numeroMagico = 0;
        for (int i = 0; i < numero.length(); i++) {
            numeroMagico += numero.charAt(i) - '0';
            numeroMagico %= 7;
        }
        return numeroMagico;
    }
    
}
