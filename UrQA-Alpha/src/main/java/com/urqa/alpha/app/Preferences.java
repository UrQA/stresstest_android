package com.urqa.alpha.app;

import android.content.SharedPreferences;

/**
 * @author seunoh on 2014. 05. 06..
 */
public interface Preferences {

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a String.
     * @throws ClassCastException
     */
    String getString(String key, String defValue);


    /**
     * Retrieve a long value from the preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a long.
     * @throws ClassCastException
     */
    long getLong(String key, long defValue);


    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a boolean.
     * @throws ClassCastException
     */
    boolean getBoolean(String key, boolean defValue);

    /**
     * Set a String value in the preferences editor, to be written back once
     * {@link #commit} or {@link #apply} are called.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.  Supplying {@code null}
     *              as the value is equivalent to calling {@link #remove(String)} with
     *              this key.
     * @return Returns a reference to the same Editor object, so you can
     * chain put calls together.
     */
    boolean putString(String key, String value);


    /**
     * Set a long value in the preferences editor, to be written back once
     * {@link #commit} or {@link #apply} are called.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Editor object, so you can
     * chain put calls together.
     */
    boolean putLong(String key, long value);

    /**
     * Set a boolean value in the preferences editor, to be written back
     * once {@link #commit} or {@link #apply} are called.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Editor object, so you can
     * chain put calls together.
     */
    boolean putBoolean(String key, boolean value);
}
