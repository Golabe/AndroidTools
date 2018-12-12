package com.github.golabe.androidtools.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 *SharedPreferences util
 */
public class SPUtil {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public void init(Context ctx, String spName) {
        this.sharedPreferences = ctx.getSharedPreferences(spName, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    private static final class Singleton {
        private static final SPUtil INSTANCE = new SPUtil();
    }

    private SPUtil() {

    }

    public static SPUtil getInstance() {
        return Singleton.INSTANCE;
    }

    public void put(String name, Object value) {
        if (value instanceof Integer) {
            editor.putInt(name, (Integer) value);
        } else if (value instanceof String) {
            editor.putString(name, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(name, (Boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(name, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(name, (Float) value);
        } else {
            throw new IllegalArgumentException("未知类型");
        }
        editor.apply();
    }

    public Object get(String name, Object defaultValue) {
        if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(name, (Integer) defaultValue);
        } else if (defaultValue instanceof String) {
            return sharedPreferences.getString(name, (String) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(name, (Float) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(name, (Boolean) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(name, (Long) defaultValue);
        } else {
            throw new IllegalArgumentException("未知类型");
        }
    }

    public void putStringSet(String name, Set<String> value) {
        editor.putStringSet(name, value);
        editor.apply();
    }

    public Set<String> getStringSet(String name, Set<String> value) {
        return sharedPreferences.getStringSet(name, value);
    }

    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    public void clear() {
        if (editor != null) {
            editor.clear();
        }
    }

    public void remove(String name) {
        if (editor != null) {
            editor.remove(name);
        }
    }
}
