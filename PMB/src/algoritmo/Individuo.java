package algoritmo;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import model.Item;
import model.Mochila;

public class Individuo {
	private List<Integer> genes;
	private int aptidao;
	private Mochila mochila;

	public Individuo(int tamanho) {
		genes = new ArrayList<>(tamanho);
		for (int i = 0; i < tamanho; i++) {
			genes.add(ThreadLocalRandom.current().nextInt(2));
		}
	}

	public Individuo(List<Integer> genes) {
		this.genes = genes;
	}

	public List<Integer> getGenes() {
		return genes;
	}

	public void setGene(int indice, int valor) {
		if (indice >= 0 && indice < genes.size() && (valor == 0 || valor == 1)) {
			genes.set(indice, valor);
		} else {
			throw new IllegalArgumentException("Índice ou valor inválido");
		}
	}

	public int getNumeroGenes() {
		return genes.size();
	}

	public void setAptidao(int aptidao) {
		this.aptidao = aptidao;
	}

	public int getAptidao() {
		return aptidao;
	}

	public void setMochila(Mochila mochila) {
		this.mochila = mochila;
	}

	public Mochila getMochila() {
		return mochila;
	}

	public boolean adicionarItem(Item item) {
		if (!mochila.getItens().contains(item)) {
			mochila.getItens().add(item);
			return true;
		}
		return false;
	}
}
