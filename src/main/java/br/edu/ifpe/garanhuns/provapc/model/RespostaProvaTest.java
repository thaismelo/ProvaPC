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
 * @author Thais
 */
public class RespostaProvaTest {
    Prova prova;
    Questao questao1;
    Questao questao2;
    Alternativa a1;
    Alternativa a2;
    Alternativa b1;
    Alternativa b2;
    RespostaQuestao respostaQuestao1;
    RespostaQuestao respostaQuestao2;

    RespostaProva respostaProva;

    public RespostaProvaTest() {
        this.prova = new Prova();
        
        this.questao1 = new Questao("Quando o Brasil foi descoberto?",5);
        this.a1 = new Alternativa("2017",false);
        this.b1 = new Alternativa("1500",true);
        questao1.adicionarAlternativa(a1);
        questao1.adicionarAlternativa(b1);
        
        this.questao2 = new Questao("Quanto é 3 + 3?",5);
        this.a2 = new Alternativa("9", false);
        this.b2 = new Alternativa("6",true);
        questao2.adicionarAlternativa(a2);
        questao2.adicionarAlternativa(b2);
        
        prova.adicionarQuestao(questao1);
        prova.adicionarQuestao(questao2);
        
        this.respostaQuestao1 = new RespostaQuestao(questao1);
        this.respostaQuestao2 = new RespostaQuestao(questao2);
        this.respostaProva = new RespostaProva(prova);
    }
    
    @Test
    public void testaResultadoDaProva() {
        respostaQuestao1.setEscolhida(b1);
        respostaQuestao2.setEscolhida(a2);
        respostaProva.adicionar(respostaQuestao1);
        respostaProva.adicionar(respostaQuestao2);
        
        assertEquals(5, respostaProva.resultado(),0.1);
    }
    
}
