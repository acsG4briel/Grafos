package Implementacoes.Implementacao2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoDirecionado {
    //Dados fundamentais para montagem da lista de adjacência
    private int numVertices;
    private int numArestas;
    private List<String> relacoes = new ArrayList<String>();

    //Estrutura para representar o grafo
    public List<List<Integer>> Sucessores = new ArrayList<List<Integer>>();

    //Variáveis de ambiente para que a busca funcione
    private int contadorTempo = 0;
    private int[] tempoDescoberta;
    private int[] tempoTermino;
    private int[] predecessores;

    //Informações de resposta do programa
    public List<String> arestasArvore = new ArrayList<String>();

    public List<String> arestasArvoreV = new ArrayList<String>(); //Arestas de arvore divergentes de V
    public List<String> arestasCruzamento = new ArrayList<String>(); //Arestas de cruzamento divergentes de V
    public List<String> arestasAvanco = new ArrayList<String>(); //Arestas de avanço divergentes de V
    public List<String> arestasRetorno = new ArrayList<String>(); //Arestas de retorno divergentes de V

    //Construtor do grafo
    public GrafoDirecionado(int v, int e, List<String> relacoes)
    {
        this.numVertices = v;
        this.numArestas = e;
        this.relacoes = relacoes;

        this.tempoDescoberta = new int[this.numVertices];
        this.tempoTermino = new int[this.numVertices];
        this.predecessores = new int[this.numVertices];

        MontarListaAdjacencias();
    }

    //Criar estrutura de representação do grafo direcionado
    private void MontarListaAdjacencias()
    {
        for(int i = 0; i < this.numVertices; i++)
        {
            Sucessores.add(new ArrayList<Integer>());
        }

        for (String relacao : this.relacoes) 
        {
            String[] partes = relacao.trim().split("\\s+");

            int verticeOrigem = Integer.parseInt(partes[0]);
            int verticeDestino = Integer.parseInt(partes[1]);

            Sucessores.get(verticeOrigem - 1).add(verticeDestino);
        }
    }

    //BUSCA EM PROFUNDIDADE
    public void buscaEmProfundidade(int v)
    {
        //Inicializar os vetores com 0
        for(int i = 0; i < this.numVertices; i++)
        {
            this.tempoDescoberta[i] = 0;
            this.tempoTermino[i] = 0;
            this.predecessores[i] = 0;
        }
        
        //Primeira raiz definida como 1
        int raiz = 1;

        //Condição caso existam mais de um componente conexo
        while(tempoDescobertaNulo(this.tempoDescoberta, raiz))
        {
            bfs(raiz, v);
        }
    }

    private void bfs(int raiz, int v)
    {
        //Incrementar e salvar tempo de descoberta 
        this.contadorTempo++;
        this.tempoDescoberta[raiz - 1] = this.contadorTempo;

        //Ordenando os vértices em ordem lexicográfica
        List<Integer> sucessoresVertice = new ArrayList<Integer>(this.Sucessores.get(raiz - 1));
        Collections.sort(sucessoresVertice);

        for(int i = 0; i < sucessoresVertice.size(); i++)
        {
            //Obter o vértice adjacente que será analisado
            int verticeIncidente = sucessoresVertice.get(i);
            int posicaoVertice = verticeIncidente - 1;

            //Condição para definir se uma aresta é árvore
            if(this.tempoDescoberta[posicaoVertice] == 0)
            {
                String aresta = "( " + raiz + " , " + verticeIncidente + " )";
                this.arestasArvore.add(aresta);

                //Caso o vértice explorado seja o vértice infomado, adiciona a aresta em uma classificação própria
                if(raiz == v)
                {
                    this.arestasArvoreV.add(aresta);
                }

                this.predecessores[posicaoVertice] = raiz;

                //Chamada recursiva da busca em largura
                bfs(verticeIncidente, v);
            }
            else
            {
                //Classificação das demais arestas caso o vértice seja o informado
                if(raiz == v)
                {
                    if(this.tempoTermino[posicaoVertice] == 0)
                    {
                        String aresta = "( " + raiz + " , " + verticeIncidente + " )";
                        this.arestasRetorno.add(aresta);
                    }
                    else if(this.tempoDescoberta[raiz - 1] < this.tempoDescoberta[posicaoVertice])
                    {
                        String aresta = "( " + raiz + " , " + verticeIncidente + " )";
                        this.arestasAvanco.add(aresta);
    
                    }
                    else
                    {
                        String aresta = "( " + raiz + " , " + verticeIncidente + " )";
                        this.arestasCruzamento.add(aresta);
                    }
                }
            }
        }

        //Finaliza a exploração do vértice
        this.contadorTempo++;
        this.tempoTermino[raiz - 1] = this.contadorTempo;
    }

    //Método para verificar se ainda existe algum vértice não descoberto
    private boolean tempoDescobertaNulo(int tempoDescoberta[], int raiz)
    {
        for(int i = 0; i < this.numVertices; i++)
        {
            if(tempoDescoberta[i] == 0)
            {
                raiz = i + 1;
                return true;
            }
        }
        
        return false;
    }
}
