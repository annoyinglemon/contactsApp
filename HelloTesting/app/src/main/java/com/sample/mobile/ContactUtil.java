package com.sample.mobile;



import com.sample.mobile.resultObjects.Name;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContactUtil {

    public static String formatName(Name name) {
        return name.title.concat(" ").concat(name.firstName).concat(" ").concat(name.lastName);
    }

    public static String formatBirthDate(String birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.CANADA);
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, YYYY", Locale.CANADA);
        try {
            Date date = simpleDateFormat.parse(birthDate);
            return newFormat.format(date);
        } catch (ParseException e) {
            return birthDate;
        }
    }

    public static String capitalizeWords(String originalString) {
        String[] words = originalString.split("\\s+");
        String newString = "";
        for (String word : words) {
            word = capitalizeWord(word);
            newString = newString.concat(word).concat(" ");
        }
        return newString;
    }

    public static String capitalizeWord(String originalString) {
        if (originalString == null || originalString.length() == 0) {
            return originalString;
        }
        return originalString.substring(0, 1).toUpperCase() + originalString.substring(1);
    }




}
