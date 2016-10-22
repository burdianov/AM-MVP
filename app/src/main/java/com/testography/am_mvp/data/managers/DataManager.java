package com.testography.am_mvp.data.managers;

import android.content.Context;

public class DataManager {
    private static DataManager ourInstance;
    private PreferencesManager mPreferencesManager;
    private Context mAppContext;

    private DataManager() {
        mPreferencesManager = new PreferencesManager();
        mAppContext = AmMvpApplication.getAppContext();
    }

    public static DataManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    public Context getAppContext() {
        return mAppContext;
    }
}
