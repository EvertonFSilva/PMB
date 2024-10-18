package algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OperacoesGeneticas {

    public static Individuo crossover(Individuo pai1, Individuo pai2) {
        List<Integer> genesPai1 = pai1.getGenes();
        List<Integer> genesPai2 = pai2.getGenes();
        int tamanho = genesPai1.size();

        List<Integer> genesFilho = new ArrayList<>(tamanho);
        int pontoCorte = ThreadLocalRandom.current().nextInt(tamanho);

        for (int i = 0; i < tamanho; i++) {
            if (i < pontoCorte) {
                genesFilho.add(genesPai1.get(i));
            } else {
                genesFilho.add(genesPai2.get(i));
            }
        }

        return new Individuo(genesFilho);
    }

    public static Individuo mutacao(Individuo individuo) {
        List<Integer> genes = individuo.getGenes();
        int posicaoMutacao = ThreadLocalRandom.current().nextInt(genes.size());

        genes.set(posicaoMutacao, genes.get(posicaoMutacao) == 1 ? 0 : 1);

        return individuo;
    }
}
