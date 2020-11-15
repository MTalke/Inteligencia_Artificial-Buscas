/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.tp1;

import inicio.Class_principal;

/**
 *
 * @author tg
 */
public class Main {

    public static Class_principal classe_principal;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      Chamando o classe principal que é responsável 
//      por apresentar as opções para o usuário escolher.
        classe_principal = new Class_principal();
        classe_principal.menu();
    }
    
    
}
