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
        //String path = "Implementacoes\\Implementacao1\\graph-test-100-1.txt";
        String path = "Implementacoes\\Implementacao1\\TestCases\\testeMenorGrafo.txt";

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
        GrafoDirecionado g1 = new GrafoDirecionado(numVertices, numArestas, linhas);
        var tempoMontagem = g1.GerarRepresentacaoGrafo(TipoRepresentacao.MatrizDeIncidencia);

        System.out.println("INSIRA UM VÉRTICE:");
        int verticeRespostas = s.nextInt();

        var tempoRespostas = g1.ObterDadosPorGrafo(verticeRespostas, TipoRepresentacao.MatrizDeIncidencia);

        System.out.println("Grau de Entrada: " + g1.GrauEntrada);
        System.out.println("Grau de Saída: " + g1.GrauSaida);
        System.out.println("Lista de Sucessores: " + g1.Sucessores);
        System.out.println("Lista de Predecessores: " + g1.Predecessores);

        System.out.println("Tempo Montagem Representação: " + tempoMontagem + "ms");
        System.out.println("Tempo Respostas: " + tempoRespostas + "ms");

        //MATRIZ DE ADJACÊNCIA


        //LISTA DE ADJACÊNCIA


        //LISTA DE ADJACÊNCIA COM VETORES
        s.close();
    }
}
