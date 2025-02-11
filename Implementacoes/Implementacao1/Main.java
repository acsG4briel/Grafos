package Implementacoes.Implementacao1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //ENUNCIADO: Implementação 1 - Teoria dos Grafos e Computabilidade
    //Criar um programa que receba informações de adjacências e crie em memória um grafo direcionado
    //obtendo as seguintes informações sobre um grafo G dado: grau de saida, grau de entrada,
    //conjunto de sucessore e conjunto de predecessores.
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> linhas = new ArrayList<String>();

        System.out.println("INSIRA O CAMINHO DO ARQUIVO:");
        //String path = s.nextLine();
        //String path = "Implementacoes\\Implementacao1\\graph-test-100-1.txt";
        String path = "Implementacoes\\Implementacao1\\testeMenorGrafo.txt";

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

        var numVertices = Integer.parseInt((linhas.get(0).split("  "))[0]);
        var numArestas = Integer.parseInt((linhas.get(0).split("  "))[1]);

        linhas.remove(0);
        linhas.removeLast();

        //MATRIZ DE INCIDÊNCIA
            //TODO: Implementar contadores de tempo
            //TODO: Implementar funções de descobrir grau de saida, grau de entrada, conjunto de sucessores e conjunto de predecessores.
        Grafo g1 = new Grafo(numVertices, numArestas, linhas);

        g1.GerarGrafo(TipoRepresentacao.MatrizDeIncidencia);

        //MATRIZ DE ADJACÊNCIA


        //LISTA DE ADJACÊNCIA


        //LISTA DE ADJACÊNCIA COM VETORES
        s.close();
    }
}
