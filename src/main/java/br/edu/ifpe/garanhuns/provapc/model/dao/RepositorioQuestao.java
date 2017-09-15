/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model.dao;

import java.util.List;

import br.edu.ifpe.garanhuns.provapc.model.Questao;

/**
 *
 * @author Thais
 */
public class RepositorioQuestao implements RepositorioGenerico<Questao, Integer> {

    
    public void inserir(Questao t) {
        DaoManagerHiber.getInstance().persist(t);
    }

    
    public void excluir(Questao t) {
        DaoManagerHiber.getInstance().delete(t);
    }

    
    public void alterar(Questao t) {
        DaoManagerHiber.getInstance().update(t);
    }

    
    public Questao recuperar(Integer g) {
        try {
            return (Questao) DaoManagerHiber.getInstance().recover("from Questao where id=" + g).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


        public List<Questao> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Questao");
    }
    

    
}
