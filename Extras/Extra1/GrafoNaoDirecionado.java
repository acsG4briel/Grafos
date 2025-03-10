package Extras.Extra1;

import java.util.ArrayList;
import java.util.List;

public class GrafoNaoDirecionado {
    private final int NumVertices;
    private List<List<Integer>> Adj = new ArrayList<>();

    public GrafoNaoDirecionado(int numVertices)
    {
        this.NumVertices = numVertices;

        for(int i = 0; i < NumVertices; i++)
        {
            this.Adj.add(new ArrayList<Integer>());
        }
    }

    public void AdicionarAresta(int v, int w)
    {
        if(!this.Adj.get(v - 1).contains(w)) 
        {
            this.Adj.get(v - 1).add(w);
            this.Adj.get(w - 1).add(v);
        }
    }

    public void ExibirGrafoNaoDirecionado()
    {
        for(int i = 0; i < this.NumVertices ; i++)
        {
            System.out.println(i + 1 + " -> ");
            System.out.println(Adj.get(i));
        }
    }

    public boolean BuscaEmLarguraAdaptada()
    {
        int index[] = new int[NumVertices];
        int nivel[] = new int[NumVertices];
        int predecessores[] = new int[NumVertices];

        for (int i = 0; i < NumVertices; i++)
        {
            index[i] = 0;
            nivel[i] = 0;
            predecessores[i] = 0;
        }

        int raiz = 0;
        List<Integer> fila = new ArrayList<Integer>();

        int contadorIndex = 1;

        index[raiz] = contadorIndex;
        nivel[raiz] = 0;
        predecessores[raiz] = 0;
        fila.add(raiz);

        while(!fila.isEmpty())
        {
            int v = fila.removeFirst();
            var adjacencias = this.Adj.get(v);

            for(int w : adjacencias)
            {
                if(index[w - 1] == 0)
                {
                    index[w - 1] = ++contadorIndex;
                    nivel[w - 1] = nivel[v] + 1;
                    predecessores[w - 1] = v + 1;
                    fila.add(w - 1);
                }
                else if(nivel[v] == nivel[w - 1])
                {
                    return false;
                }
            }
        }

        return true;
    }
}
