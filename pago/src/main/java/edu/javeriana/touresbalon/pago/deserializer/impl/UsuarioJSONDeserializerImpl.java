package edu.javeriana.touresbalon.pago.deserializer.impl;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.javeriana.touresbalon.pago.deserializer.JSONDeserializer;
import edu.javeriana.touresbalon.pago.dto.UsuarioDTO;
import edu.javeriana.touresbalon.pago.utils.Utilities;

import java.lang.reflect.Type;

public class UsuarioJSONDeserializerImpl implements JSONDeserializer {

    @Override
    public UsuarioDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject content = jsonElement.getAsJsonObject();
        return (UsuarioDTO) Utilities.getObject(content.toString(), type);
    }
}
