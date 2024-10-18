package model;

public class Item {
    private int peso;
    private int ganho;

    public Item(int peso, int ganho) {
        this.peso = peso;
        this.ganho = ganho;
    }

    public int getPeso() {
        return peso;
    }

    public int getGanho() {
        return ganho;
    }
}
