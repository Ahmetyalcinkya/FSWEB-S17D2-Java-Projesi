package com.workintech.s17d2.validation;

import com.workintech.s17d2.model.Developer;

import java.util.Map;

public class DeveloperValidation {

    public static boolean isExist(Map<Integer, Developer> developers, int id) {
        return developers.containsKey(id);
    }
}
