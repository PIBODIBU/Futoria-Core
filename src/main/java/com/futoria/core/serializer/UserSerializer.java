package com.futoria.core.serializer;

import com.google.gson.*;
import com.futoria.core.model.Role;
import com.futoria.core.model.User;

import java.lang.reflect.Type;

public class UserSerializer implements JsonSerializer<User> {
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
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        RoleSerializer roleSerializer = new RoleSerializer();
        JsonObject object = new JsonObject();
        JsonArray roles = new JsonArray();

        object.addProperty("id", src.getId());
        object.addProperty("firstName", src.getFirstName());
        object.addProperty("lastName", src.getLastName());
        object.addProperty("middleName", src.getMiddleName());
        object.addProperty("email", src.getEmail());
        object.addProperty("username", src.getUsername());
        object.addProperty("password", src.getPassword());
        object.addProperty("isEnabled", src.getEnabled());

        try {
            for (Role role : src.getRoles()) {
                roles.add(roleSerializer.serialize(role, typeOfSrc, context));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            object.add("roles", roles);
        }

        return object;
    }
}
