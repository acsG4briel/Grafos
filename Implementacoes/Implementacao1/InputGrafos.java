package Implementacoes.Implementacao1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Implementacoes.Implementacao1.Entidades.PropriedadesGrafo;

public class InputGrafos 
{
    public static PropriedadesGrafo LerArquivo(String path)
    {
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

        return new PropriedadesGrafo(linhas, numVertices, numArestas);
    }
}
