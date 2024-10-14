package util;

import java.util.List;

import algoritmo.Cromossomo;
import model.Item;
import model.Mochila;

public class Aptidao {
    public static int calcularAptidao(Cromossomo cromossomo, Mochila mochila) {
        int valorTotal = 0;
        int pesoTotal = 0;

        int[] genes = cromossomo.getGenes();
        List<Item> itens = mochila.getItens();

        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 1) {
                pesoTotal += itens.get(i).getPeso();
                valorTotal += itens.get(i).getValor();
            }
        }

        return (pesoTotal > mochila.getCapacidade()) ? 0 : valorTotal;
    }
}
