/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;

import estrutura.Busca;
import logica.No;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author tg
 */
public class Profundidade extends Busca {
    
    private int max;
    private final int profundidade;
    private boolean solucionado;


    public Profundidade() {
        pilha = new Stack();
        solucao = new ArrayList<>();
        max = 0;
        movimentos = 0;
        profundidade = 0;
    }
    
    public void setMax(int max) {
        this.max = max;
    }  
    
    /*
        verifica se o estado é o objetivo final
     */
    private boolean objetivo_final(No no) {
        int estado[][] = no.getEstados();
        for (int i = 0; i < objetivo.length; i++) {
            for (int j = 0; j < objetivo[i].length; j++) {
                    System.out.println("peça movimentada = " + estado[i][j] + " objetivo = " + objetivo[i][j]);
                if (estado[i][j] != objetivo[i][j]) {
                    System.out.println("não é objetivo");
                    return false;
                }else {
                    System.out.println("Encontrado um dos objetivos");
                }
            }
        }
        return true;
    }
    
    /*
        Localiza a peça vazia no estado (0)
    */
    private int[] localizaZero(No no) {
        int zero[] = new int[2];
        for (int i = 0; i < no.getEstados().length; i++) {
            for (int j = 0; j < no.getEstados()[i].length; j++) {
                if (no.getEstados()[i][j] == 0) {
                    zero[0] = i;
                    zero[1] = j;
                    return zero;
                }
            }
        }
        return zero;
    }
    
    
    /*
        Copia o novo estado para um novo Nó
    */
    private void copiar(int matriz[][], int estado[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                estado[i][j] = matriz[i][j];
            }
        }
    }

    
    
    /*
        Expande os estados
    */
    public void expandir(No no) {
        contador++;
        if (no.getCusto() < max) {
            int zero[] = localizaZero(no);
            int i = zero[0];
            int j = zero[1];
            if (i - 1 >= 0) {
                No novo = new No();
                novo.setPai(no);
                int estado[][] = new int[3][3];
                copiar(no.getEstados(), estado);
                int aux = estado[i][j];
                estado[i][j] = estado[i - 1][j];
                estado[i - 1][j] = aux;
                novo.setEstados(estado);
                novo.setCusto(no.getCusto() + 1);
                pilha.push(novo);
            }

            if (i + 1 < objetivo[j].length) {
                No novo = new No();
                novo.setPai(no);
                int estado[][] = new int[3][3];
                copiar(no.getEstados(), estado);
                int aux = estado[i][j];
                estado[i][j] = estado[i + 1][j];
                estado[i + 1][j] = aux;
                novo.setEstados(estado);
                novo.setCusto(no.getCusto() + 1);
                pilha.push(novo);
            }
            
            if (j - 1 >= 0) {
                No novo = new No();
                novo.setPai(no);
                int estado[][] = new int[3][3];
                copiar(no.getEstados(), estado);
                int aux = estado[i][j];
                estado[i][j] = estado[i][j - 1];
                estado[i][j - 1] = aux;
                novo.setEstados(estado);
                novo.setCusto(no.getCusto() + 1);
                pilha.push(novo);
            }

            if (j + 1 < objetivo[i].length) {
                No novo = new No();
                novo.setPai(no);
                int estado[][] = new int[3][3];
                copiar(no.getEstados(), estado);
                int aux = estado[i][j];
                estado[i][j] = estado[i][j + 1];
                estado[i][j + 1] = aux;
                novo.setEstados(estado);
                novo.setCusto(no.getCusto() + 1);
                pilha.push(novo);
            }
        }
        
    }
    
    
    /*
        Aprofundamento Iterativo
     */
    private void iteracaoLimite(No no) {
        if (!solucionado) {
            contador = 0;
            max++;
            solucionar(no);
        }
    }

    
    @Override
    public void solucionar(No no) {
        no.setCusto(profundidade);
        
    //  Empilhando
        pilha.push(no);
//        System.out.println("/n operacao de empilhando");
        while (!pilha.isEmpty()) {
    //  Desempilhando
            No node = pilha.pop();
//            System.out.println("/n operacao de desempilhar" + node.getEstados());
            if (objetivo_final(node)) {
                solucionado = true;
                System.out.println("Busca por Profundidade - Solução encontrada!");
                while (node.getPai() != null) { 
                    solucao.add(node);
                    node = node.getPai();                    
                    movimentos++;
                }                
                
                break;
                
            } else {
                expandir(node);
            }
        }
        iteracaoLimite(no);    
    }
    
}
