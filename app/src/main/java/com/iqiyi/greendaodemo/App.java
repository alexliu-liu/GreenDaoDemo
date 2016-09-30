package com.iqiyi.greendaodemo;

import android.app.Application;

import com.iqiyi.greendaodemo.entity.DaoMaster;
import com.iqiyi.greendaodemo.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by chanliu on 2016/9/29.
 * Email: alexnewtt@gmail.com
 */
public class App extends Application {
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getSession() {
        return daoSession;
    }
}
