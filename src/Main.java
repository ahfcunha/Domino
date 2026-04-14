import logica.Partida;
import ui.ConsoleUI;
import ui.JogoUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JogoUI ui = new ConsoleUI();
        Partida partida = new Partida(ui);
        partida.iniciarPartida();
    }
}