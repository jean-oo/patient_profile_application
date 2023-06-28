package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {
    Case c1;
    PatientProfile p1;
    PatientProfile p2;
    PatientProfile p3;
    Case c2;

    @BeforeEach
    void setup(){
        c1 = new Case(20180308, "Joy", "No.1 hospital", "cold", "headache");
        p1 = new PatientProfile("Amy",20201010,"female");
        p2 = new PatientProfile("Amy", 20201010,"Male");
        p3 = new PatientProfile("Amy",20201010,"female");
        c2 = new Case(20180308, "Joy", "No.1 hospital", "cold", "headache");

    }

    @Test
    void testCaseConstructor(){
        assertEquals(20180308, c1.getDate());
        assertEquals("Joy", c1.getDoctor());
        assertEquals("No.1 hospital", c1.getHospital());
        assertEquals("headache",c1.getDetails());
        assertEquals("headache", c1.getDetails());
    }

    @Test
    void testGetIllness() {
        assertEquals("headache",c1.getDetails());
    }

    @Test
    void testGetDetails() {
        assertEquals("headache", c1.getDetails());
    }

    @Test
    void testGetDate() {
        assertEquals(20180308, c1.getDate());
    }

    @Test
    void testGetDoctor() {
        assertEquals("Joy", c1.getDoctor());
    }

    @Test
    void testGetHospital() {
        assertEquals("No.1 hospital", c1.getHospital());
    }

    @Test
    void testAddPatientProfile() {
        c1.addPatientProfile(p1);
        assertEquals(1,c1.getPatients().size());
        assertTrue(c1.getPatients().contains(p1));

    }

    @Test
    void testAddSamePatientProfile() {
        c1.addPatientProfile(p1);
        c1.addPatientProfile(p1);
        assertEquals(1,c1.getPatients().size());
        assertEquals(true,c1.getPatients().contains(p1));

    }

    @Test
    void testAddAnotherPatientProfile() {
        c1.addPatientProfile(p1);
        c1.addPatientProfile(p2);
        assertEquals(2,c1.getPatients().size());
        assertEquals(true,c1.getPatients().contains(p1));
        assertEquals(true,c1.getPatients().contains(p2));

    }

    @Test
    void testAddNullPatientProfile() {
        PatientProfile p4 = null;
        c1.addPatientProfile(p4);
        assertEquals(0,c1.getPatients().size());

    }

    @Test
    void testEqualIll() {
        Case c3 = new Case(20180308,"A","B","cold","D");
        assertNotEquals(c1,c3);
    }

    @Test
    void testEqualIllDate() {
        Case c3 = new Case(20180308,"A","B","cold","D");
        assertNotEquals(c1,c3);
    }

    @Test
    void testEqualIllDateDetails() {
        Case c3 = new Case(20180308,"A","B","cold","headache");
        assertNotEquals(c1, c3);
    }

    @Test
    void testEqualIllDateDetailsDr() {
        Case c3 = new Case(20180308,"Joy","B","cold","headache");
        assertNotEquals(c1, c3);
    }

    @Test
    void testEqualAll() {
        c1 = new Case(20180308, "Joy", "No.1 hospital", "cold", "headache");
        Case c3 = new Case(20180308, "Joy", "No.1 hospital", "cold", "headache");
        assertEquals(c1, c3);
    }

    @Test
    void testDifferentClass() {
        Medication m = new Medication("Joy");
        assertNotEquals(c1,m);

    }
    @Test
    void testEqualOfNull() {

        assertFalse(c1.equals(null));
    }

    @Test
    void testEqualDifferent() {
        Case c2 = new Case(20180303, "Joy", "No.1 hospital", "cold", "headache");
        assertFalse(c1.equals(c2));
    }

    @Test
    void testJson() {
        try {
            PatientProfile pp = new PatientProfile("Ning",19990526,"female");
            pp.addCase(c1);
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
            assertEquals(1, pp.getMedicalHistory().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testHashCode() {
        assertEquals(c1.hashCode(),c2.hashCode());
    }


}