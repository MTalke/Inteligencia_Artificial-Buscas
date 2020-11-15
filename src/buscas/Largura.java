/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;

import estrutura.Busca;
import logica.No;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author tg
 */
public class Largura extends Busca {
    
    
    public Largura() {
        fila = new ArrayList<>();
        solucao = new ArrayList<>();
        movimentos = 0;
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
    public void expandir(logica.No no) {
        contador++;
        int zero[] = localizaZero(no);
        int i = zero[0];
        int j = zero[1];
        if (i - 1 >= 0) {
            logica.No novo = new logica.No();
            novo.setPai(no);
            int estado[][] = new int[3][3];
            copiar(no.getEstados(), estado);
            int aux = estado[i][j];
            estado[i][j] = estado[i - 1][j];
            estado[i - 1][j] = aux;
            novo.setEstados(estado);
            fila.add(novo);
        }

        if (i + 1 < objetivo[j].length) {
            logica.No novo = new logica.No();
            novo.setPai(no);
            int estado[][] = new int[3][3];
            copiar(no.getEstados(), estado);
            int aux = estado[i][j];
            estado[i][j] = estado[i + 1][j];
            estado[i + 1][j] = aux;
            novo.setEstados(estado);
            fila.add(novo);
        }

        if (j - 1 >= 0) {
            logica.No novo = new logica.No();
            novo.setPai(no);
            int estado[][] = new int[3][3];
            copiar(no.getEstados(), estado);
            int aux = estado[i][j];
            estado[i][j] = estado[i][j - 1];
            estado[i][j - 1] = aux;
            novo.setEstados(estado);
            fila.add(novo);
        }

        if (j + 1 < objetivo[i].length) {
            logica.No novo = new logica.No();
            novo.setPai(no);
            int estado[][] = new int[3][3];
            copiar(no.getEstados(), estado);
            int aux = estado[i][j];
            estado[i][j] = estado[i][j + 1];
            estado[i][j + 1] = aux;
            novo.setEstados(estado);
            fila.add(novo);
        }

    }

    /*
        Localiza a peça vazia no estado (0)
     */
    private int[] localizaZero(logica.No no) {
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
        verifica se o estado é o objetivo final
     */
    private boolean objetivo_final(logica.No no) {
        int estado[][] = no.getEstados();
        for (int i = 0; i < objetivo.length; i++) {
            for (int j = 0; j < objetivo[i].length; j++) {
                    System.out.println("peça movimentada = " + estado[i][j] + " objetivo = " + objetivo[i][j]);
                if (estado[i][j] != objetivo[i][j]) {
                    System.out.println("não é objetivo");
                    return false;
                } else {
                    System.out.println("Encontrado um dos objetivos");
                }
            }
        }
        return true;
    }

    @Override
    public void solucionar(logica.No no) {
        fila.add(no);
        while (!fila.isEmpty()) {
            logica.No node = (logica.No) fila.remove(0);
            if (objetivo_final(node)) {
                System.out.println("Busca por Largura - Solução encontrada! " + node.getCusto());
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
    }

}
