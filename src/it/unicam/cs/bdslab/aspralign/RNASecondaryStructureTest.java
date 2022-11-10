/**
 * 
 */
package it.unicam.cs.bdslab.aspralign;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * @author lucatesei
 *
 */
class RNASecondaryStructureTest {

    /**
     * Test method for
     * {@link it.unicam.cs.bdslab.aspralign.RNASecondaryStructure#isPseudoknotted()}.
     */
    @Test
    void testIsPseudoknotted() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader
		.readStructure("test/edbn/CRW_16S_A_C_1.db", false);
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/edbn/CRW_16S_A_C_19.db", false);
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader
		.readStructure("test/edbn/CRW_5S_A_C_20.db", false);
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn/CRW_5S_A_C_22.db", false);
	assertTrue(s1.isPseudoknotted());
	assertTrue(s2.isPseudoknotted());
	assertFalse(s3.isPseudoknotted());
	assertFalse(s4.isPseudoknotted());

    }

}
