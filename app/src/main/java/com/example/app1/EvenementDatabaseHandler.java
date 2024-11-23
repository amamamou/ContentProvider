package com.example.app1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.util.Date;

public class EvenementDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EvenementManager";

    private static final String TABLE_EVENEMENTS = "table_Evenements";

    private static final String COLONNE_ID = "id";
    private static final String COLONNE_TITRE = "titre";
    private static final String COLONNE_LIEU = "lieu";
    private static final String COLONNE_DATE = "date";
    private static final String COLONNE_HEURE_DEBUT = "heure_debut";
    private static final String COLONNE_HEURE_FIN = "heure_fin";

    // Constructor
    public EvenementDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table SQL command
        String CREATE_EVENEMENTS_TABLE = "CREATE TABLE " + TABLE_EVENEMENTS + "("
                + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLONNE_TITRE + " TEXT, "
                + COLONNE_LIEU + " TEXT, "
                + COLONNE_DATE + " TEXT, " // Store date as TEXT in ISO 8601 format (yyyy-MM-dd)
                + COLONNE_HEURE_DEBUT + " TEXT, " // Store time as TEXT (HH:mm:ss)
                + COLONNE_HEURE_FIN + " TEXT" // Store time as TEXT (HH:mm:ss)
                + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENEMENTS);
        // Recreate the table
        onCreate(db);
    }
    public EvenementDatabaseHandler(Context context, String titre, SQLiteDatabase.CursorFactory cursorfactory, int version) {
        super(context, titre , cursorfactory, version ); }
}
