package Implementacoes.Implementacao1.Representacoes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Implementacoes.Implementacao1.Entidades.DadosGrafoDirecionado;
import Implementacoes.Implementacao1.Entidades.PropriedadesGrafo;

public class MatrizAdjacencia 
{
    protected int[][] MatrizDeAdjacencia;
    protected PropriedadesGrafo Propriedades;

    public MatrizAdjacencia(PropriedadesGrafo propriedades)
    {
        this.Propriedades = propriedades;
    }

    public DadosGrafoDirecionado ObterDadosMatrizDeAdjacencia(int verticeRespostas) throws Exception
    {
        if(verticeRespostas > Propriedades.Vertices)
        {
            throw new IOException("Vértice não existe no grafo atual.");
        }

        PreencherMatrizDeAdjacencia();

        int grauDeEntrada = 0;
        int grauDeSaida = 0;
        List<Integer> sucessores = new ArrayList<Integer>();
        List<Integer> predecessores = new ArrayList<Integer>();

        for(int i = 0; i < Propriedades.Vertices; i++)
        {
            if(i == verticeRespostas - 1)
            {
                for(int j = 0; j < Propriedades.Vertices; j++)
                {
                    if(this.MatrizDeAdjacencia[i][j] == 1)
                    {
                        grauDeSaida++;
                        sucessores.add(j + 1);
                    }
                }

                for(int j = 0; j < Propriedades.Vertices; j++)
                {
                    if(this.MatrizDeAdjacencia[j][i] == 1)
                    {
                        grauDeEntrada++;
                        predecessores.add(j + 1);
                    }
                }
            }
        }
        
        return new DadosGrafoDirecionado(grauDeEntrada, grauDeSaida, sucessores, predecessores);
    }

    private void PreencherMatrizDeAdjacencia()
    {
        this.MatrizDeAdjacencia = new int[Propriedades.Vertices][Propriedades.Vertices];

        for (String relacao : Propriedades.Relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            for(int i = 0; i < Propriedades.Vertices; i++)
            {
                for(int j = 0; j < Propriedades.Vertices; j++)
                {
                    if(i == verticeOrigem - 1 && j == verticeDestino - 1)
                    {
                        this.MatrizDeAdjacencia[i][j] = 1;
                    }
                }
            }
        }
    }

}
