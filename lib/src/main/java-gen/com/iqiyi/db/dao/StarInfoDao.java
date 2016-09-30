package com.iqiyi.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.iqiyi.db.entity.StarInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tb_star".
*/
public class StarInfoDao extends AbstractDao<StarInfo, Long> {

    public static final String TABLENAME = "tb_star";

    /**
     * Properties of entity StarInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Oid = new Property(1, String.class, "oid", false, "OID");
        public final static Property Vid = new Property(2, String.class, "vid", false, "VID");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(4, String.class, "content", false, "CONTENT");
        public final static Property CreateTime = new Property(5, String.class, "createTime", false, "CREATE_TIME");
    }


    public StarInfoDao(DaoConfig config) {
        super(config);
    }
    
    public StarInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tb_star\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"OID\" TEXT," + // 1: oid
                "\"VID\" TEXT," + // 2: vid
                "\"TITLE\" TEXT," + // 3: title
                "\"CONTENT\" TEXT," + // 4: content
                "\"CREATE_TIME\" TEXT);"); // 5: createTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tb_star\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StarInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String oid = entity.getOid();
        if (oid != null) {
            stmt.bindString(2, oid);
        }
 
        String vid = entity.getVid();
        if (vid != null) {
            stmt.bindString(3, vid);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(6, createTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StarInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String oid = entity.getOid();
        if (oid != null) {
            stmt.bindString(2, oid);
        }
 
        String vid = entity.getVid();
        if (vid != null) {
            stmt.bindString(3, vid);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(6, createTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public StarInfo readEntity(Cursor cursor, int offset) {
        StarInfo entity = new StarInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // oid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // vid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // content
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // createTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StarInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setVid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setContent(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCreateTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(StarInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(StarInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(StarInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}