/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel Alves
 */
public class QuestaoTest {

    private Questao questao;
    private int quantidadeDeAlternativas;

    public QuestaoTest() {
        questao = new Questao("Quantos dedos Lula tem?", 5);
        quantidadeDeAlternativas = questao.getAlternativas().size();
    }

    @Test
    public void testAdicionarAlternativa() {
        Alternativa a = new Alternativa("19", true);
        questao.adicionarAlternativa(a);
        assertEquals(questao.getAlternativas().get((int) a.getId()), a);
    }

    @Test
    public void testRemoverAlternativa() {
        Alternativa a = new Alternativa("19", true);
        Alternativa b = new Alternativa("29", false);
        questao.adicionarAlternativa(a);
        questao.adicionarAlternativa(b);
        questao.removerAlternativa(b);
        assertEquals(quantidadeDeAlternativas+1, questao.getAlternativas().size());

    }

    
    @Test
    public void testCopiar() {
        Alternativa a = new Alternativa("19", true);
        questao.adicionarAlternativa(a);
        assertEquals(questao.copiar(),questao);
    }
}

