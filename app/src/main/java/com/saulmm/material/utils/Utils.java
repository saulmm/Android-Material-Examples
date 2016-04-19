package com.saulmm.material.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> createFakeData (int size) {

        ArrayList<String> fakeDataList = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            fakeDataList.add(String.format("Fake element #%d", i));

        return fakeDataList;
    }
}
