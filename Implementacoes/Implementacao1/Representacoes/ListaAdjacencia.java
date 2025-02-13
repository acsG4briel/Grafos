package Implementacoes.Implementacao1.Representacoes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Implementacoes.Implementacao1.Entidades.DadosGrafoDirecionado;
import Implementacoes.Implementacao1.Entidades.PropriedadesGrafo;

public class ListaAdjacencia 
{
    protected List<List<Integer>> Sucessores = new LinkedList<List<Integer>>();
    protected List<List<Integer>> Predecessores = new LinkedList<List<Integer>>();
    protected PropriedadesGrafo Propriedades;

    public ListaAdjacencia(PropriedadesGrafo propriedades)
    {
        this.Propriedades = propriedades;
    }

    public DadosGrafoDirecionado ObterDadosListaDeAdjacencia(int verticeRespostas) throws Exception
    {
        if(verticeRespostas > Propriedades.Vertices)
        {
            throw new IOException("Vértice não existe no grafo atual.");
        }

        PreencherListaDeAdjacencia();

        int grauDeEntrada = Predecessores.get(verticeRespostas - 1).size();
        int grauDeSaida = Sucessores.get(verticeRespostas - 1).size();
        var sucessores = Sucessores.get(verticeRespostas - 1);
        var predecessores = Predecessores.get(verticeRespostas - 1);

        return new DadosGrafoDirecionado(grauDeEntrada, grauDeSaida, sucessores, predecessores);
    }

    private void PreencherListaDeAdjacencia()
    {
        for(int i = 0; i < Propriedades.Vertices; i++)
        {
            Sucessores.add(new ArrayList<Integer>());
            Predecessores.add(new ArrayList<Integer>());
        }

        for (String relacao : Propriedades.Relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            Sucessores.get(verticeOrigem - 1).add(verticeDestino);
            Predecessores.get(verticeDestino - 1).add(verticeOrigem);
        }
    } 

}
