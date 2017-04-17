import static org.junit.Assert.*;

import org.junit.Test;


public class tester_huffman {

	@Test
	public void test() {
		User_interface ui_obj = new User_interface();
		assertEquals(ui_obj.doHuffman("eeyjjjj"),"eeyjjjj");
		assertEquals(ui_obj.doHuffman("My Test works totally fine"),"My Test works totally fine");
	}

}
