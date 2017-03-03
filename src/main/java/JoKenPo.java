
/**
 * O torneio mundial de jokenpô precisa de você!
 * 
 * Jokenpô é um jogo entre duas pessoas em que ambas escolhem ao mesmo
 * tempo uma jogada: "Pedra", "Tesoura" e "Papel". Assim que ambos
 * escolhem a sua jogada, o vencedor é decidido:
 * 
 *      - a "Tesoura" corta o "Papel"
 *      - o "Papel" embala a "Pedra"
 *      - a "Pedra" quebra a "Tesoura"
 * 
 * Se ambos jogadores escolherem a mesma jogada, o resultado é um
 * empate.
 * 
 * No campeonato de jokenpô, uma ou mais partidas são disputadas entre
 * dois jogadores. Uma partida é representada por uma String no formato:
 * 
 *      <jogada jogador A> + "-" + <jogada jogador B>
 *       
 * Exemplos:
 *      - "Tesoura-Papel"
 *      - "Pedra-Pedra"
 * 
 * Dada uma ou mais partidas de jokenpô descritas acima, codifique a
 * função ganhador() para que retorne "Jogador A", caso o jogador A
 * tenha vencido a partida, "Jogador B", caso o vencedor seja o jogador
 * B, ou "Empate" se não houve vencedor.
 */
public class JoKenPo {

    String ganhador(String... partidas) {
        int vitoriasJogadorA = 0;
        int vitoriasJogadorB = 0;
        for (String partida : partidas) {
            String[] jogadas = partida.split("-");
            if (jogadas[0].equals(jogadas[1])) {
                // nada, empate
            } else if (
                    (jogadas[0].equals("Tesoura") && jogadas[1].equals("Pedra")) ||
                    (jogadas[0].equals("Pedra") && jogadas[1].equals("Papel")) ||
                    (jogadas[0].equals("Papel") && jogadas[1].equals("Tesoura"))) {
                vitoriasJogadorB++;
            } else {
                vitoriasJogadorA++;
            }
        }
        if (vitoriasJogadorA > vitoriasJogadorB) {
            return "Jogador A";
        } else if (vitoriasJogadorB > vitoriasJogadorA) {
            return "Jogador B";
        } else {
            return "Empate";
        }
    }
    
}
