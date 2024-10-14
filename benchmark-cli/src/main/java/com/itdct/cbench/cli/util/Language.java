package com.itdct.cbench.cli.util;

import com.itdct.cbench.cli.language.Chinese;
import com.itdct.cbench.cli.language.English;
import com.itdct.cbench.cli.language.LangType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhouwx
 * @date 2024/10/14 21:48:14
 * @version 1.0
 * @description
 */
public class Language {
    public static int languageType = LangType.ENGLISH;
    private static HashMap<Integer, Map> typeMap = new HashMap<>();

    static {
        typeMap.put(LangType.ENGLISH, English.map);
        typeMap.put(LangType.CHINESE, Chinese.map);
    }

    public static String get(String key) {
        Map<String, String> languageMap = typeMap.get(languageType);
        if (languageMap == null) {
            return "Missing language";
        }
        String result = languageMap.get(key);
        if (result == null || result.isEmpty()) {
            result = "Missing string";
        }
        return result;
    }

}
