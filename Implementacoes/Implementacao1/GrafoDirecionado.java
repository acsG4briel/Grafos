package Implementacoes.Implementacao1;
import Implementacoes.Implementacao1.Entidades.DadosGrafoDirecionado;
import Implementacoes.Implementacao1.Entidades.PropriedadesGrafo;
import Implementacoes.Implementacao1.Representacoes.ListaAdjacencia;
import Implementacoes.Implementacao1.Representacoes.MatrizAdjacencia;
import Implementacoes.Implementacao1.Representacoes.MatrizIncidencia;

public class GrafoDirecionado{
    private DadosGrafoDirecionado Dados;

    private MatrizIncidencia MatrizIncidencia;
    private MatrizAdjacencia MatrizAdjacencia;
    private ListaAdjacencia ListaAdjacencia;

    public GrafoDirecionado(PropriedadesGrafo propriedades)
    {
        this.MatrizIncidencia = new MatrizIncidencia(propriedades);
        this.MatrizAdjacencia = new MatrizAdjacencia(propriedades);
        this.ListaAdjacencia = new ListaAdjacencia(propriedades);
    }

    public void ObterDadosPorMatrizDeIncidencia(int verticeRespostas) throws Exception {
        long inicio = System.currentTimeMillis(); 
        this.Dados = MatrizIncidencia.ObterDadosMatrizDeIncidencia(verticeRespostas);
        long fim = System.currentTimeMillis();
        ExibirDadosGrafoDirecionado(fim - inicio);
      }
      
      public void ObterDadosPorMatrizDeAdjacencia(int verticeRespostas) throws Exception {
        long inicio = System.currentTimeMillis();
        this.Dados = MatrizAdjacencia.ObterDadosMatrizDeAdjacencia(verticeRespostas);
        long fim = System.currentTimeMillis();
        ExibirDadosGrafoDirecionado(fim - inicio);
      }
      
      public void ObterDadosPorListaAdjacencia(int verticeRespostas) throws Exception {
        long inicio = System.currentTimeMillis();
        this.Dados = ListaAdjacencia.ObterDadosListaDeAdjacencia(verticeRespostas);
        long fim = System.currentTimeMillis();
        ExibirDadosGrafoDirecionado(fim - inicio);
      }

    private void ExibirDadosGrafoDirecionado(long tempo)
    {
        System.out.println("Grau de Entrada: " + this.Dados.GrauEntrada);
        System.out.println("Grau de Saída: " + this.Dados.GrauSaida);
        System.out.println("Lista de Sucessores: " + this.Dados.Sucessores);
        System.out.println("Lista de Predecessores: " + this.Dados.Predecessores);

        System.out.println("Tempo gasto:" + tempo + "ms");
    }

}
