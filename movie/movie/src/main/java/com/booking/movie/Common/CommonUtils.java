package com.booking.movie.Common;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    private CommonUtils() {

    }

    public static Map<String, Object> putError(String item, String errorCode) {
        Map<String, Object> error = new HashMap<>();
        error.put(Constants.ERROR_ITEM, item);
        error.put(Constants.ERROR_CODE, errorCode);
        return error;
    }

    public static Map<String, Object> putError(String item, String errorCode, Integer rowId) {
        Map<String, Object> error = new HashMap<>();
        error.put(Constants.ERROR_ITEM, item);
        error.put(Constants.ERROR_CODE, errorCode);
        error.put(Constants.ROW_ID, rowId);
        return error;
    }

    public static Double getValueDouble(Double source) {
        if (source == null) {
            return 0d;
        }
        return Math.round(source * 100) / 100d;
    }

    public static Double getValueDoubleMigration(Double source) {
        if (source == null) {
            return 0d;
        }
        return Math.round(source * 100000) / 100000d;
    }
}
