/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model.dao;

import java.util.List;

import br.edu.ifpe.garanhuns.provapc.model.Turma;

/**
 *
 * @author Thais
 */
public class RepositorioTurma implements RepositorioGenerico<Turma, Integer>{

    
    public void inserir(Turma t) {
        DaoManagerHiber.getInstance().persist(t);
    }

    
    public void excluir(Turma t) {
        DaoManagerHiber.getInstance().delete(t);
    }

    
    public void alterar(Turma t) {
        DaoManagerHiber.getInstance().update(t);
    }

    public Turma recuperar(Integer g) {
        try {
            return (Turma) DaoManagerHiber.getInstance().recover("from Turma where id=" + g).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    
    public List<Turma> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Turma");
    }
    
}
