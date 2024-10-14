package algoritmo;

import java.util.Random;

public class Cromossomo {
    private int[] genes;
    private int aptidao;

    public Cromossomo(int tamanho) {
        genes = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            genes[i] = random.nextBoolean() ? 1 : 0;
        }
    }

    public int[] getGenes() {
        return genes;
    }

    public void setAptidao(int aptidao) {
        this.aptidao = aptidao;
    }

    public int getAptidao() {
        return aptidao;
    }
}
