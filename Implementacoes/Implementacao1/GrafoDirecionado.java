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
    public List<List<Integer>> ListaDeAdjacenciaSucessores = new ArrayList<List<Integer>>();
    public List<List<Integer>> ListaDeAdjacenciaPredecessores = new ArrayList<List<Integer>>();
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
                var tempoMatrizIncidencia = PreencherMatrizDeIncidencia();
                //ExibirMatrizDeIncidencia();
                return tempoMatrizIncidencia;
            case TipoRepresentacao.MatrizDeAdjacencia:
                var tempoMatrizAdjacencia = PreencherMatrizDeAdjacencia();
                //ExibirMatrizDeAdjacencia();
                return tempoMatrizAdjacencia;
            case TipoRepresentacao.ListaDeAdjacencia:
                var tempoListaAdjacencia = PreencherListaDeAdjacencia();
                //ExibirListaDeAdjacencia();
                return tempoListaAdjacencia;
            default:
                return 0;
        }
    }

    public long ObterDadosPorGrafo(int verticeRespostas, TipoRepresentacao representacao) throws Exception
    {
        switch (representacao) {
            case TipoRepresentacao.MatrizDeIncidencia:
                return ObterDadosMatrizDeIncidencia(verticeRespostas);
            case TipoRepresentacao.MatrizDeAdjacencia:
                return ObterDadosMatrizDeAdjacencia(verticeRespostas);
            case TipoRepresentacao.ListaDeAdjacencia:
                return ObterDadosListaDeAdjacencia(verticeRespostas);
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

    private long PreencherMatrizDeAdjacencia()
    {
        long inicio = System.currentTimeMillis();
        
        this.MatrizDeAdjacencia = new int[this.Vertices][this.Vertices];

        for (String relacao : Relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            for(int i = 0; i < this.Vertices; i++)
            {
                for(int j = 0; j < this.Vertices; j++)
                {
                    if(i == verticeOrigem - 1 && j == verticeDestino - 1)
                    {
                        this.MatrizDeAdjacencia[i][j] = 1;
                    }
                }
            }
        }

        long fim = System.currentTimeMillis();

        return fim - inicio;
    }

    private long ObterDadosMatrizDeAdjacencia(int verticeRespostas) throws Exception
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

        for(int i = 0; i < this.Vertices; i++)
        {
            if(i == verticeRespostas - 1)
            {
                for(int j = 0; j < this.Vertices; j++)
                {
                    if(this.MatrizDeAdjacencia[i][j] == 1)
                    {
                        grauDeSaida++;
                        sucessores.add(j + 1);
                    }
                }

                for(int j = 0; j < this.Vertices; j++)
                {
                    if(this.MatrizDeAdjacencia[j][i] == 1)
                    {
                        grauDeEntrada++;
                        predecessores.add(j + 1);
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

    private void ExibirMatrizDeAdjacencia()
    {
        System.out.print("   ");
        for (int i = 1; i <= this.Vertices; i++) 
        {
            System.out.print(i + "  ");
        }
        System.out.println();

        for (int i = 0; i < this.Vertices; i++) 
        {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < Vertices; j++) 
            {
                System.out.print(this.MatrizDeAdjacencia[i][j] + "  "); 
            }
            System.out.println(); 
        }
    }

    private long PreencherListaDeAdjacencia()
    {
        long inicio = System.currentTimeMillis();

        for(int i = 0; i < this.Vertices; i++)
        {
            this.ListaDeAdjacenciaSucessores.add(new ArrayList<Integer>());
            this.ListaDeAdjacenciaPredecessores.add(new ArrayList<Integer>());
        }

        for (String relacao : Relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            this.ListaDeAdjacenciaSucessores.get(verticeOrigem - 1).add(verticeDestino);
            this.ListaDeAdjacenciaPredecessores.get(verticeDestino - 1).add(verticeOrigem);
        }

        long fim = System.currentTimeMillis();

        return fim - inicio;
    } 

    private long ObterDadosListaDeAdjacencia(int verticeRespostas) throws Exception
    {
        if(verticeRespostas > this.Vertices)
        {
            throw new IOException("Vértice não existe no grafo atual.");
        }

        long inicio = System.currentTimeMillis();

        int grauDeEntrada = this.ListaDeAdjacenciaPredecessores.get(verticeRespostas - 1).size();
        int grauDeSaida = this.ListaDeAdjacenciaSucessores.get(verticeRespostas - 1).size();

        long fim = System.currentTimeMillis();

        this.GrauEntrada = grauDeEntrada;
        this.GrauSaida = grauDeSaida;
        this.Sucessores = this.ListaDeAdjacenciaSucessores.get(verticeRespostas - 1);
        this.Predecessores = this.ListaDeAdjacenciaPredecessores.get(verticeRespostas - 1);

        return fim - inicio;
    }

    public void ExibirListaDeAdjacencia() 
    {
        System.out.println("LISTA DE SUCESSORES");
        for (int i = 0; i < this.Vertices; i++) 
        {
            List<Integer> lista = this.ListaDeAdjacenciaSucessores.get(i);
        
            System.out.println((i + 1) + " -> " + lista);
        }

        System.out.println("LISTA DE PREDECESSORES");
        for (int i = 0; i < this.Vertices; i++) 
        {
            List<Integer> lista = this.ListaDeAdjacenciaPredecessores.get(i);
        
            System.out.println((i + 1) + " -> " + lista);
        }
    }
}
