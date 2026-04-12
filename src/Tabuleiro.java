import java.util.ArrayDeque;
import java.util.ArrayList;

public class Tabuleiro {
    private ArrayDeque<Peca> t = new ArrayDeque<>();

    public boolean addPeca(int indice, ArrayList<Peca> mao) {
        Peca p = mao.get(indice);

        if (t.isEmpty()) {
            t.add(p);
            return true;
        }

        int pontaEsquerda = t.peekFirst().getValorA();

        int pontaDireita = t.peekLast().getValorB();


        if (p.getValorB() == pontaEsquerda) {
            t.addFirst(p);
            return true;
        } else if (p.getValorA() == pontaEsquerda) {
            p.mudarOrientacao();
            t.addFirst(p);
            return true;
        }



        if (p.getValorA() == pontaDireita) {
            t.addLast(p);
            return true;
        } else if (p.getValorB() == pontaDireita) {
            p.mudarOrientacao();
            t.addLast(p);
            return true;
        }

        return false;
    }

    public boolean verificarJogada (ArrayList<Peca> mao){
        for(Peca p : mao){
            int lado = p.getValorA();
            if(t.isEmpty()){
                return true;
            }

            int pontaEsquerda = t.peekFirst().getValorA();
            if(pontaEsquerda == lado){
                return true;
            }

            int pontaDireita = t.peekLast().getValorB();
            if(pontaDireita == lado){
                return true;
            }
            p.mudarOrientacao();
            lado = p.getValorA();
            if(t.isEmpty()){
                return true;
            }

            pontaEsquerda = t.peekFirst().getValorA();
            if(pontaEsquerda == lado){
                return true;
            }

            pontaDireita = t.peekLast().getValorB();
            if(pontaDireita == lado){
                return true;
            }
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
