package com.example.tests.helper;

import com.github.javafaker.Faker;

import java.util.Random;

public class DataGenerator {
    Faker faker = new Faker();

    public String getRandomText(){
        return faker.lorem().characters(1,10)
                + "." + faker.number().randomDigit(); //генерирую уникальный текст
    }

    public int getRandomUID(){
        return faker.number().numberBetween(1, 100000); //генерирую уникальный ID
    }

    public boolean getRandomBool() {
        Random rand = new Random();
        return rand.nextBoolean(); //генерирую рандомный тип
    }
}

