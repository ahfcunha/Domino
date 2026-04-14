package logica;

import java.util.ArrayList;

public class Peca {
    private int valorA;
    private int valorB;

    public Peca (int valorA, int valorB){
        this.valorA = valorA;
        this.valorB = valorB;
    }

    public int getValorA (){
        return this.valorA;
    }

    public int getValorB (){
        return this.valorB;
    }

    public void mudarOrientacao(){
        int aux = valorB;
        valorB = valorA;
        valorA = aux;
    }

    public static ArrayList<Peca> gerarPecas(){
        ArrayList<Peca> monte = new ArrayList<>();
        for(int i = 0; i<= 6; i++){
            for(int j = i; j <= 6; j++){
                monte.add(new Peca(i, j));
            }
        }
        return monte;
    }
}
