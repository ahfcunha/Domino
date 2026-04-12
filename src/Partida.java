import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    Tabuleiro tabuleiro = new Tabuleiro();
    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    ArrayList<Peca> monte = Peca.gerarPecas();
    Scanner scanner = new Scanner(System.in);

    public void preparar(){
        System.out.println("---INÍCIO DO JOGO---");
        int tamanho = monte.size();
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
        jogador1.distribuir(quantidadePecas, monte);
        jogador2.distribuir(quantidadePecas, monte);

    }

    public boolean fazerJogada(Jogador jogador, String nome){
        System.out.println();
        System.out.println(tabuleiro);
        try{
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("\nVEZ DE: " + nome);
        jogador.imprimirMao();
        if(tabuleiro.verificarJogada(jogador.getMao())){
            boolean jogadaValida = false;
            while(!jogadaValida){
                System.out.println("\nQual peça você deseja jogar?");
                int n = scanner.nextInt();
                int indice = n - 1;
                if (indice >= 0 && indice < jogador.getMao().size()) {
                    if (tabuleiro.addPeca(indice, jogador.getMao())) {
                        jogador.removerPeca(indice);
                        jogadaValida = true;
                    } else {
                        System.out.println("\nNÃO É POSSÍVEL ADICIONAR ESSA PEÇA AO TABULEIRO!");
                    }
                } else {
                    System.out.println("\nÍNDICE INVÁLIDO!");
                }
            }
            return true;
        }
        else{
            System.out.println("\nVOCÊ NÃO POSSUI JOGADAS DISPONÍVEIS, PULOU A VEZ!");
            return false;
        }
    }

    public void exibirVencedor(){
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


    public void iniciarPartida(){
        preparar();

        while (!jogador1.maoVazia() && !jogador2.maoVazia()) {
            boolean j1Jogou = fazerJogada(jogador1, "JOGADOR 1");

            if (jogador1.maoVazia()) break;


            boolean j2Jogou = fazerJogada(jogador2, "JOGADOR 2");

            if (!j1Jogou && !j2Jogou) {
                System.out.println("FECHOU O JOGO!!");
                break;
            }
        }
        System.out.println("\n--- FIM DE JOGO ---");
        exibirVencedor();
    }

}
