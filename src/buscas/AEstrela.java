/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;

import estrutura.Busca;
import estrutura.Heuristica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import logica.No;

/**
 *
 * @author tg
 */
public class AEstrela extends Busca implements Heuristica {

   private int heuristica;
    private List<No> visitados;

    public AEstrela() {
        fila = new ArrayList<>();
        visitados = new ArrayList<>();
        solucao = new ArrayList<>();
        movimentos = 0;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    /*
        Expande os estados
     */
    public void expandir(No no, int h) {
        contador++;
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
            definirFator(novo);
            if (avaliado(novo, visitados) == -1) {
                int k = avaliado(novo, fila);
                if (k != -1) {
                    if (fila.get(k).getFator() > novo.getFator()) {
                        fila.remove(k);
                    } 
                } else {
                    fila.add(novo);
                }
            }
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
            definirFator(novo);
            if (avaliado(novo, visitados) == -1) {
                int k = avaliado(novo, fila);
                if (k != -1) {
                    if (fila.get(k).getFator() > novo.getFator()) {
                        fila.remove(k);
                    } 
                } else {
                    fila.add(novo);
                }
            }
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
            definirFator(novo);
            if (avaliado(novo, visitados) == -1) {
                int k = avaliado(novo, fila);
                if (k != -1) {
                    if (fila.get(k).getFator() > novo.getFator()) {
                        fila.remove(k);
                    } 
                } else {
                    fila.add(novo);
                }
            }
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
            definirFator(novo);
            if (avaliado(novo, visitados) == -1) {
                int k = avaliado(novo, fila);
                if (k != -1) {
                    if (fila.get(k).getFator() > novo.getFator()) {
                        fila.remove(k);
                    } 
                } else {
                    fila.add(novo);
                }
            }

        }
        Collections.sort(fila);
    }

    /*
        Verifica se o nó que foi expandido já consta na fila ou na lista de visitados
    */
    private int avaliado(No no, List<No> conjunto) {
        for (int k = 0; k < conjunto.size(); k++) {
            if (iguais(conjunto.get(k), no)) {
                return k;
            }
        }
        return -1;
    }

    /*
        Verifica se dois nós são iguais
    */
    private boolean iguais(No no, No novo) {
        for (int i = 0; i < no.getEstados().length; i++) {
            for (int j = 0; j < no.getEstados()[i].length; j++) {
                if (no.getEstados()[i][j] != novo.getEstados()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        Configura a heuristica para a função de avaliação do nó
    */
    private void definirFator(No no) {
        if (heuristica == 0) {
            no.setFator(pecasForaDoLugar(no) + no.getCusto());
        } else if (heuristica == 1) {
            no.setFator(distanciaDeManhantan(no) + no.getCusto());
        }
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
                } else {
                    System.out.println("Encontrado um dos objetivos");
                }
            }
        }
        return true;
    }

    @Override
    public void solucionar(No no) {
        definirFator(no);
        fila.add(no);
        while (!fila.isEmpty()) {
            No node = (No) fila.remove(0);
            visitados.add(node);
            if (objetivo_final(node)) {
                visitados.clear();
                System.out.println("Solução encontrada! Profundidade:" + node.getCusto());
                while (node.getPai() != null) {
                    solucao.add(node);
                    node = node.getPai();
                    movimentos++;
                }

                break;
            } else {
                expandir(node, heuristica);
            }
        }
    }

    @Override
    public int pecasForaDoLugar(No no) {
        int peca = 0;
        for (int i = 0; i < no.getEstados().length; i++) {
            for (int j = 0; j < no.getEstados()[i].length; j++) {
                if (no.getEstados()[i][j] != objetivo[i][j] && no.getEstados()[i][j] != 0) {
                    peca++;
                }
            }
        }
        return peca;
    }

    /*
        Heuristica para o melhor fator - peças fora do lugar
     */
    @Override
    public int distanciaDeManhantan(No no) {
        int distancia = 0;
        for (int i = 0; i < no.getEstados().length; i++) {
            for (int j = 0; j < no.getEstados()[i].length; j++) {
                int[] coord = getCoordenadas(no.getEstados()[i][j]);
                distancia += Math.abs(i - coord[0]) + Math.abs(j - coord[1]);
            }
        }
        return distancia;
    }
    
    /*
        Obtem o valor da linha e da coluna para um valor no estado
    */
    private int[] getCoordenadas(int valor) {
        int[] coord = new int[2];
        for (int i = 0; i < objetivo.length; i++) {
            for (int j = 0; j < objetivo[i].length; j++) {
                if (valor == objetivo[i][j]) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return null;
    }

    
}
