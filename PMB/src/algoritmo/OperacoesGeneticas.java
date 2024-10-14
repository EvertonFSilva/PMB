package algoritmo;

import java.util.Random;

public class OperacoesGeneticas {
	private static Random random = new Random();

	public static Cromossomo crossover(Cromossomo pai1, Cromossomo pai2) {
		int tamanho = pai1.getGenes().length;
		Cromossomo filho = new Cromossomo(tamanho);

		int pontoCorte = random.nextInt(tamanho);
		for (int i = 0; i < pontoCorte; i++) {
			filho.getGenes()[i] = pai1.getGenes()[i];
		}
		for (int i = pontoCorte; i < tamanho; i++) {
			filho.getGenes()[i] = pai2.getGenes()[i];
		}

		return filho;
	}

	public static Cromossomo mutacao(Cromossomo individuo) {
		int tamanho = individuo.getGenes().length;
		Cromossomo filhoMutado = new Cromossomo(tamanho);

		int posicaoMutacao = random.nextInt(tamanho);
		for (int i = 0; i < tamanho; i++) {
			if (i == posicaoMutacao) {
				filhoMutado.getGenes()[i] = random.nextBoolean() ? 1 : 0;
			} else {
				filhoMutado.getGenes()[i] = individuo.getGenes()[i];
			}
		}

		return filhoMutado;
	}
}
