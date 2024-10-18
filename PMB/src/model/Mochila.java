package model;

import java.util.ArrayList;
import java.util.List;

public class Mochila {
	private int capacidade;
	private List<Item> itens;

	public Mochila(int capacidade) {
		this.capacidade = capacidade;
		this.itens = new ArrayList<>();
	}

	public Mochila(int capacidade, List<Item> itens) {
		this.capacidade = capacidade;
		this.itens = itens;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens.clear();
		this.itens.addAll(itens);
	}
}
