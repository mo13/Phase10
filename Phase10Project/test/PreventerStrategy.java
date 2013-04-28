import static org.junit.Assert.*;

import org.junit.Test;


import model.*;
import strategy.*;
public class PreventerStrategy {
	Player cortona = new Player("Cortona");
	@Test
	public void testConstructor() {
		
		Preventer p = new Preventer();
		assertSame(p.player, null);
		p.setPlayer(cortona);
		assertSame(p.player, cortona);
		System.out.println(p.strat);
	}

}
