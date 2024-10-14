package algoritmo;

import util.Aptidao;

import java.util.Random;

import model.Mochila;

public class AlgoritmoGenetico {
    private Mochila mochila;
    private int tamanhoPopulacao;
    private int numeroGeracoes;
    private double probabilidadeCrossover;
    private double probabilidadeMutacao;

    public AlgoritmoGenetico(Mochila mochila, int tamanhoPopulacao, int numeroGeracoes, double probabilidadeCrossover, double probabilidadeMutacao) {
        this.mochila = mochila;
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.numeroGeracoes = numeroGeracoes;
        this.probabilidadeCrossover = probabilidadeCrossover;
        this.probabilidadeMutacao = probabilidadeMutacao;
    }

    public Cromossomo executar() {
        Populacao populacaoAtual = new Populacao(tamanhoPopulacao, mochila.getItens().size());
        avaliarPopulacao(populacaoAtual);

        for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
            Populacao novaPopulacao = new Populacao(tamanhoPopulacao, mochila.getItens().size());

            for (int i = 0; i < tamanhoPopulacao; i++) {
                Cromossomo pai1 = selecionarIndividuo(populacaoAtual);
                Cromossomo pai2 = selecionarIndividuo(populacaoAtual);
                Cromossomo filho;

                if (Math.random() < probabilidadeCrossover) {
                    filho = OperacoesGeneticas.crossover(pai1, pai2);
                } else {
                    filho = pai1;
                }

                novaPopulacao.adicionar(filho);
            }

            for (Cromossomo individuo : novaPopulacao.getCromossomos()) {
                if (Math.random() < probabilidadeMutacao) {
                    individuo = OperacoesGeneticas.mutacao(individuo);
                }
            }

            avaliarPopulacao(novaPopulacao);
            populacaoAtual = novaPopulacao;
        }

        return populacaoAtual.getMelhorIndividuo();
    }

    private void avaliarPopulacao(Populacao populacao) {
        for (Cromossomo individuo : populacao.getCromossomos()) {
            int aptidao = Aptidao.calcularAptidao(individuo, mochila);
            individuo.setAptidao(aptidao);
        }
    }

    private Cromossomo selecionarIndividuo(Populacao populacao) {
        return populacao.getCromossomos().get(new Random().nextInt(populacao.getTamanho()));
    }
}
