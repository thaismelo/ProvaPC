/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model.dao;

import br.edu.ifpe.garanhuns.provapc.model.Turma;
import java.util.List;

/**
 *
 * @author Thais
 */
public class RepositorioTurma implements RepositorioGenerico<Turma, Integer>{

    @Override
    public void inserir(Turma t) {
        DaoManagerHiber.getInstance().persist(t);
    }

    @Override
    public void excluir(Turma t) {
        DaoManagerHiber.getInstance().delete(t);
    }

    @Override
    public void alterar(Turma t) {
        DaoManagerHiber.getInstance().update(t);
    }

    @Override
    public Turma recuperar(Integer g) {
        try {
            return (Turma) DaoManagerHiber.getInstance().recover("from Turma where id=" + g).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public List<Turma> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Turma");
    }
    
}
