package com.conexa.challenge.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReplaceUrlUtil {

    private static final String API_URL = "https://www.swapi.tech/api";

    public static String LOCAL_URL = "https://conexa-production.up.railway.app";

    public static List<String> replaceBaseUrlList(List<String> urls) {
        if (Objects.isNull(urls) || urls.isEmpty()) {
            return urls;
        }

        List<String> updated = new ArrayList<>();
        for (String url : urls) {
            if (Objects.nonNull(url)) {
                String updatedURL = url.replace(API_URL, LOCAL_URL);
                updated.add(updatedURL);
            }
        }
        return updated;
    }

    public static String replaceBaseUrl(String url) {
        if (Objects.nonNull(url)) {
            return url.replace(API_URL, LOCAL_URL);
        }
        return null;
    }
}
