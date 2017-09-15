/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model.dao;

import java.util.List;

import br.edu.ifpe.garanhuns.provapc.model.RespostaProva;

/**
 *
 * @author Thais
 */
public class RepositorioRespostaProva implements RepositorioGenerico<RespostaProva, Integer>{

    
    public void inserir(RespostaProva t) {
        DaoManagerHiber.getInstance().persist(t);
    }

    
    public void excluir(RespostaProva t) {
        DaoManagerHiber.getInstance().delete(t);
    }

    
    public void alterar(RespostaProva t) {
        DaoManagerHiber.getInstance().update(t);
    }

    
    public RespostaProva recuperar(Integer g) {
        try{
        return (RespostaProva) DaoManagerHiber.getInstance().recover("from RespostaProva where id="+ g).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
        }

    
    public List<RespostaProva> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from RespostaProva");
    }
    
    
}
