/**
 * Foi feito um concurso para eleger a letra mais popular de um conjunto
 * de palavras. A letra mais popular é aquela que aparece mais vezes em
 * uma sequência de palavras.
 * 
 * Por exemplo:
 *      - dada a palavra "batata", a letra mais popular é a letra 'a'
 *      (aparece 3 vezes em "batata")
 *      - dadas as palavras "rucula", "cenoura", a letra mais popular é
 *      a letra 'u' (aparece 2 vezes em "rucula" e 1 vez em "cenoura")
 * 
 * Uma palavra para o concurso é formada exclusivamente por letras
 * minúsculas (a-z), sem espaços, números ou caracteres especiais.
 * 
 * Escreva um código que dadas uma ou mais palavras retorne a letra mais
 * popular entre todas as palavras. Caso mais de uma letra seja a mais
 * popular, retorne qualquer uma das letras mais populares.
 */
public class LetraPopular {

    char letraPopular(String... palavras) {
        int[] quantidades = new int[26];
        for (String palavra : palavras) {
            for (int i = 0; i < palavra.length(); i++) {
                quantidades[palavra.charAt(i) - 'a']++;
            }
        }
        int index = 0;
        for (int i = 1; i < 26; i++) {
            if (quantidades[i] > quantidades[index]) {
                index = i;
            }
        }
        return (char) (index + 'a');
    }
    
}
