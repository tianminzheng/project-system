package com.tianyalan.common.serializer;

import java.util.Properties;

import com.google.gson.JsonObject;

public class PropertiesSerializer extends AbstractSerializer {

    private static PropertiesSerializer propertiesSerializer;

    public static synchronized PropertiesSerializer instance() {
        if (PropertiesSerializer.propertiesSerializer == null) {
            PropertiesSerializer.propertiesSerializer = new PropertiesSerializer(false);
        }

        return PropertiesSerializer.propertiesSerializer;
    }

    public PropertiesSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    public PropertiesSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public String serialize(Properties aProperties) {
        JsonObject object = new JsonObject();

        for (Object keyObj : aProperties.keySet()) {
            String key = keyObj.toString();
            String value = aProperties.getProperty(key);
            object.addProperty(key, value);
        }

        return object.getAsString();
    }
}
