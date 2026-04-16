package logica;

import ui.ConsoleUI;
import ui.JogoUI;

import java.util.ArrayList;

public class Partida {
    JogoUI ui = new ConsoleUI();
    Tabuleiro tabuleiro = new Tabuleiro(ui);
    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    ArrayList<Peca> monte = Peca.gerarPecas();


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

    public void fazerJogada(Jogador jogador){
        boolean jogadaValida = false;
        while(!jogadaValida){
            int n = ui.escolhaPeca();
            int indice = n - 1;
            if (indice >= 0 && indice < jogador.getMao().size()) {
                if (tabuleiro.addPeca(indice, jogador.getMao())) {
                    jogador.removerPeca(indice);
                    jogadaValida = true;
                } else {
                    ui.mostrarMensagem("\nVOCÊ NÃO PODE ADICIONAR ESSA PEÇA!");
                }
            } else {
                ui.mostrarMensagem("\nÍNDICE INVÁLIDO!");
            }
        }
    }

    public boolean jogarSemCompra(Jogador jogador, String nome){
        ui.mostrarTabuleiro(tabuleiro);
        ui.mostrarMensagemDelay("\nVEZ DE: " + nome);
        ui.mostrarMao(jogador.getMao());
        if(tabuleiro.verificarJogada(jogador.getMao())){
            fazerJogada(jogador);
            return true;
        }
        else{
            ui.mostrarMensagem("\nVOCÊ NÃO POSSUI JOGADAS DISPONÍVEIS, PULOU A VEZ!");
            return false;
        }
    }

    public boolean jogarComCompra(Jogador jogador, String nome){
        ui.mostrarTabuleiro(tabuleiro);
        ui.mostrarMensagemDelay("\nVEZ DE: " + nome);
        ui.mostrarMao(jogador.getMao());
        if(tabuleiro.verificarJogada(jogador.getMao())){
            fazerJogada(jogador);
            return true;
        }
        else{
            if(!monte.isEmpty()){
                while(!tabuleiro.verificarJogada(jogador.getMao())){
                    ui.mostrarMensagem("\nComprando...");
                    ui.mostrarMensagemDelay("");
                    Peca pecaComprada = jogador.comprarPeca(monte);
                    jogador.getMao().add(pecaComprada);
                    ui.mostrarMao(jogador.getMao());
                    if(monte.isEmpty()){
                        ui.mostrarMensagem("O monte de compras acabou, pulou a vez!!");
                        return false;
                    }
                }
                fazerJogada(jogador);
                return true;
            }
            ui.mostrarMensagem("\nVOCÊ NÃO POSSUI JOGADAS DISPONÍVEIS, PULOU A VEZ!");
            return false;
        }
    }

    public void iniciarPartida(){
        preparar();
        boolean compraHabilitada = ui.habilitarCompras();
        boolean j1Jogou, j2Jogou;

        while (!jogador1.maoVazia() && !jogador2.maoVazia()) {
            if(compraHabilitada) {
                j1Jogou = jogarComCompra(jogador1, "JOGADOR 1");
            }
            else{
                j1Jogou = jogarSemCompra(jogador1, "JOGADOR 1");
            }

            if (jogador1.maoVazia()) break;


            if(compraHabilitada) {
                j2Jogou = jogarComCompra(jogador2, "JOGADOR 2");
            }
            else{
                j2Jogou = jogarSemCompra(jogador2, "JOGADOR 2");
            }

            if (!j1Jogou && !j2Jogou) {
                ui.mostrarMensagem("FECHOU O JOGO!!");
                break;
            }
        }
        ui.mostrarMensagem("\n--- FIM DE JOGO ---");
        ui.exibirVencedor(jogador1, jogador2);
    }

}
