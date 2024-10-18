package algoritmo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import model.Item;
import model.Mochila;
import util.Aptidao;
import util.ManipuladorArquivo;

public class AlgoritmoGenetico {
	private final Mochila mochila;
	private final int tamanhoPopulacao;
	private final int numeroGeracoes;
	private final double probabilidadeCrossover;
	private final double probabilidadeMutacao;
	private List<Individuo> melhoresIndividuos;

	public AlgoritmoGenetico(Mochila mochila, int numeroGeracoes) {
		this.mochila = mochila;
		this.tamanhoPopulacao = mochila.getItens().size();
		this.numeroGeracoes = numeroGeracoes;
		this.probabilidadeCrossover = 0.7;
		this.probabilidadeMutacao = 0.01;
		this.melhoresIndividuos = new ArrayList<>();
	}

	public Individuo executar() {
		// Geração da população inicial
		List<Individuo> populacaoAtual = gerarPopulacaoInicial();
		avaliarPopulacao(populacaoAtual);

		// Manter os melhores indivíduos da população inicial
		atualizarMelhoresIndividuos(populacaoAtual);

		for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
			List<Individuo> novaPopulacao = new ArrayList<>(tamanhoPopulacao);

			// Dividindo a população em duas metades P1 e P2
			List<Individuo> p1 = populacaoAtual.subList(0, tamanhoPopulacao / 2);
			List<Individuo> p2 = populacaoAtual.subList(tamanhoPopulacao / 2, tamanhoPopulacao);

			// Aplicando crossover em P1
			for (int i = 0; i < p1.size(); i++) {
				Individuo pai1 = selecionarIndividuo(p1);
				Individuo pai2 = selecionarIndividuo(p1);

				Individuo filho = ThreadLocalRandom.current().nextDouble() < probabilidadeCrossover
						? OperacoesGeneticas.crossover(pai1, pai2)
						: pai1;

				novaPopulacao.add(filho);
			}

			// Aplicando mutação em P2
			for (int i = 0; i < p2.size(); i++) {
				Individuo mutante = selecionarIndividuo(p2);

				if (ThreadLocalRandom.current().nextDouble() < probabilidadeMutacao) {
					mutante = OperacoesGeneticas.mutacao(mutante);
				}

				novaPopulacao.add(mutante);
			}

			// Avaliando a nova população
			avaliarPopulacao(novaPopulacao);

			// Atualiza os melhores indivíduos
			atualizarMelhoresIndividuos(novaPopulacao);

			// Atualizando a população atual com a nova
			populacaoAtual = novaPopulacao;
		}

		// Retorna o melhor indivíduo encontrado na lista de melhores indivíduos
		return melhoresIndividuos.stream().max(Comparator.comparingInt(Individuo::getAptidao)).orElse(null);
	}

	private List<Individuo> gerarPopulacaoInicial() {
		List<Individuo> populacaoInicial = new ArrayList<>(tamanhoPopulacao);
		for (int i = 0; i < tamanhoPopulacao; i++) {
			populacaoInicial.add(new Individuo(mochila.getItens().size()));
		}
		return populacaoInicial;
	}

	private void avaliarPopulacao(List<Individuo> populacao) {
		populacao.parallelStream().forEach(individuo -> {
			individuo.setMochila(new Mochila(mochila.getCapacidade()));
			individuo.setAptidao(Aptidao.calcularAptidao(individuo, mochila.getItens()));
		});
	}

	private void atualizarMelhoresIndividuos(List<Individuo> populacao) {
		populacao.sort(Comparator.comparingInt(Individuo::getAptidao).reversed());

		int numeroMelhores = Math.max(1, populacao.size() / 10);
		List<Individuo> melhoresIndividuos = new ArrayList<>(populacao.subList(0, numeroMelhores));

		this.melhoresIndividuos.clear();
		this.melhoresIndividuos.addAll(melhoresIndividuos);
	}

	private Individuo selecionarIndividuo(List<Individuo> individuos) {
		return individuos.get(ThreadLocalRandom.current().nextInt(individuos.size()));
	}

	public void exibirMochila() {
		StringBuilder sb = new StringBuilder();
		sb.append("Capacidade da Mochila: ").append(mochila.getCapacidade()).append("\n");
		sb.append("Itens:\n");

		mochila.getItens().forEach(item -> sb.append("Valor: ").append(item.getGanho()).append(", Peso: ")
				.append(item.getPeso()).append("\n"));

		System.out.println(sb.toString());
	}

	public void exibirResultadoMelhorSolucao(Individuo melhorIndividuo) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("Melhor Solução Encontrada:\n");

		int ganhoTotal = 0;
		int pesoTotal = 0;
		List<Item> itens = melhorIndividuo.getMochila().getItens();
		List<Integer> genes = melhorIndividuo.getGenes();

		sb.append("Genes: ").append(String.join(" ", genes.stream().map(String::valueOf).toArray(String[]::new)))
				.append("\n");

		sb.append("Itens selecionados: ");
		for (Item item : itens) {
			ganhoTotal += item.getGanho();
			pesoTotal += item.getPeso();
			sb.append("[Ganho: ").append(item.getGanho()).append(", Peso: ").append(item.getPeso()).append("] ");
		}

		sb.append("\nGanho Total: ").append(ganhoTotal).append(", Peso Total: ").append(pesoTotal);
		System.out.println(sb.toString());
		ManipuladorArquivo.escreverResultado("C:\\\\Users\\\\evert\\\\Documents\\\\pmb\\\\resultado.txt", ganhoTotal,
				pesoTotal, genes);
	}
}
