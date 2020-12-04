/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import buscas.AEstrela;
import buscas.Gulosa;
import buscas.Largura;
import buscas.Profundidade;
import logica.No;
import estrutura.Busca;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tg 
 */
public class Class_principal {
    
    private int proximo;
    private Busca busca;
    private No no;
    private List<logica.Celula> lista;
    private List<No> solucao;
    private int estado[][];
    
    /*
      Configura os possíveis estados iniciais do quebra cabeça
    */
    
    public void estado_inicial_quebra_cabeca(int num) {
        
        switch (num) {
            case 0: {
                int[][] quebra_cabeca = {{7, 1, 3}, {2, 6, 0}, {5, 4, 8}};
                estado = quebra_cabeca;
                break;
            }
            case 1: {
                int[][] quebra_cabeca = {{4, 3, 5}, {2, 0, 8}, {7, 6, 1}};
                estado = quebra_cabeca;
                break;
            }
            default:
                break;
        }
        no.setPai(null);
        no.setEstados(estado);
    }

    public Class_principal() {
        lista = new ArrayList<>();
        no = new No();
        proximo = 0;
    }
    
    
    public int escolher_estado_inicial(){
        
        Scanner ler = new Scanner(System.in);

        int opcao_estado_inicial;
        System.out.println("Selecione um estado inicial");
        System.out.println("Digite 1 para => {7, 1, 3}, {2, 6, 0}, {5, 4, 8}");
        System.out.println("Digite 2 para => {4, 3, 5}, {2, 0, 8}, {7, 6, 1}");
        opcao_estado_inicial = ler.nextInt();
        
        return opcao_estado_inicial;

    }
    
        public int escolher_heuristica(){
        
        Scanner ler = new Scanner(System.in);

        int heuristica;
        System.out.println("Selecione a heuristica");
        System.out.println("Digite 0 para - Peças fora do lugar -");
        System.out.println("Digite 1 para - Distanciamento de Manhattan}");
        heuristica = ler.nextInt();
        
        return heuristica;      
        
    }
    
    
    public void menu(){
        
        Scanner ler = new Scanner(System.in);
        int n;
        int proximo;
        int estado_inicial;
        int heuristica;

        System.out.println("Escolha uma opção!");
        System.out.println("Escolha 1 para busca em profundidade");
        System.out.println("Escolha 2 para busca em largura");
        System.out.println("Escolha 3 para busca gulosa");
        System.out.println("Escolha 4 para busca A*");

        n = ler.nextInt();
        
        if (n == 1){

                estado_inicial = escolher_estado_inicial();

                if(estado_inicial == 1){

                    estado_inicial_quebra_cabeca(0);
                    busca = new Profundidade();
                    busca.solucionar(no);
                    System.out.println("Quantidade de nos expandidos: " + busca.getContador());
                    System.out.println("Movimentos: " + busca.getMovimentos());
                    solucao = busca.getSolucao();
                    busca.setContador(0);
                } if(estado_inicial == 2) {
                    estado_inicial_quebra_cabeca(1);
                    busca = new Profundidade();
                    busca.solucionar(no);
                    System.out.println("Quantidade de nos expandidos: " + busca.getContador());
                    System.out.println("Total de movimentações: " + busca.getMovimentos());
                    solucao = busca.getSolucao();
                    busca.setContador(0);

                } else {
                    System.out.println("Fim");
                }
            } if (n == 2){
                System.out.println("Busca por Largura");
                estado_inicial = escolher_estado_inicial();
                if(estado_inicial == 1){
                    estado_inicial_quebra_cabeca(0);
                } else if(estado_inicial == 2) {
                    System.out.println("Aguarde pois este estado inicial demora para ser resolvido");
                    estado_inicial_quebra_cabeca(1);
                } 
                busca = new Largura();
                busca.solucionar(no);
                System.out.println("Quantidade de nos expandidos: " + busca.getContador());
                System.out.println("Total de movimentações: " + busca.getMovimentos());
                solucao = busca.getSolucao();
                busca.setContador(0);
        } if (n == 3){
        
            System.out.println("Você escolheu Busca gulosa");
            estado_inicial = escolher_estado_inicial();
            if(estado_inicial == 1){
                estado_inicial_quebra_cabeca(0);
            }else if(estado_inicial == 2) {
                estado_inicial_quebra_cabeca(1);
            }      
            heuristica = escolher_heuristica();       
            if ((heuristica == 0) || (heuristica == 1)){
                Gulosa gula = new Gulosa();
                gula.setHeuristica(heuristica);
                gula.solucionar(no);
                System.out.println("Quantidade de nos expandidos: " + gula.getContador());
                System.out.println("Total de movimentações: " + gula.getMovimentos());
                solucao = gula.getSolucao();
                gula.setMovimentos(0);
                gula.setContador(0);  
            } else {
               System.out.println("Digite os numeros solicitados");
            }

        } else if(n == 4) {
        
            System.out.println("Você escolheu Busca A*");
            estado_inicial = escolher_estado_inicial();
            if(estado_inicial == 1){
                estado_inicial_quebra_cabeca(0);
            }else if(estado_inicial == 2) {
                estado_inicial_quebra_cabeca(1);
            }
            heuristica = escolher_heuristica();
            if ((heuristica == 0) || (heuristica == 1)){
                AEstrela estrela = new AEstrela();
                estrela.setHeuristica(heuristica);
                estrela.solucionar(no);
                System.out.println("Quantidade de nos expandidos: " + estrela.getContador());
                System.out.println("Total de movimentações: " + estrela.getMovimentos());
                solucao = estrela.getSolucao();
                estrela.setMovimentos(0);
                estrela.setContador(0);
            } else {
               System.out.println("Digite os numeros solicitados");

            }
        }
    
    }
    
    
}
