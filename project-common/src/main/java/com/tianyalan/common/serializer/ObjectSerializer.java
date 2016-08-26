
package com.tianyalan.common.serializer;

import java.lang.reflect.Type;

public class ObjectSerializer extends AbstractSerializer {

    private static ObjectSerializer eventSerializer;

    public static synchronized ObjectSerializer instance() {
        if (ObjectSerializer.eventSerializer == null) {
            ObjectSerializer.eventSerializer = new ObjectSerializer();
        }

        return ObjectSerializer.eventSerializer;
    }

    public ObjectSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    public ObjectSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public <T extends Object> T deserialize(String aSerialization, final Class<T> aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);

        return domainEvent;
    }

    public <T extends Object> T deserialize(String aSerialization, final Type aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);

        return domainEvent;
    }

    public String serialize(Object anObject) {
        String serialization = this.gson().toJson(anObject);

        return serialization;
    }

    private ObjectSerializer() {
        this(false, false);
    }
}
