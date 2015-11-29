package sk.hudak.pricecomparator.server.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 2. 5. 2015.
 */
public class JefQClassGenerator {

    public static void generateQClassFor(Class<?> clazz) {

        String className = generateNameOfClass(clazz);

        // generovanie
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(clazz.getPackage().getName()).append(";").append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("public class " + className + " {").append(System.lineSeparator());
        sb.append(System.lineSeparator());


        List<String> constants = new ArrayList<>();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {

            String name = field.getName();

            constants.add("public static final String " + name.toUpperCase() + " = \"" + name + "\";");


            System.out.println(name);
            //if(field.getDeclaringClass().equals())

        }


        System.out.println("");
    }

    private static boolean isJavaCommonType(Field field) {
        //TODO
        return false;
    }

    private static String generateNameOfClass(Class<?> clazz) {
        return "Q" + clazz.getName();
    }


}
