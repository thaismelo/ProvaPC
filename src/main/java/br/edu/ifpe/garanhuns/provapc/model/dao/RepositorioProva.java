/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model.dao;

import java.util.List;

import br.edu.ifpe.garanhuns.provapc.model.Prova;

/**
 *
 * @author Thais
 */
public class RepositorioProva implements RepositorioGenerico<Prova, Integer>{
        
    public void inserir(Prova t) {
        DaoManagerHiber.getInstance().persist(t);
    }

    
    public void excluir(Prova t) {
        DaoManagerHiber.getInstance().delete(t);
    }

    
    public void alterar(Prova t) {
        DaoManagerHiber.getInstance().update(t);
    }

    
    public Prova recuperar(Integer g) {
        try {
            return (Prova) DaoManagerHiber.getInstance().recover("from Prova where id=" + g).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


        public List<Prova> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Prova");
    }
    
    
}
