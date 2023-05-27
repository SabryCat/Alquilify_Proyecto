package com.edix.Proyecto_Final_Curso.modeloDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String INPUT_DATE_FORMAT = "yyyy-MM-dd";

    public static Date parseStringToDate(String dateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(INPUT_DATE_FORMAT);
            return inputFormat.parse(dateString);
        } catch (ParseException e) {
            // Manejo de errores si la cadena no se puede convertir a Date
            e.printStackTrace();
            return null;
        }
    }
}