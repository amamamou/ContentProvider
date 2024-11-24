package com.example.app1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class EvenementContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.app1";
    private static final String PATH_EVENEMENTS = "events";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH_EVENEMENTS);

    private EvenementDatabaseHandler db;

    @Override
    public boolean onCreate() {
        db = new EvenementDatabaseHandler(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = db.getReadableDatabase();
        return database.query("table_Evenements", projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = db.getWritableDatabase();
        long id = database.insert("table_Evenements", null, values);
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = db.getWritableDatabase();
        return database.update("table_Evenements", values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = db.getWritableDatabase();
        return database.delete("table_Evenements", selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.com.example.app1.events";
    }
}