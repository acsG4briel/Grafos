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
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        List<String> linhas = new ArrayList<String>();

        System.out.println("INSIRA O CAMINHO DO ARQUIVO:");
        //String path = s.nextLine();
        //String path = "Implementacoes\\Implementacao1\\TestCases\\graph-test-100-1.txt";
        //String path = "Implementacoes\\Implementacao1\\TestCases\\testeMenorGrafo.txt";
        String path = "Implementacoes\\Implementacao1\\TestCases\\graph-test-50000-1.txt";

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
        // GrafoDirecionado g1 = new GrafoDirecionado(numVertices, numArestas, linhas);
        // var tempoMontagemMatrizIncidencia = g1.GerarRepresentacaoGrafo(TipoRepresentacao.MatrizDeIncidencia);

        // System.out.println("INSIRA UM VÉRTICE:");
        // int verticeRespostasMatrizIncidencia = s.nextInt();

        // var tempoRespostasMatrizIncidencia = g1.ObterDadosPorGrafo(verticeRespostasMatrizIncidencia, TipoRepresentacao.MatrizDeIncidencia);

        // System.out.println("Grau de Entrada: " + g1.GrauEntrada);
        // System.out.println("Grau de Saída: " + g1.GrauSaida);
        // System.out.println("Lista de Sucessores: " + g1.Sucessores);
        // System.out.println("Lista de Predecessores: " + g1.Predecessores);

        // System.out.println("Tempo Montagem Representação: " + tempoMontagemMatrizIncidencia + "ms");
        // System.out.println("Tempo Respostas: " + tempoRespostasMatrizIncidencia + "ms");

        //MATRIZ DE ADJACÊNCIA
        // GrafoDirecionado g2 = new GrafoDirecionado(numVertices, numArestas, linhas);
        // var tempoMontagemMatrizAdjacencia = g2.GerarRepresentacaoGrafo(TipoRepresentacao.MatrizDeAdjacencia);

        // System.out.println("INSIRA UM VÉRTICE:");
        // int verticeRespostasMatrizAdjacencia = s.nextInt();

        // var tempoRespostasMatrizAdjacencia = g2.ObterDadosPorGrafo(verticeRespostasMatrizAdjacencia, TipoRepresentacao.MatrizDeAdjacencia);

        // System.out.println("Grau de Entrada: " + g2.GrauEntrada);
        // System.out.println("Grau de Saída: " + g2.GrauSaida);
        // System.out.println("Lista de Sucessores: " + g2.Sucessores);
        // System.out.println("Lista de Predecessores: " + g2.Predecessores);

        // System.out.println("Tempo Montagem Representação: " + tempoMontagemMatrizAdjacencia + "ms");
        // System.out.println("Tempo Respostas: " + tempoRespostasMatrizAdjacencia + "ms");

        //LISTA DE ADJACÊNCIA
        GrafoDirecionado g3 = new GrafoDirecionado(numVertices, numArestas, linhas);
        var tempoMontagemListaAdjacencia = g3.GerarRepresentacaoGrafo(TipoRepresentacao.ListaDeAdjacencia);

        System.out.println("INSIRA UM VÉRTICE:");
        int verticeRespostasListaAdjacencia = s.nextInt();

        var tempoRespostaListaAdjacencia = g3.ObterDadosPorGrafo(verticeRespostasListaAdjacencia, TipoRepresentacao.ListaDeAdjacencia);
        System.out.println("Grau de Entrada: " + g3.GrauEntrada);
        System.out.println("Grau de Saída: " + g3.GrauSaida);
        System.out.println("Lista de Sucessores: " + g3.Sucessores);
        System.out.println("Lista de Predecessores: " + g3.Predecessores);

        System.out.println("Tempo Montagem Representação: " + tempoMontagemListaAdjacencia + "ms");
        System.out.println("Tempo Respostas: " + tempoRespostaListaAdjacencia + "ms");
        //LISTA DE ADJACÊNCIA COM VETORES
        s.close();
    }
}
