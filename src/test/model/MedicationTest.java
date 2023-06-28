package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {
    Medication m;

    @BeforeEach
    void setup(){
        m = new Medication("aspirin");
    }

    @Test
    void testMedicationConstructor(){
        assertEquals("aspirin",m.getMedicationName());
    }



    @Test
    void testGetMedicationName() {
        assertEquals("aspirin",m.getMedicationName());
    }

    @Test
    void testJson() {
        try {
            PatientProfile pp = new PatientProfile("Ning",19990526,"female");
            pp.addMedication(m);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatientProfile.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPatientProfile.json");
            pp = reader.read();
            assertEquals("Ning", pp.getName());
            assertEquals(19990526, pp.getBirthday());
            assertEquals("female", pp.getGender());
            assertEquals(1,pp.getAllergyMedications().size());
            assertEquals("aspirin",pp.getAllergyMedications().get(0).getMedicationName());
            assertEquals(0, pp.getMedicalHistory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
