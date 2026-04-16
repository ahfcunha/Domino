package ui;

import logica.Jogador;
import logica.Peca;
import logica.Tabuleiro;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements JogoUI{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void mostrarMensagemDelay(String mensagem) {
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(mensagem);
    }

    @Override
    public void mostrarTabuleiro(Tabuleiro tabuleiro) {
        System.out.println();
        System.out.println(tabuleiro);
    }

    @Override
    public void mostrarMao(List<Peca> mao) {
        for (Peca p : mao) {
            System.out.print("  " + p.getValorA() + "  ");
        }
        System.out.println();

        for (Peca p : mao) {
            System.out.print(" --- ");
        }
        System.out.println();

        for (Peca p : mao) {
            System.out.print("  " + p.getValorB() + "  ");
        }
        System.out.println();
    }

    @Override
    public int numeroPecas(int tamanho) {
        int quantidadePecas = 0;
        boolean quantidadeValida = false;
        while(!quantidadeValida){
            System.out.println("Quantas peças para cada jogador?");
            quantidadePecas = scanner.nextInt();
            if(quantidadePecas*2 < tamanho){
                quantidadeValida = true;
            }
            else{
                System.out.println("Cada jogador pode receber no máximo  " + tamanho/2 + " peças!");
            }
        }
        return quantidadePecas;
    }

    @Override
    public int escolhaPeca() {
        System.out.println("Escolha a peça que você deseja jogar!");
        int n = scanner.nextInt();
        return n;
    }

    @Override
    public void exibirVencedor(Jogador jogador1, Jogador jogador2) {
        if(jogador1.maoVazia()) System.out.println("VENCEDOR: JOGADOR 1");
        else if(jogador2.maoVazia()) System.out.println("VENCEDOR: JOGADOR 2");
        else if(jogador1.somarPontos() < jogador2.somarPontos()){
            System.out.println("VENCEDOR: JOGADOR 1");
            System.out.println("Jogador 1: " + jogador1.somarPontos() + " pontos");
            System.out.println("Jogador 2: " + jogador2.somarPontos() + " pontos");
        }
        else if(jogador2.somarPontos() < jogador1.somarPontos()){
            System.out.println("VENCEDOR: JOGADOR 2");
            System.out.println("Jogador 2: " + jogador2.somarPontos() + " pontos");
            System.out.println("Jogador 1: " + jogador1.somarPontos() + " pontos");
        }
        else{
            System.out.println("EMPATE");
            System.out.println("Jogador 1: " + jogador1.somarPontos() + " pontos");
            System.out.println("Jogador 2: " + jogador2.somarPontos() + " pontos");
        }
    }

    @Override
    public int escolherLado() {
        System.out.println("\nEm que lado você deseja colocar a peça?\n0 - Esquerda\n1 Direita");
        int lado = scanner.nextInt();
        return lado;
    }

    @Override
    public boolean habilitarCompras() {
        System.out.println("Quer jogar com compras?\n0 - sim\n1 - não");
        int compraHabilitada = scanner.nextInt();
        if(compraHabilitada == 0) return true;
        else return false;
    }
}
