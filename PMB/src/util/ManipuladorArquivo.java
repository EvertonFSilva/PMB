package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Mochila;

public class ManipuladorArquivo {

	public static Mochila lerDeArquivo(String nomeArquivo) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(nomeArquivo));

			int capacidade = Integer.parseInt(reader.readLine().trim());
			int numeroItens = Integer.parseInt(reader.readLine().trim());

			List<Item> itens = new ArrayList<>();
			for (int i = 0; i < numeroItens; i++) {
				String[] partes = reader.readLine().trim().split("\\s+");
				int valor = Integer.parseInt(partes[0]);
				int peso = Integer.parseInt(partes[1]);
				itens.add(new Item(valor, peso));
			}

			return new Mochila(capacidade, itens);

		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public static void escreverNoArquivo(Mochila mochila, String nomeArquivo) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(nomeArquivo));

			writer.write(mochila.getCapacidade() + "\n");
			writer.write(mochila.getItens().size() + "\n");

			for (Item item : mochila.getItens()) {
				writer.write(item.getValor() + " " + item.getPeso() + "\n");
			}

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
