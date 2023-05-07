package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {

    @Test
    @DisplayName("Simple test")
    void test01() {
        testOne("Hello, World!", 3, "Khoor, Zruog!");
    }

    @Test
    @DisplayName("Wrap around")
    void test02() {
        testOne("Hello, World!", 26, "Hello, World!");
    }

    @Test
    @DisplayName("No change")
    void test03() {
        testOne("Hello, World!", 0, "Hello, World!");
    }

    @Test
    @DisplayName("Upper alphabet")
    void test04() {
        testOne("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 5, "FGHIJKLMNOPQRSTUVWXYZABCDE");
    }

    @Test
    @DisplayName("Lower alphabet")
    void test05() {
        testOne("abcdefghijklmnopqrstuvwxyz", 13, "nopqrstuvwxyzabcdefghijklm");
    }

    @Test
    @DisplayName("Special characters")
    void test06() {
        testOne("12345!@#$%", 7, "12345!@#$%");
    }

    @Test
    @DisplayName("Mixed case")
    void test07() {
        testOne("AaBbCcDdEe", 1, "BbCcDdEeFf");
    }

    @Test
    @DisplayName("Empty string")
    void test08() {
        testOne("", 3, "");
    }

    @Test
    @DisplayName("Negative key")
    void test09() {
        testOne("Khoor, Zruog!", -3, "Hello, World!");
    }

    @Test
    @DisplayName("Negative key wrap around")
    void test10() {
        testOne("Khoor, Zruog!", -29, "Hello, World!");
    }

    void testOne(String text, int key, String expected) {
        var enc = CaesarCipher.encrypt(text, key);
        var dec = CaesarCipher.decrypt(enc, key);
        assertEquals(expected, enc);
        assertEquals(text, dec);
    }
}