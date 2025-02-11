package Implementacoes.Implementacao1;

import java.util.List;

public class Grafo {
    public int Vertices;
    public int Arestas;
    public List<String> Relacoes;

    public int[][] MatrizDeIncidencia;
    public int[][] MatrizDeAdjacencia;
    public List<List<Integer>> ListaDeAdjacencia;
    //TODO: Adicionar vetor de adjacencias

    public Grafo(int vertices, int arestas, List<String> relacoes)
    {
        this.Vertices = vertices;
        this.Arestas = arestas;
        this.Relacoes = relacoes;
    }

    public void GerarGrafo(TipoRepresentacao representacao)
    {
        switch (representacao) {
            case TipoRepresentacao.MatrizDeIncidencia:
                PreencherMatrizDeIncidencia();
                ExibirMatrizDeIncidencia();
                break;
        
            default:
                break;
        }
    }

    private void PreencherMatrizDeIncidencia()
    {  
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
