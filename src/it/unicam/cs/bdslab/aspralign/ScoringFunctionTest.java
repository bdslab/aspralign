package it.unicam.cs.bdslab.aspralign;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ScoringFunctionTest {

    @Test
    void testF() {
	ScoringFunction sf = new ScoringFunction("ASPRAlign-config.txt");
	assertTrue(sf.f("H(2,3)", "H(3,4)") == 0.0);
	assertTrue(sf.f("H(2,3)", "(" + Operators.CROSSING_LABEL + ","
		+ "2)") == Double.POSITIVE_INFINITY);
	assertTrue(sf.f("(" + Operators.CROSSING_LABEL + "," + "2)",
		"(" + Operators.CROSSING_LABEL + "," + "2)") == 0.0);
	assertTrue(sf.f("(" + Operators.CROSSING_LABEL + "," + "2)",
		"(" + Operators.CROSSING_LABEL + "," + "1)") == 1.0);
    }

}
