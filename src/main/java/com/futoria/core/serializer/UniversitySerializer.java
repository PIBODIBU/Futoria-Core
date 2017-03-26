package com.futoria.core.serializer;

import com.futoria.core.model.university.Faculty;
import com.futoria.core.model.university.University;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UniversitySerializer implements JsonSerializer<University> {
    private static FacultySerializer facultySerializer;

    static {
        facultySerializer = new FacultySerializer();
    }

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
    public JsonElement serialize(University src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        JsonArray faculties = new JsonArray();

        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("shortName", src.getShortName());
        jsonObject.addProperty("longName", src.getLongName());

        try {
            for (Faculty faculty : src.getFaculties()) {
                faculties.add(facultySerializer.serialize(faculty, typeOfSrc, context));
            }

            jsonObject.add("faculties", faculties);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }
}
