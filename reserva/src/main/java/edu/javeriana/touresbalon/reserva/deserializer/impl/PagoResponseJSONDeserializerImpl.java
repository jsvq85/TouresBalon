package edu.javeriana.touresbalon.reserva.deserializer.impl;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.javeriana.touresbalon.reserva.dto.PagoResponse;
import edu.javeriana.touresbalon.reserva.utils.Utilities;
import edu.javeriana.touresbalon.reserva.deserializer.JSONDeserializer;

import java.lang.reflect.Type;

public class PagoResponseJSONDeserializerImpl implements JSONDeserializer {

    @Override
    public PagoResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject content = jsonElement.getAsJsonObject();
        return (PagoResponse) Utilities.getObject(content.toString(), type);
    }
}
