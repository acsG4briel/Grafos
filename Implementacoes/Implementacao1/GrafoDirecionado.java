package Implementacoes.Implementacao1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrafoDirecionado {
    public int Vertices;
    public int Arestas;
    public List<String> Relacoes;

    public int[][] MatrizDeIncidencia;
    public int[][] MatrizDeAdjacencia;
    public List<List<Integer>> ListaDeAdjacencia;
    //TODO: Adicionar vetor de adjacencias

    public int GrauSaida;
    public int GrauEntrada;
    public List<Integer> Sucessores;
    public List<Integer> Predecessores;

    public GrafoDirecionado(int vertices, int arestas, List<String> relacoes)
    {
        this.Vertices = vertices;
        this.Arestas = arestas;
        this.Relacoes = relacoes;
    }

    public long GerarRepresentacaoGrafo(TipoRepresentacao representacao)
    {
        switch (representacao) {
            case TipoRepresentacao.MatrizDeIncidencia:
                var tempo = PreencherMatrizDeIncidencia();
                ExibirMatrizDeIncidencia();
                return tempo;
            default:
                return 0;
        }
    }

    public long ObterDadosPorGrafo(int verticeRespostas, TipoRepresentacao representacao) throws Exception
    {
        switch (representacao) {
            case TipoRepresentacao.MatrizDeIncidencia:
                return ObterDadosMatrizDeIncidencia(verticeRespostas);

            default:
                return 0;
        }
    }

    private long PreencherMatrizDeIncidencia()
    {  
        long inicio = System.currentTimeMillis();

        int contadorColunaMatriz = 0;
        this.MatrizDeIncidencia = new int[this.Vertices][this.Arestas];

        for (String relacao : Relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            for(int i = 0; i < this.Vertices; i++)
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

        long fim = System.currentTimeMillis();

        return fim - inicio;
    }

    private long ObterDadosMatrizDeIncidencia(int verticeRespostas) throws Exception
    {
        if(verticeRespostas > this.Vertices)
        {
            throw new IOException("Vértice não existe no grafo atual.");
        }

        long inicio = System.currentTimeMillis();

        int grauDeEntrada = 0;
        int grauDeSaida = 0;
        List<Integer> sucessores = new ArrayList<Integer>();
        List<Integer> predecessores = new ArrayList<Integer>();

        for(int i = 0; i < this.Arestas; i++)
        {
            if(MatrizDeIncidencia[verticeRespostas - 1][i] == 1)
            {
                grauDeEntrada++;
                for(int j = 0; j < this.Vertices; j++)
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
                for(int j = 0; j < this.Vertices; j++)
                {
                    if(MatrizDeIncidencia[j][i] == 1)
                    {
                        sucessores.add(j+1);
                    }
                }
            }
        }

        long fim = System.currentTimeMillis();

        this.GrauEntrada = grauDeEntrada;
        this.GrauSaida = grauDeSaida;
        this.Sucessores = sucessores;
        this.Predecessores = predecessores;

        return fim - inicio;
    }

    private void ExibirMatrizDeIncidencia()
    {
        int linhas = this.MatrizDeIncidencia.length;
        int colunas = this.MatrizDeIncidencia[0].length;

        System.out.print("    ");
        for (int j = 0; j < colunas; j++) 
        {
            System.out.print(j + 1 + "  ");
        }
        System.out.println();

        for (int i = 0; i < linhas; i++) 
        {
            System.out.print(i + 1 + "   ");
      
            for (int j = 0; j < colunas; j++) 
            {
                String ESPACO_ENTRE_VALORES = (this.MatrizDeIncidencia[i][j] >= 0) ? "  " : " ";
                System.out.print(this.MatrizDeIncidencia[i][j] + ESPACO_ENTRE_VALORES);
            }
            System.out.println();
        }
    }
}
