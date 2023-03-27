package com.ostech.cbt.model;

import org.apache.commons.text.StringEscapeUtils;

public class InputUtils {
    public static String cleanseInput(String data) {
        String cleansedData = data.trim();
        cleansedData = cleansedData.replaceAll("\\\\", "");
        cleansedData = StringEscapeUtils.escapeHtml4(cleansedData);

        return cleansedData;
    }
}