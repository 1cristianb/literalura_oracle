package com.alura.literalura.model;

public enum Lenguaje {
    INGLES ("en","Ingles"),
    ESPAÑOL("es","Español"),
    PORTUGUES("pt","Portugues"),
    ALEMAN("de", "Aleman"),
    ITALIANO("it", "Italiano"),
    FRANCES("fr", "Frances"),
    LATIN("la", "Latin");

    private String langLiter;
    private String languageLiter;

    Lenguaje(String langLiter, String languageLiter) {
        this.langLiter = langLiter;
        this.languageLiter = languageLiter;
    }
    public static Lenguaje fromString(String text) {
        for (Lenguaje language: Lenguaje.values()) {
            if (language.langLiter.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }
    public static Lenguaje fromTotalString(String text) {
        for (Lenguaje language: Lenguaje.values()) {
            if (language.languageLiter.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }
}