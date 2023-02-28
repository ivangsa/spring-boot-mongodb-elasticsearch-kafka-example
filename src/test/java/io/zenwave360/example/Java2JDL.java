package io.zenwave360.example;

import io.zenwave360.sdk.plugins.JavaToJDLGenerator;

public class Java2JDL {

    public static void main(String[] args) {
        String jdl = new JavaToJDLGenerator()
                .withPackageName("io.zenwave360.example.core.domain")
                .withPersistenceType(JavaToJDLGenerator.PersistenceType.MONGODB)
                .generate();
        System.out.println(jdl);
    }
}
