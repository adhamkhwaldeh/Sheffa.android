package com.joyBox.shefaa.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.entities.MessageResult;
import com.joyBox.shefaa.entities.RegisterNotificationResult;
import com.joyBox.shefaa.entities.TermsAndConditionsEntity;

import java.lang.reflect.Type;

/**
 * Created by Adhamkh on 2018-08-10.
 */

public class JsonParser {
    /*client parse started*/
    public static Client getClient(String gsonstring) {
        Type type = new TypeToken<Client>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, new ClientDeserializer()).create();
        return gson.fromJson(gsonstring, type);
    }


//    public static RegisterNotificationResult getRegisterNotificationResult(String gsonstring) {
//        Gson gson = new Gson();
//        return gson.fromJson(gsonstring, RegisterNotificationResult.class);
//    }


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

}
