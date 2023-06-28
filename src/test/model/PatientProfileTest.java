package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientProfileTest {
    PatientProfile pro;
    Case c1;
    Case c2;
    Case c3;
    Medication m1;
    Medication m2;

    @BeforeEach
    void setup() {
        pro = new PatientProfile("Jane", 19990716, "Female");
        c1 = new Case(20180308, "Joy", "No.1 hospital", "cold", "headache");
        c2 = new Case(20190130, "Amy", "NO.2 clinic", "cold", "cough");
        c3 = new Case(20201001, "James", "NO.3 clinic", "fever", "hyperthermia");
        pro.addCase(c1);
        pro.addCase(c2);
        pro.addCase(c3);

        m1 = new Medication("penicillin");
        m2 = new Medication("aspirin");
        pro.addMedication(m1);
        pro.addMedication(m2);
    }

    @Test
    void testConstructor() {
        List<Medication> m = new ArrayList<>();
        m.add(m1);
        m.add(m2);

        List<Case> c = new ArrayList<>();
        c.add(c1);
        c.add(c2);
        c.add(c3);

        assertEquals("Jane", pro.getName());
        assertEquals(19990716, pro.getBirthday());
        assertEquals("Female", pro.getGender());
        assertEquals(m, pro.getAllergyMedications());
        assertEquals(c, pro.getMedicalHistory());
    }

    @Test
    void testGetAge() {

        assertEquals(21, pro.getAge(20201012));

    }

    @Test
    void testAddMedication() {
        Medication m = new Medication("cephalosporins");
        pro.addMedication(m);
        List<Medication> medications = pro.getAllergyMedications();
        assertEquals(3, medications.size());
        assertEquals(m1, medications.get(0));
        assertEquals(m2, medications.get(1));
        assertEquals(m, medications.get(2));

    }

    @Test
    void testGetAllergyMedications() {
        List<Medication> list = pro.getAllergyMedications();
        assertEquals(2, list.size());
        assertEquals(m1, list.get(0));
        assertEquals(m2, list.get(1));

    }

    @Test
    void testNotAllergyToMedication() {
        assertFalse(pro.isAllergyToMedication("streptomycin"));
    }

    @Test
    void testAllergyToMedication() {

        assertTrue(pro.isAllergyToMedication("aspirin"));

    }

    @Test
    void testRemoveMedication() {
        pro.removeMedication(m1);
        List<Medication> m = pro.getAllergyMedications();
        assertEquals(1, m.size());
        assertEquals(m2, m.get(0));
    }

    @Test
    void testNoHappenBefore() {
        List<Case> c = new ArrayList<>();
        assertEquals(c, pro.doseHappenBefore("AOM"));
    }

    @Test
    void testHappenOnceBefore() {
        ArrayList<Case> cases = pro.doseHappenBefore("fever");
        assertEquals(1, cases.size());
        assertEquals(c3, cases.get(0));
    }

    @Test
    void testHappenTwiceBefore() {
        ArrayList<Case> cases = pro.doseHappenBefore("cold");
        assertEquals(2, cases.size());
        assertEquals(c1, cases.get(0));
        assertEquals(c2, cases.get(1));
    }

    @Test
    void testAddCase() {
        Case c = new Case(20201013, "Bella", "NO.4 hospital", "coronary disease",
                "chest pain");
        pro.addCase(c);
        List<Case> cases = pro.getMedicalHistory();
        assertEquals(4, cases.size());
        assertEquals(c1, cases.get(0));
        assertEquals(c2, cases.get(1));
        assertEquals(c3, cases.get(2));
        assertEquals(c, cases.get(3));
    }

    @Test
    void testNoMedicalHistory() {
        PatientProfile p = new PatientProfile("Frank", 20200908, "m");
        List<Case> mh = p.getMedicalHistory();
        assertEquals(0, mh.size());
    }

    @Test
    void testExistMedicalHistory() {
        List<Case> mh = pro.getMedicalHistory();
        assertEquals(3, mh.size());
        assertEquals(c1, mh.get(0));
        assertEquals(c2, mh.get(1));
        assertEquals(c3, mh.get(2));
    }


    @Test
    void testGetBirthday() {
        assertEquals(19990716, pro.getBirthday());
    }

    @Test
    void testAddSameCase() {
        Case c4 = new Case(20180308, "Joy", "No.1 hospital", "cold", "headache");
        pro.addCase(c4);
        List<Case> cases = pro.getMedicalHistory();
        assertEquals(3, cases.size());
        assertEquals(c1, cases.get(0));
        assertEquals(c2, cases.get(1));
        assertEquals(c3, cases.get(2));
    }


    @Test
    void testAddToNullMedicalHistory() {
        PatientProfile pp = new PatientProfile("Amy", 20201010, "female");
        pp.addCase(c1);
        assertEquals(1, pp.getMedicalHistory().size());
        assertEquals(c1, pp.getMedicalHistory().get(0));

    }

    @Test
    void testAddNullToMedicalHistory() {
        Case c4 = null;
        pro.addCase(c4);
        List<Case> cases = pro.getMedicalHistory();
        assertEquals(3, cases.size());
        assertEquals(c1, cases.get(0));
        assertEquals(c2, cases.get(1));
        assertEquals(c3, cases.get(2));

    }

    @Test
    void testEqualOfNull() {
        assertFalse(pro.equals(null));
    }

    @Test
    void testEqualOfSame() {
        assertTrue(pro.equals(pro));
    }

    @Test
    void testEqualOfDifferent() {
        PatientProfile pp = new PatientProfile("Amy", 20201010, "female");
        assertFalse(pro.equals(pp));
    }

    @Test
    void testEqualOfSame2() {
        PatientProfile pp = new PatientProfile("Jane", 19990716, "Female");
        assertTrue(pro.equals(pp));
    }

    @Test
    void testJson() {
            try {
                PatientProfile pp = new PatientProfile("Ning",19990526,"female");
                pp.addCase(c1);
                pp.addCase(c2);
                pp.addMedication(m1);
                pp.addMedication(m2);
                JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatientProfile.json");
                writer.open();
                writer.write(pp);
                writer.close();

                JsonReader reader = new JsonReader("./data/testWriterEmptyPatientProfile.json");
                pp = reader.read();
                assertEquals("Ning", pp.getName());
                assertEquals(19990526, pp.getBirthday());
                assertEquals("female", pp.getGender());
                assertEquals(2,pp.getAllergyMedications().size());
                assertEquals(2, pp.getMedicalHistory().size());
            } catch (IOException e) {
                fail("Exception should not have been thrown");
            }
        }

    @Test
    void testEqualNameBir() {
        PatientProfile p3 = new PatientProfile("Jane", 19990716, "Male");
        assertNotEquals(pro,p3);
    }

    @Test
    void testEqualName() {

        PatientProfile p2 = new PatientProfile("Jane", 19990715, "Female");
        assertNotEquals(pro,p2);
    }

    @Test
    void testDifferentClass() {
        Medication m = new Medication("Jane");
        assertNotEquals(pro,m);

    }

    }
