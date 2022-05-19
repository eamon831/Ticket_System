package com.example.ticketsystem.Users;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {
    @TypeConverter
    public String get_string(List<String> str) {
        if (str == null)
            return null;
        StringBuilder pictures = new StringBuilder();
        for (String s : str) pictures.append(s).append(",");
        return pictures.toString();
    }

    @TypeConverter
    public ArrayList<String> set_string(String str) {
        if (str == null)
            return null;
        return new ArrayList<>(Arrays.asList(str.split(",")));
    }
}