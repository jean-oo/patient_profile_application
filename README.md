# Patient Profile Application
## My Personal Project

This application is designed for doctors to recording patient's present illness, familiarize with patient's health condition. Doctors can retrieve patient's medical history, and knowing some matters needing attention about patient like allergy drug right away.

I design this application because when I go to see a doctor, and doctor ask a quetion about medical history. It's hard to recall these things if it happens very long time ago. If doctors knows about ours conditions more accurate, maybe it can help doctor improving efficiency on diagnosing and healing patient.

## User Stories

- As a user, I want to be able to add a present illness to patient profile
- As a user, I want to be able to view a patient's medical history
- As a user, I want to be able to retrieve particular illness from medical history
- As a user, I want to be able to retrieve particular medication from allergy medication
- As a user, I want to be able to save patient profile to file
- As a user, I want to be able to load patient profile from file 

## Phase 4: Task 2
I choose Bi-directional association.

Bi-directional association between Case class and PatientProfile class.

In PatientProfile, *addCase(Case c)*, if the medical history doesn't contain 
the present illness(case), we add the present illness(case) to the patient, and add this patient to this case.

In Case, *addPatientProfile(PatientProfile pp)*, if the list of patients doesn't contain this patient,
 we add this patient profile to the list of patients, and add the case to patient

## Phase 4: Task 3

- I'd like to refactor the method sound() in AddingPanel, ButtonPanel, and 
  searchPanel. Extracting the method and generate a class, let  AddingPanel, ButtonPanel, 
  and SearchPanel  to extend it.
- I'd like to refactor PatientProfileApp class. Subtracting the Medication
  class and Case class from field, and then generate and add medication and case 
  to a patient profile by patientProfile.add(new Case()), and patientProfile.add(new Medication())
- Change the name of Case class to PresentIllness, easier to know what the class abut
  
