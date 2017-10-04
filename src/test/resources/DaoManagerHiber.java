import static org.junit.Assert.*;

import org.junit.Test;

public class DaoManagerHiber {

	
	@Before
	DaoManagerHiber dao = new DaoManagerHiber();
	
	@Test
	public int testeSeProvaFoiCadastrada(){
		Prova prova = new Prova();
		assertEquals(1, 1);
	}

}