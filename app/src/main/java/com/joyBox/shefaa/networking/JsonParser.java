package com.joyBox.shefaa.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.joyBox.shefaa.entities.AppointmentEntity;
import com.joyBox.shefaa.entities.AvailableTime;
import com.joyBox.shefaa.entities.Doctor;
import com.joyBox.shefaa.entities.Lab;
import com.joyBox.shefaa.entities.Pharmacy;
import com.joyBox.shefaa.entities.PrescriptionFollowUp;
import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.entities.HomeAddress;
import com.joyBox.shefaa.entities.MedicinePotionEntity;
import com.joyBox.shefaa.entities.MessageResult;
import com.joyBox.shefaa.entities.Prescription;
import com.joyBox.shefaa.entities.RegisterNotificationResult;
import com.joyBox.shefaa.entities.TermsAndConditionsEntity;
import com.joyBox.shefaa.entities.TestResultEntity;

import junit.framework.TestResult;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-08-10.
 */

public class JsonParser {

    public static MessageResult getMessagesResult(String gsonString) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, MessageResult.class);
    }

    public static TermsAndConditionsEntity getTermsAndConditionsEntity(String gsonString) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, TermsAndConditionsEntity.class);
    }

    public static RegisterNotificationResult getRegisterNotificationResult(String gsonString) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, RegisterNotificationResult.class);
    }

    public static List<Prescription> getPrescription(String gsonstring) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(gsonstring, Prescription[].class));
    }


    /*client parse started*/
    public static Client getClient(String gsonstring) {
        Type type = new TypeToken<Client>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new ClientDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class ClientDeserializer implements JsonDeserializer<Client> {
        @Override
        public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Gson gson = new GsonBuilder().create();
            Client cnt = gson.fromJson(json, Client.class);

            JsonElement userelement = json.getAsJsonObject().get("user");
//            cnt.setUser(gson.fromJson(userelement, User.class));

            JsonElement Roleelement = userelement.getAsJsonObject().get("roles");

            /*cnt.Roles = new Vector<>();
            for (Map.Entry entity : Roleelement.getAsJsonObject().entrySet()) {
                cnt.Roles.add(entity.getValue().toString());
            }*/
            return cnt;
        }
    }
    /*client parse ended*/


    /*Appointment parse started*/
    public static List<AppointmentEntity> getAppointment(String gsonstring) {
        Type type = new TypeToken<List<AppointmentEntity>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new AppointmentDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class AppointmentDeserializer implements JsonDeserializer<List<AppointmentEntity>> {
        @Override
        public List<AppointmentEntity> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Gson gson = new GsonBuilder().create();

            List<AppointmentEntity> entityList = new Vector<>();
            for (JsonElement elmnt : json.getAsJsonArray()) {
                AppointmentEntity appointmentEntity = gson.fromJson(elmnt, AppointmentEntity.class);

                JsonElement urgentElement = elmnt.getAsJsonObject().get("Patient home address");
                if ((!urgentElement.isJsonArray()) && urgentElement.isJsonObject()) {
                    HomeAddress homeAddress = gson.fromJson(urgentElement, HomeAddress.class);
                    appointmentEntity.setPatientHomeAddress(homeAddress);
                }
                JsonElement homeElement = elmnt.getAsJsonObject().get("Home appointment");
                if (!homeElement.isJsonArray()) {
                    appointmentEntity.setHomeAppointment(homeElement.getAsString());
                }
                entityList.add(appointmentEntity);
            }
            return entityList;
        }
    }

    public static AvailableTime getAvailableTime(String gsonstring) {
        Gson gson = new Gson();
        return gson.fromJson(gsonstring, AvailableTime.class);
    }
    /*Appointment parse ended*/

    /*MedicinePotionEntity parse started*/
    public static List<MedicinePotionEntity> getMedicinePotionEntities(String gsonstring) {
        Type type = new TypeToken<List<MedicinePotionEntity>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new MedicinePotionEntityDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class MedicinePotionEntityDeserializer implements JsonDeserializer<List<MedicinePotionEntity>> {
        @Override
        public List<MedicinePotionEntity> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Gson gson = new GsonBuilder().create();

            List<MedicinePotionEntity> entityList = new Vector<>();
            for (JsonElement elmnt : json.getAsJsonArray()) {
                MedicinePotionEntity entity = gson.fromJson(elmnt, MedicinePotionEntity.class);

                JsonElement notesElmnt = elmnt.getAsJsonObject().get("Notes");
                if (!notesElmnt.isJsonArray()) {
                    String notes = notesElmnt.getAsString();
                    entity.setNotes_Parsed(notes);
                }

                JsonElement How_many_timesElement = elmnt.getAsJsonObject().get("How many times");
                if (!How_many_timesElement.isJsonArray()) {
                    String homeAddress = How_many_timesElement.getAsString();
                    entity.setHow_many_times(homeAddress);
                }

                JsonElement For_how_longElement = elmnt.getAsJsonObject().get("For how long");
                if (!For_how_longElement.isJsonArray()) {
                    entity.setFor_how_long(For_how_longElement.getAsString());
                }

                JsonElement For_how_long2Element = elmnt.getAsJsonObject().get("For how long2");
                if (!For_how_long2Element.isJsonArray()) {
                    entity.setFor_how_long2(For_how_long2Element.getAsString());
                }

                entityList.add(entity);
            }
            return entityList;
        }
    }

    /*MedicinePotionEntity parse ended*/

    /*Appointment Follow up started*/
    public static List<PrescriptionFollowUp> getAppointmentFollowUps(String gsonstring) {
        Type type = new TypeToken<List<PrescriptionFollowUp>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new AppointmentFollowUpDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class AppointmentFollowUpDeserializer implements JsonDeserializer<List<PrescriptionFollowUp>> {
        @Override
        public List<PrescriptionFollowUp> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Gson gson = new GsonBuilder().create();

            List<PrescriptionFollowUp> entityList = new Vector<>();
            for (JsonElement elmnt : json.getAsJsonArray()) {
                PrescriptionFollowUp entity = gson.fromJson(elmnt, PrescriptionFollowUp.class);

                JsonElement followElment = elmnt.getAsJsonObject().get("Indicator tid");
                if (!followElment.isJsonArray()) {
                    PrescriptionFollowUp.Indicator indicator = gson.fromJson(followElment, PrescriptionFollowUp.Indicator.class);
                    entity.setIndicator_tid(indicator);
                }
                JsonElement notesElmnt = elmnt.getAsJsonObject().get("Notes");
                if (!notesElmnt.isJsonArray()) {
                    String notes = notesElmnt.getAsString();
                    entity.setNotes_Parsed(notes);
                }
                JsonElement How_many_timesElement = elmnt.getAsJsonObject().get("How many times");
                if (!How_many_timesElement.isJsonArray()) {
                    String homeAddress = How_many_timesElement.getAsString();
                    entity.setHow_many_times(homeAddress);
                }
                JsonElement For_how_longElement = elmnt.getAsJsonObject().get("For how long");
                if (!For_how_longElement.isJsonArray()) {
                    entity.setFor_how_long(For_how_longElement.getAsString());
                }
                JsonElement For_how_long2Element = elmnt.getAsJsonObject().get("For how long2");
                if (!For_how_long2Element.isJsonArray()) {
                    entity.setFor_how_long2(For_how_long2Element.getAsString());
                }

                entityList.add(entity);
            }
            return entityList;
        }
    }

    /*Appointment Follow up ended*/


    /*Test result started*/
    public static List<TestResultEntity> getTestResults(String gsonstring) {
        Type type = new TypeToken<List<TestResultEntity>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new TestResultsDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class TestResultsDeserializer implements JsonDeserializer<List<TestResultEntity>> {
        @Override
        public List<TestResultEntity> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Gson gson = new GsonBuilder().create();

            List<TestResultEntity> entityList = new Vector<>();
            for (JsonElement elmnt : json.getAsJsonArray()) {
                TestResultEntity entity = gson.fromJson(elmnt, TestResultEntity.class);

                JsonElement testNameElment = elmnt.getAsJsonObject().get("Test name");
                if ((testNameElment != null) && (!testNameElment.isJsonArray())) {
                    String testName = testNameElment.getAsString();
                    entity.setTest_name(testName);
                }
                JsonElement labNameElmnt = elmnt.getAsJsonObject().get("Lab name");
                if ((labNameElmnt != null) && (!labNameElmnt.isJsonArray())) {
                    String labName = labNameElmnt.getAsString();
                    entity.setLab_name(labName);
                }
                JsonElement patientNameElement = elmnt.getAsJsonObject().get("Patient name");
                if ((patientNameElement != null) && (!patientNameElement.isJsonArray())) {
                    String patientName = patientNameElement.getAsString();
                    entity.setPatient_name(patientName);
                }

                JsonElement doctorNameElement = elmnt.getAsJsonObject().get("Doctor name");
                if ((doctorNameElement != null) && (!doctorNameElement.isJsonArray())) {
                    String doctorName = doctorNameElement.getAsString();
                    entity.setDoctor_name(doctorName);
                }


//                JsonElement For_how_longElement = elmnt.getAsJsonObject().get("For how long");
//                if (!For_how_longElement.isJsonArray()) {
//                    entity.setFor_how_long(For_how_longElement.getAsString());
//                }
//                JsonElement For_how_long2Element = elmnt.getAsJsonObject().get("For how long2");
//                if (!For_how_long2Element.isJsonArray()) {
//                    entity.setFor_how_long2(For_how_long2Element.getAsString());
//                }

                entityList.add(entity);
            }
            return entityList;
        }

    }

    /*Test result ended*/


    /*Doctor started*/
    public static List<Doctor> getDoctors(String gsonstring) {
        Type type = new TypeToken<List<Doctor>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new DoctorDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class DoctorDeserializer implements JsonDeserializer<List<Doctor>> {
        @Override
        public List<Doctor> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<Doctor> lst = new Vector<>();
            Gson gson = new GsonBuilder().create();
            for (JsonElement el : json.getAsJsonArray()) {
                Doctor pharmacy = gson.fromJson(el, Doctor.class);
                JsonElement eltmp = el.getAsJsonObject().get("Cost");
                if (!eltmp.isJsonArray()) {
                    pharmacy.setCosts(eltmp.getAsString());
                } else {
                    pharmacy.setCosts("0");
                }
                lst.add(pharmacy);
            }
            return lst;
        }
    }
    /*Doctor ended*/


    /*Pharmacy started*/
    public static List<Pharmacy> getPharmacies(String gsonstring) {
        Type type = new TypeToken<List<Pharmacy>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new PharmacyDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class PharmacyDeserializer implements JsonDeserializer<List<Pharmacy>> {
        @Override
        public List<Pharmacy> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<Pharmacy> lst = new Vector<>();
            Gson gson = new GsonBuilder().create();
            for (JsonElement el : json.getAsJsonArray()) {
                Pharmacy pharmacy = gson.fromJson(el, Pharmacy.class);
                lst.add(pharmacy);
            }
            return lst;
        }
    }
    /*Pharmacy ended*/

    /*Lab started*/
    public static List<Lab> getLabs(String gsonstring) {
        Type type = new TypeToken<List<Lab>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new LabDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }

    public static class LabDeserializer implements JsonDeserializer<List<Lab>> {
        @Override
        public List<Lab> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<Lab> lst = new Vector<>();
            Gson gson = new GsonBuilder().create();
            for (JsonElement el : json.getAsJsonArray()) {
                Lab lab = gson.fromJson(el, Lab.class);
                lst.add(lab);
            }
            return lst;
        }
    }
    /*Lab ended*/


}
