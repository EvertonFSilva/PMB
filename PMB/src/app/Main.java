package app;

import java.io.IOException;

import algoritmo.AlgoritmoGenetico;
import algoritmo.Individuo;
import model.Mochila;
import util.ManipuladorArquivo;

public class Main {
    public static void main(String[] args) {
        String arquivoEntrada = "C:\\Users\\evert\\Documents\\pmb\\mochila.txt";

        try {
            Mochila mochila = ManipuladorArquivo.lerDeArquivo(arquivoEntrada);
            int numeroGeracoes = 1000;

            AlgoritmoGenetico ag = new AlgoritmoGenetico(mochila, numeroGeracoes);
            ag.exibirMochila();
            
            Individuo melhorIndividuo = ag.executar();
            ag.exibirResultadoMelhorSolucao(melhorIndividuo);

        } catch (IOException e) {
            System.err.println("Erro ao manipular arquivos: " + e.getMessage());
        }
    }
}
