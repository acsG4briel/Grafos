package Implementacoes.Implementacao1.Representacoes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Implementacoes.Implementacao1.Entidades.DadosGrafoDirecionado;
import Implementacoes.Implementacao1.Entidades.PropriedadesGrafo;

public class MatrizIncidencia
{
    protected int[][] MatrizDeIncidencia;
    protected PropriedadesGrafo Propriedades;

    public MatrizIncidencia(PropriedadesGrafo propriedades)
    {
        this.Propriedades = propriedades;
    }

    public DadosGrafoDirecionado ObterDadosMatrizDeIncidencia(int verticeRespostas) throws Exception
    {
        if(verticeRespostas > Propriedades.Vertices)
        {
            throw new IOException("Vértice não existe no grafo atual.");
        }

        PreencherMatrizDeIncidencia();

        int grauDeEntrada = 0;
        int grauDeSaida = 0;
        List<Integer> sucessores = new ArrayList<Integer>();
        List<Integer> predecessores = new ArrayList<Integer>();

        for(int i = 0; i < Propriedades.Arestas; i++)
        {
            if(MatrizDeIncidencia[verticeRespostas - 1][i] == 1)
            {
                grauDeEntrada++;
                for(int j = 0; j < Propriedades.Vertices; j++)
                {
                    if(MatrizDeIncidencia[j][i] == -1)
                    {
                        predecessores.add(j+1);
                    }
                }
            }
            else if(MatrizDeIncidencia[verticeRespostas - 1][i] == -1)
            {
                grauDeSaida++;
                for(int j = 0; j < Propriedades.Vertices; j++)
                {
                    if(MatrizDeIncidencia[j][i] == 1)
                    {
                        sucessores.add(j+1);
                    }
                }
            }
        }

        return new DadosGrafoDirecionado(grauDeEntrada, grauDeSaida, sucessores, predecessores);
    }

    private void PreencherMatrizDeIncidencia()
    {  
        int contadorColunaMatriz = 0;
        this.MatrizDeIncidencia = new int[this.Propriedades.Vertices][this.Propriedades.Arestas];

        for (String relacao : this.Propriedades.Relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            for(int i = 0; i < Propriedades.Vertices; i++)
            {
                if(i == verticeOrigem - 1)
                {
                    this.MatrizDeIncidencia[i][contadorColunaMatriz] = -1;
                }
                else if(i == verticeDestino - 1)
                {
                    this.MatrizDeIncidencia[i][contadorColunaMatriz] = 1;
                }
                else
                {
                    this.MatrizDeIncidencia[i][contadorColunaMatriz] = 0;
                }
            }

            contadorColunaMatriz++;
        }
    }

    

}
