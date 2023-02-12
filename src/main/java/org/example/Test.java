package org.example;

import java.lang.reflect.Method;
import java.util.Objects;

public class Test {

    public static void checkIfSerializable(Object object) throws Exception {
        if (Objects.isNull(object)) {
            throw new Exception("The object to serialize is null");
        }

        Class<?> class_ = object.getClass();
        if (!class_.isAnnotationPresent(JsonSerializable.class)) {
            throw new Exception("The class "
                    + class_.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
        else{
            System.out.println("this class is JsonSerializable");
        }
    }

    public static void initializeObject(Object object) throws Exception {
        Class<?> class_ = object.getClass();
        for (Method method : class_.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Person person=new Person();
        person.setFirstName("Ali");
        person.setLastName("Aloui");
        checkIfSerializable(person);
        initializeObject(person);
        System.out.println(person);


    }
}
