package app;

import java.io.IOException;
import java.util.List;

import algoritmo.AlgoritmoGenetico;
import algoritmo.Cromossomo;
import model.Item;
import model.Mochila;
import util.ManipuladorArquivo;

public class Main {
    public static void main(String[] args) {
        String arquivoEntrada = "C:\\Users\\evert\\Documents\\pmb\\mochila.txt";

        try {
            Mochila mochila = ManipuladorArquivo.lerDeArquivo(arquivoEntrada);
            exibirMochila(mochila);
            System.out.println();
            
            int M = 50; 
            int Ng = 100;
            double Pc = 0.7;
            double Pm = 0.01;

            AlgoritmoGenetico ag = new AlgoritmoGenetico(mochila, M, Ng, Pc, Pm);
            Cromossomo melhorCromossomo = ag.executar();

            exibirResultadoMelhorCromossomo(melhorCromossomo, mochila);

        } catch (IOException e) {
            System.err.println("Erro ao manipular arquivos: " + e.getMessage());
        }
    }

    public static void exibirMochila(Mochila mochila) {
        System.out.println("Capacidade da mochila: " + mochila.getCapacidade());
        System.out.println("Itens:");
        for (Item item : mochila.getItens()) {
            System.out.println("Valor: " + item.getValor() + ", Peso: " + item.getPeso());
        }
    }

    public static void exibirResultadoMelhorCromossomo(Cromossomo melhorCromossomo, Mochila mochila) {
        System.out.println("Melhor Cromossomo:");
        int valorTotal = 0;
        int pesoTotal = 0;

        int[] genes = melhorCromossomo.getGenes();
        List<Item> itens = mochila.getItens();

        System.out.print("Itens selecionados: ");
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 1) {
                valorTotal += itens.get(i).getValor();
                pesoTotal += itens.get(i).getPeso();
                System.out.print("[Valor: " + itens.get(i).getValor() + ", Peso: " + itens.get(i).getPeso() + "] ");
            }
        }
        System.out.println("\nValor Total: " + valorTotal + ", Peso Total: " + pesoTotal);
    }
}
