package ui;
import logica.Tabuleiro;
import logica.Peca;
import logica.Jogador;

import java.util.List;

public interface JogoUI {
    void mostrarMensagem(String mensagem);
    void mostrarMensagemDelay(String mensagem);
    void mostrarTabuleiro(Tabuleiro tabuleiro);
    void mostrarMao(List<Peca> mao);
    int numeroPecas(int tamanho);
    int escolhaPeca();
    void exibirVencedor(Jogador jogador1, Jogador jogador2);

}
