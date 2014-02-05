/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.logic;

/**
 *
 * @author lor1an
 */
public class Convert {

    public static int strToInt(String line) {
        int result = 0;
        for (int i = 0; i < line.length(); i++) {
            int temp = line.charAt(i) - 48;
            result += (int) (temp * Math.pow(10, line.length() - i - 1));
        }
        return result;
    }
}
