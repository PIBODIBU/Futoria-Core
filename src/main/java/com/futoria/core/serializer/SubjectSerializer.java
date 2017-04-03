package com.futoria.core.serializer;

import com.futoria.core.model.university.Subject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SubjectSerializer implements JsonSerializer<Subject> {
    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(Subject src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("uuid", src.getUuid());
        jsonObject.addProperty("shortName", src.getShortName());
        jsonObject.addProperty("longName", src.getLongName());
        jsonObject.add("department", new DepartmentSerializer().serialize(src.getDepartment(), typeOfSrc, context));

        return jsonObject;
    }
}
