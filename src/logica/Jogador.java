package logica;

import java.util.ArrayList;
import java.util.Collections;

public class Jogador {

    private ArrayList<Peca> mao = new ArrayList<>();

    public ArrayList<Peca> getMao(){
        return this.mao;
    }

    public ArrayList<Peca> distribuir (int quantidade, ArrayList<Peca> monte){
        Collections.shuffle(monte);
        for(int i = 0; i < quantidade; i++){
            Peca pecaSorteada = monte.remove(0);
            mao.add(pecaSorteada);
        }
        return mao;

    }

    public Peca removerPeca(int indice){
        Peca p = mao.get(indice);
        mao.remove(indice);
        return p;
    }

    public int somarPontos(){
        int soma = 0;
        for(Peca p : mao){
            soma += p.getValorA();
            soma += p.getValorB();
        }
        return soma;
    }

    public boolean maoVazia(){
        return mao.isEmpty();
    }


}
