package algoritmo;

import java.util.ArrayList;
import java.util.List;

public class Populacao {
    private List<Cromossomo> cromossomos;

    public Populacao(int tamanho, int tamanhoCromossomo) {
        cromossomos = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            cromossomos.add(new Cromossomo(tamanhoCromossomo));
        }
    }

    public List<Cromossomo> getCromossomos() {
        return cromossomos;
    }

    public Cromossomo getMelhorIndividuo() {
        return cromossomos.stream().max((c1, c2) -> Integer.compare(c1.getAptidao(), c2.getAptidao())).orElse(null);
    }

    public void adicionar(Cromossomo cromossomo) {
        this.cromossomos.add(cromossomo);
    }

    public int getTamanho() {
        return cromossomos.size();
    }
}
