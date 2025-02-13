package Implementacoes.Implementacao1.Entidades;

import java.util.ArrayList;
import java.util.List;

public class DadosGrafoDirecionado 
{
    public int GrauEntrada;
    public int GrauSaida;
    public List<Integer> Sucessores = new ArrayList<Integer>();
    public List<Integer> Predecessores = new ArrayList<Integer>();

    public DadosGrafoDirecionado(int ge, int gs, List<Integer> suc, List<Integer> pred)
    {
        this.GrauEntrada = ge;
        this.GrauSaida = gs;
        this.Sucessores = suc;
        this.Predecessores = pred;
    }
}
