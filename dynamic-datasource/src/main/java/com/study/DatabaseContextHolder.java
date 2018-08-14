package com.study;

/**
 * Created by liyang on 2018/8/10.
 */
public class DatabaseContextHolder {
    /*
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<DatabaseType> contextholder = new ThreadLocal();

    public static void setDatabaseType(DatabaseType databaseType){
        contextholder.set(databaseType);
    }

    public static DatabaseType getDatabaseType(){
        return contextholder.get();
    }

    public static void clearDatabaseType(){
        contextholder.remove();
    }

}
