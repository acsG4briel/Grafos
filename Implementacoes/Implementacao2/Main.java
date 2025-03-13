package Implementacoes.Implementacao2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Receber dados do usuário
        Scanner s = new Scanner(System.in);

        System.out.println("INSIRA O CAMINHO DO ARQUIVO:");
        String path = s.nextLine();
        System.out.println("INSIRA O VÉRTICE PARA OBTER AS INFORMAÇÕES:");
        int verticeRespostas = s.nextInt();

        s.close();

        //Ler arquivo e separar os dados
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

        var numVertices = Integer.parseInt((linhas.get(0).split("  "))[0]);
        var numArestas = Integer.parseInt((linhas.get(0).split("  "))[1]);

        linhas.remove(0);
        linhas.removeLast();

        //Criar grafo com base nos dados e executar busca
        GrafoDirecionado g1 = new GrafoDirecionado(numVertices, numArestas, linhas);
        g1.buscaEmProfundidade(verticeRespostas);


        //Exibir respostas na tela
        System.out.println("Arestas de Arvore do Grafo:" + g1.arestasArvore);

        System.out.println("Arestas de Arvore de V:" + g1.arestasArvoreV);
        System.out.println("Arestas de Retorno de V:" + g1.arestasRetorno);
        System.out.println("Arestas de Avanco de V:" + g1.arestasAvanco);
        System.out.println("Arestas de Cruzamento de V:" + g1.arestasCruzamento);
    }
}
