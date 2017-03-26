package com.futoria.core.serializer;

import com.futoria.core.model.university.Faculty;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class FacultySerializer implements JsonSerializer<Faculty> {
    private UniversitySerializer universitySerializer;

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
    public JsonElement serialize(Faculty src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("shortName", src.getShortName());
        jsonObject.addProperty("longName", src.getLongName());

        try {
            jsonObject.add("university", universitySerializer.serialize(src.getUniversity(), typeOfSrc, context));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jsonObject.add("university", new JsonObject());
        }

        return jsonObject;
    }

    @Autowired
    public void setUniversitySerializer(UniversitySerializer universitySerializer) {
        this.universitySerializer = universitySerializer;
    }
}
