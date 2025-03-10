package Extras.Extra1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //ENUNCIADO: Desenvolver algoritmo que descubra se um grafo é ou não bipartido.
    public static void main(String[] args) {
        String path = "Testes\\teste-grafo-nao-direcionado-bipartido.txt";
        List<String> linhas = new ArrayList<String>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(path))) 
        {
            String linha;

            while ((linha = leitor.readLine()) != null) 
            {
                linhas.add(linha);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Ocorreu um erro ao abrir o arquivo.");
            e.printStackTrace();
        }

        int numVertices = Integer.parseInt(linhas.get(0));
        linhas.remove(0);

        GrafoNaoDirecionado grafo = new GrafoNaoDirecionado(numVertices);

        for(int i = 0; i < linhas.size(); i++)
        {
            String aresta = linhas.get(i);

            var dados = aresta.split("   ");

            var v = Integer.parseInt(dados[0]);
            var w = Integer.parseInt(dados[1]);

            grafo.AdicionarAresta(v, w);
        }

        boolean ehBipartido = grafo.BuscaEmLarguraAdaptada();

        if(ehBipartido)
        {
            System.out.println("Grafo é bipartido!");
        }
        else
        {
            System.out.println("Grafo não é bipartido!");
        }
    }
}
