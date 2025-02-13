package Implementacoes.Implementacao1;
import java.util.Scanner;

public class Main {
    //ENUNCIADO: Implementação 1 - Teoria dos Grafos e Computabilidade
    //Criar um programa que receba informações de adjacências e crie em memória um grafo direcionado
    //obtendo as seguintes informações sobre um grafo G dado: grau de saida, grau de entrada,
    //conjunto de sucessore e conjunto de predecessores.
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);

        System.out.println("INSIRA O CAMINHO DO ARQUIVO:");
        String path = s.nextLine();
        System.out.println("INSIRA O VÉRTICE PARA OBTER AS INFORMAÇÕES:");
        int verticeRespostas = s.nextInt();

        var propriedades = InputGrafos.LerArquivo(path);

        GrafoDirecionado g1 = new GrafoDirecionado(propriedades);
        g1.ObterDadosPorMatrizDeIncidencia(verticeRespostas);

        GrafoDirecionado g2 = new GrafoDirecionado(propriedades);
        g2.ObterDadosPorMatrizDeAdjacencia(verticeRespostas);

        GrafoDirecionado g3 = new GrafoDirecionado(propriedades);
        g3.ObterDadosPorListaAdjacencia(verticeRespostas);

        s.close();
    }
}
