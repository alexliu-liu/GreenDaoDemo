package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyDaos {
    public static final int version=1;//数据库版本号
    public static final String entityPackageName="com.iqiyi.db.entity";//实体生存的包名
    public static final String entityClassName1="RecentlyInfo";//实体的类名
    public static final String entityClassName2="StarInfo";//实体的类名
    public static final String daoPackageName="com.iqiyi.db.dao";//指定dao层模板的包

    //自动生成模板类存放的绝对地址，也就是你的module创建的session文件夹 也就是java-gen
    public static final String autoGenerateJavaPath="D:\\Extra_Work\\useful_plugins\\greenDaoDemo\\lib\\src\\main\\java-gen";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(version, entityPackageName);
        schema.setDefaultJavaPackageDao(daoPackageName);
        Entity entity1 = schema.addEntity(entityClassName1);
        entity1.addIdProperty();
        entity1.addStringProperty("oid");
        entity1.addStringProperty("vid");
        entity1.addStringProperty("title");
        entity1.addStringProperty("content");
        entity1.addStringProperty("createTime");
        entity1.setClassNameDao(entityClassName1 + "Dao");
        entity1.setTableName("tb_recently");

        Entity entity2 = schema.addEntity(entityClassName2);
        entity2.addIdProperty();
        entity2.addStringProperty("oid");
        entity2.addStringProperty("vid");
        entity2.addStringProperty("title");
        entity2.addStringProperty("content");
        entity2.addStringProperty("createTime");
        entity2.setClassNameDao(entityClassName2 + "Dao");
        entity2.setTableName("tb_star");

        new DaoGenerator().generateAll(schema, autoGenerateJavaPath);
    }
}
