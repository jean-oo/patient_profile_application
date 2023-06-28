package persistence;

import model.Case;
import model.Medication;
import model.PatientProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PatientProfile pp = new PatientProfile("Ning",19990526,"female");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPatientProfile() {
        try {
            PatientProfile pp = new PatientProfile("Ning",19990526,"female");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatientProfile.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPatientProfile.json");
            pp = reader.read();
            assertEquals("Ning", pp.getName());
            assertEquals(19990526, pp.getBirthday());
            assertEquals("female", pp.getGender());
            assertEquals(0,pp.getAllergyMedications().size());
            assertEquals(0, pp.getMedicalHistory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPatientProfile() {
        try {
            PatientProfile pp = new PatientProfile("Ning",19990526,"female");
            pp.addCase(new Case(20180308, "Joy", "No.1 hospital", "cold", "headache"));
            pp.addCase(new Case(20190130, "Amy", "NO.2 clinic", "cold", "cough"));
            pp.addMedication(new Medication("aspirin"));
            pp.addMedication(new Medication("penicillin"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPatientProfile.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPatientProfile.json");
            pp = reader.read();
            assertEquals("Ning", pp.getName());
            assertEquals(19990526, pp.getBirthday());
            assertEquals("female", pp.getGender());

            List<Case> cases = pp.getMedicalHistory();
            assertEquals(2, cases.size());
            checkCase(20180308,"Joy","No.1 hospital","cold","headache", cases.get(0));
            checkCase(20190130, "Amy", "NO.2 clinic", "cold", "cough", cases.get(1));

            List<Medication> medications = pp.getAllergyMedications();
            assertEquals(2,medications.size());
            checkMedication("aspirin",medications.get(0));
            checkMedication("penicillin",medications.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}