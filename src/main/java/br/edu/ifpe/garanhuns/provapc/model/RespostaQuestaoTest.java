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
public class RespostaQuestaoTest {
    private final Questao questao1;
    private final RespostaQuestao respostaQuestao;
    private final Alternativa a;
    private final Alternativa b;
    
    public RespostaQuestaoTest() {
        questao1 = new Questao("Quantos dedos Lula tem?",5);
        a = new Alternativa("19", true);
        b = new Alternativa("29", false);
        questao1.adicionarAlternativa(a);
        questao1.adicionarAlternativa(b);
        respostaQuestao = new RespostaQuestao(questao1);
    }
  
    @Test
    public void testCalcularPontuacaoQuandoAlternativaEscolhidaForVerdadeira(){
        respostaQuestao.setEscolhida(a);
        assertEquals(respostaQuestao.calcularPontuacao(), 5,0.1);
    }
    @Test
    public void testCalcularPontuacaoQuandoAlternativaEscolhidaForFalsa(){
        respostaQuestao.setEscolhida(b);
        assertEquals(respostaQuestao.calcularPontuacao(), 0,0.1);
    }
}