package Implementacoes.Implementacao1.Entidades;

import java.util.List;

public class PropriedadesGrafo {
    public List<String> Relacoes;
    public int Vertices;
    public int Arestas;

    public PropriedadesGrafo(List<String> relacoes, int vertices, int arestas)
    {
        this.Relacoes = relacoes;
        this.Vertices = vertices;
        this.Arestas = arestas;
    }
}
