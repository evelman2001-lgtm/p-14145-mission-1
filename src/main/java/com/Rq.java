package com;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();
        String[] cmdBits = cmd.split("\\?", 2);
        String actionName = cmdBits[0];
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        for (String queryParam : queryStringBits) {
            String[] queryparamBits = queryParam.split("=");
            String Key = queryparamBits[0].trim();
            String Value = queryparamBits[1];

            if (Value.isEmpty()) {
                continue;
            }
            paramsMap.put(Key, Value);
        }
        this.actionName = actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        }else {
            return defaultValue;
        }
    }
    public String getActionName() {
        return actionName;
    }
    public int getParamAsInt(String pramName, int defaultValue) {
        String value = getParam(pramName, "");
        if (value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

