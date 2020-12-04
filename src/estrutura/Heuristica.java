/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import logica.No;

/**
 *
 * @author tg
 */
public interface Heuristica {
    public int pecasForaDoLugar(No no);
    public int distanciaDeManhantan(No no);
}
