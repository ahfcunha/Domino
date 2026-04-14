package logica;

import java.util.ArrayDeque;
import java.util.ArrayList;
import ui.JogoUI;

public class Tabuleiro {
    private ArrayDeque<Peca> t = new ArrayDeque<>();
    private JogoUI ui;

    public Tabuleiro(JogoUI ui){
        this.ui = ui;
    }

    public boolean encaixaEsquerda(Peca p){
        int pontaEsquerda = t.peekFirst().getValorA();
        return p.getValorA() == pontaEsquerda || p.getValorB() == pontaEsquerda;
    }

    public boolean encaixaDireita(Peca p){
        int pontaDireita = t.peekLast().getValorB();
        return p.getValorA() == pontaDireita || p.getValorB() == pontaDireita;
    }

    public boolean addPeca(int indice, ArrayList<Peca> mao){
        Peca p = mao.get(indice);

        if (t.isEmpty()) {
            t.add(p);
            return true;
        }
        if(!encaixaDireita(p) && !encaixaEsquerda(p)){
            return false;
        }

        int ladoEscolhido;

        if(encaixaDireita(p) && encaixaEsquerda(p)){
            ladoEscolhido = ui.escolherLado();
        }
        else{
            ladoEscolhido = encaixaEsquerda(p) ? 0 : 1;
        }
        return addPecaLado(ladoEscolhido, p);
    }

    public boolean addPecaLado(int ladoEscolhido, Peca p){
        int pontaEsquerda = t.peekFirst().getValorA();
        int pontaDireita = t.peekLast().getValorB();
        if(ladoEscolhido == 0){
            if(p.getValorA() == pontaEsquerda){
                p.mudarOrientacao();
            }
            t.addFirst(p);
        }
        else{
            if(p.getValorB() == pontaDireita){
                p.mudarOrientacao();
            }
            t.addLast(p);
        }

        return true;
    }

    public boolean verificarJogada(ArrayList<Peca> mao){
        if(t.isEmpty()) return true;

        for(Peca p : mao){
            if(encaixaEsquerda(p) || encaixaDireita(p)) return true;
        }
        return false;
    }



    @Override
    public String toString(){
        String s = "";
        for(Peca p : t){
            s += "[ " + p.getValorA() + " | " + p.getValorB() + " ]";
        }
        return s;
    }

}
