package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Mochila;

public class ManipuladorArquivo {

	public static Mochila lerDeArquivo(String nomeArquivo) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(nomeArquivo))) {

			int capacidade = Integer.parseInt(reader.readLine().trim());
			int numeroItens = Integer.parseInt(reader.readLine().trim());

			List<Item> itens = new ArrayList<>(numeroItens);

			for (int i = 0; i < numeroItens; i++) {
				String[] partes = reader.readLine().trim().split("\\s+");
				if (partes.length != 2) {
					throw new IOException("Formato de linha inválido no arquivo.");
				}

				int ganho = Integer.parseInt(partes[0]);
				int peso = Integer.parseInt(partes[1]);
				itens.add(new Item(ganho, peso));
			}

			return new Mochila(capacidade, itens);
		}
	}

	public static void escreverNoArquivo(Mochila mochila, String nomeArquivo) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nomeArquivo))) {

			writer.write(mochila.getCapacidade() + "\n");
			writer.write(mochila.getItens().size() + "\n");

			StringBuilder sb = new StringBuilder();
			for (Item item : mochila.getItens()) {
				sb.append(item.getGanho()).append(" ").append(item.getPeso()).append("\n");
			}

			writer.write(sb.toString());
		}
	}

	public static void escreverResultado(String nomeArquivo, int ganhoTotal, int pesoTotal, List<Integer> vetorBinario)
			throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nomeArquivo))) {
			writer.write(ganhoTotal + "\n");
			writer.write(pesoTotal + "\n");
			writer.write(vetorBinario.stream().map(String::valueOf).reduce((a, b) -> a + " " + b).orElse("") + "\n");
		}
	}
}
