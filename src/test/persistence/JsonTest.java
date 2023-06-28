package persistence;

import model.Case;
import model.Medication;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCase(Integer date, String dr, String hospital, String illness, String details, Case c) {
        assertEquals(dr,c.getDoctor());
        assertEquals(date, c.getDate());
        assertEquals(hospital, c.getHospital());
        assertEquals(illness, c.getIllness());
        assertEquals(details, c.getDetails());
    }

    protected void checkMedication (String medi, Medication m) {
        assertEquals(medi,m.getMedicationName());
    }
}

