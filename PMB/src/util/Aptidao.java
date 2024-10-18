package util;

import java.util.List;
import algoritmo.Individuo;
import model.Item;

public class Aptidao {
    public static int calcularAptidao(Individuo individuo, List<Item> itens) {
        int ganhoTotal = 0;
        int pesoTotal = 0;
        List<Integer> genes = individuo.getGenes();

        for (int i = 0; i < genes.size(); i++) {
            if (genes.get(i) == 1) {
                Item item = itens.get(i);
                pesoTotal += item.getPeso();

                if (pesoTotal > individuo.getMochila().getCapacidade()) {
                    return 0;
                }

                ganhoTotal += item.getGanho();
                individuo.adicionarItem(item);
            }
        }

        return ganhoTotal;
    }
}
