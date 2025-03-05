/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author shuej
 */
public class formatting {
        // Method to create a string of repeated characters
    public static String getDivider(char symbol, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(symbol);
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
