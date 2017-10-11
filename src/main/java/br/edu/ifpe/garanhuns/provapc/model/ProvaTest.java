package br.edu.ifpe.garanhuns.provapc.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel Alves
 */
public class ProvaTest {

    private Prova prova;
    private int quantidadeDeQuestoes;

    public ProvaTest() {
        prova = new Prova();
        quantidadeDeQuestoes = prova.getQuestoes().size();
    }

    @Test
    public void testaSeQuestaoFoiAdicionadaNaProva() {
        Questao questao1 = new Questao("Quanto é dois mais dois?", 5);
        prova.adicionarQuestao(questao1);
        assertEquals(prova.getQuestoes().get((int) questao1.getId()), questao1);

    }

    @Test
    public void testaSeQuestaoFoiRemovidaDaProva() {
        Questao questao1 = new Questao("Qual a primeira vogal?", 5);
        Questao questao2 = new Questao("Qual a capital do Acre?", 5);
        prova.adicionarQuestao(questao1);
        prova.adicionarQuestao(questao2);
        prova.removerQuestao(questao2);
        assertTrue(quantidadeDeQuestoes+1==prova.getQuestoes().size());
    }
    /**
     * Test of copiar method, of class Prova.
     */
    @Test
    public void testCopiar(){
        Questao questao1 = new Questao("Quanto é dois mais dois?", 5);
        prova.adicionarQuestao(questao1);
        assertEquals(prova.copiar(),prova);
    }

}