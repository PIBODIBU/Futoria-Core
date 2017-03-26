package com.futoria.core.serializer;

import com.futoria.core.model.UserData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class UserDataSerializer implements JsonSerializer<UserData> {
    private UserSerializer userSerializer;
    private UniversitySerializer universitySerializer;
    private FacultySerializer facultySerializer;
    private DepartmentSerializer departmentSerializer;
    private GroupSerializer groupSerializer;

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
    public JsonElement serialize(UserData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("bookNum", src.getBookNum());
        jsonObject.addProperty("phone", src.getPhone());
        jsonObject.add("user", userSerializer.serialize(src.getUser(), typeOfSrc, context));
        jsonObject.add("university", universitySerializer.serialize(src.getUniversity(), typeOfSrc, context));
        jsonObject.add("faculty", facultySerializer.serialize(src.getFaculty(), typeOfSrc, context));
        jsonObject.add("department", departmentSerializer.serialize(src.getDepartment(), typeOfSrc, context));
        jsonObject.add("group", groupSerializer.serialize(src.getGroup(), typeOfSrc, context));

        return jsonObject;
    }

    @Autowired
    public void setUserSerializer(UserSerializer userSerializer) {
        this.userSerializer = userSerializer;
    }

    @Autowired
    public void setUniversitySerializer(UniversitySerializer universitySerializer) {
        this.universitySerializer = universitySerializer;
    }

    @Autowired
    public void setFacultySerializer(FacultySerializer facultySerializer) {
        this.facultySerializer = facultySerializer;
    }

    @Autowired
    public void setDepartmentSerializer(DepartmentSerializer departmentSerializer) {
        this.departmentSerializer = departmentSerializer;
    }

    @Autowired
    public void setGroupSerializer(GroupSerializer groupSerializer) {
        this.groupSerializer = groupSerializer;
    }
}
