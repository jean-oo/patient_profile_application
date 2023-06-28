package persistence;

import model.Case;
import model.Medication;
import model.PatientProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PatientProfile pp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPatientProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPatientProfile.json");
        try {
            PatientProfile pp = reader.read();
            assertEquals("Ning",pp.getName());
            assertEquals("female",pp.getGender());
            assertEquals(19990526,pp.getBirthday());
            assertEquals(0,pp.getAllergyMedications().size());
            assertEquals(0,pp.getMedicalHistory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPatientProfile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPatientProfile.json");
        try {
            PatientProfile pp = reader.read();
            assertEquals("Ning",pp.getName());
            assertEquals("female",pp.getGender());
            assertEquals(19990526,pp.getBirthday());

            List<Case> cases = pp.getMedicalHistory();
            assertEquals(2, cases.size());
            checkCase(20180308,"Joy","No.1 hospital","cold","headache", cases.get(1));
            checkCase(20190130, "Amy", "NO.2 clinic", "cold", "cough", cases.get(0));

            List<Medication> medications = pp.getAllergyMedications();
            assertEquals(2,medications.size());
            checkMedication("aspirin",medications.get(1));
            checkMedication("penicillin",medications.get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
