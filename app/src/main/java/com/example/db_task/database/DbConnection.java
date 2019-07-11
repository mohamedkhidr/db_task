package com.example.db_task.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;



public class DbConnection {
    private Context context;
    private DaoSession daoSession;

    public DbConnection(Context context) {
        this.context = context;
    }
    public void connect(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "emp-db"); // create db if not exists
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession(); // get connection session

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
