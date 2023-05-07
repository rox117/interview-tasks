package org.example;

public class CaesarCipher {
    public static String encrypt(String text, int key) {
        StringBuilder ciphertext = new StringBuilder();
        int shiftedValue;
        for (char chartoShift : text.toCharArray()) {
            if (Character.isLetter(chartoShift)) {
                int startingLetter = findStartingPosition(chartoShift);
                shiftedValue = calcCharShift(chartoShift, startingLetter, key);
                chartoShift = (char) (calcEncryptedChar(shiftedValue, 26, startingLetter));
            }
            ciphertext.append(chartoShift);
        }
        return ciphertext.toString();
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, -key);
    }

    public static int calcEncryptedChar(int dividend, int divisor, int initialPosition){
        return ((dividend + divisor) % divisor) + initialPosition;
    }

    public static int findStartingPosition(char ch){
        return Character.isUpperCase(ch)? 'A' : 'a';
    }

    public static int calcCharShift(char characterToShift, int startingPosition, int shiftAmount){
        return characterToShift - startingPosition + shiftAmount;
    }
}