package logica;

import ui.JogoUI;

import java.util.ArrayList;

public class Partida {
    Tabuleiro tabuleiro = new Tabuleiro();
    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    ArrayList<Peca> monte = Peca.gerarPecas();
    private JogoUI ui;

    public Partida(JogoUI ui){
        this.ui = ui;
    }

    public void preparar(){
        ui.mostrarMensagem("---INÍCIO DO JOGO---");
        int tamanho = monte.size();
        int quantidadePecas = ui.numeroPecas(tamanho);
        jogador1.distribuir(quantidadePecas, monte);
        jogador2.distribuir(quantidadePecas, monte);

    }

    public boolean fazerJogada(Jogador jogador, String nome){
        ui.mostrarTabuleiro(tabuleiro);
        ui.mostrarMensagemDelay("\nVEZ DE: " + nome);
        ui.mostrarMao(jogador.getMao());
        if(tabuleiro.verificarJogada(jogador.getMao())){
            boolean jogadaValida = false;
            while(!jogadaValida){
                int n = ui.escolhaPeca();
                int indice = n - 1;
                if (indice >= 0 && indice < jogador.getMao().size()) {
                    if (tabuleiro.addPeca(indice, jogador.getMao())) {
                        jogador.removerPeca(indice);
                        jogadaValida = true;
                    } else {
                        ui.mostrarMensagem("\nNÃO É POSSÍVEL ADICIONAR ESSA PEÇA AO TABULEIRO!");
                    }
                } else {
                    ui.mostrarMensagem("\nÍNDICE INVÁLIDO!");
                }
            }
            return true;
        }
        else{
            ui.mostrarMensagem("\nVOCÊ NÃO POSSUI JOGADAS DISPONÍVEIS, PULOU A VEZ!");
            return false;
        }
    }

    public void iniciarPartida(){
        preparar();

        while (!jogador1.maoVazia() && !jogador2.maoVazia()) {
            boolean j1Jogou = fazerJogada(jogador1, "JOGADOR 1");

            if (jogador1.maoVazia()) break;


            boolean j2Jogou = fazerJogada(jogador2, "JOGADOR 2");

            if (!j1Jogou && !j2Jogou) {
                ui.mostrarMensagem("FECHOU O JOGO!!");
                break;
            }
        }
        ui.mostrarMensagem("\n--- FIM DE JOGO ---");
        ui.exibirVencedor(jogador1, jogador2);
    }

}
