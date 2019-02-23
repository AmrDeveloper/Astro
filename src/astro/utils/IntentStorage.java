package astro.utils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Objects;

public class IntentStorage {

    private static HashMap<String, Object> mIntentValues = new HashMap<>();

    public void addIntegerValue(String key, int value) {
        mIntentValues.put(key,value);
    }

    public int getIntegerValue(String key, int defaultValue) {
        Object val = mIntentValues.get(key);
        return Objects.isNull(val) ? defaultValue : Integer.parseInt(String.valueOf(val));
    }

    public void addStringValue(String key, String value) {
        mIntentValues.put(key,value);
    }

    public String getStringValue(String key, String defaultValue) {
        Object val = mIntentValues.get(key);
        return Objects.isNull(val) ? defaultValue : String.valueOf(val);
    }

    public void addObjectValue(String key, Object value) {
        mIntentValues.put(key, value);
    }

    @Nullable
    public Object getObjectValue(String key) {
        return mIntentValues.get(key);
    }

    public void resetIntentMap() {
        mIntentValues.clear();
    }
}
