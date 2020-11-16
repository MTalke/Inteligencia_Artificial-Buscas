/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author tg
 */
public class No implements Comparable<No> {

    private No pai;
//    Matriz com o estado inicial escolhido pelo usuário
    private int estados[][]; 
    private int fator;
    private int custo;
    
    
    public No() {
        custo = 0;
        fator = 0;
        custo = 0;
    }

    @Override
    public int compareTo(No o) {
        if(this.fator < o.getFator()){
            return -1;
        }else if (this.fator > o.getFator()){
            return 1;
        }
        return 0;    
    }

    /**
     * @return the pai
     */
    public No getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(No pai) {
        this.pai = pai;
    }

    /**
     * @return the estados
     */
    public int[][] getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(int[][] estados) {
        this.estados = estados;
    }

    /**
     * @return the fator
     */
    public int getFator() {
        return fator;
    }

    /**
     * @param fator the fator to set
     */
    public void setFator(int fator) {
        this.fator = fator;
    }

    /**
     * @return the custo
     */
    public int getCusto() {
        return custo;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(int custo) {
        this.custo = custo;
    }
    
}
