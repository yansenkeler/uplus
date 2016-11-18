package com.ejb.uplus.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ejb.uplus.greendao.entity.Bus;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BUS".
*/
public class BusDao extends AbstractDao<Bus, Long> {

    public static final String TABLENAME = "BUS";

    /**
     * Properties of entity Bus.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserId = new Property(0, Long.class, "userId", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
    }


    public BusDao(DaoConfig config) {
        super(config);
    }
    
    public BusDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BUS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: userId
                "\"NAME\" TEXT);"); // 1: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BUS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Bus entity) {
        stmt.clearBindings();
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(1, userId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Bus entity) {
        stmt.clearBindings();
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(1, userId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Bus readEntity(Cursor cursor, int offset) {
        Bus entity = new Bus( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // userId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Bus entity, int offset) {
        entity.setUserId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Bus entity, long rowId) {
        entity.setUserId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Bus entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Bus entity) {
        return entity.getUserId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
