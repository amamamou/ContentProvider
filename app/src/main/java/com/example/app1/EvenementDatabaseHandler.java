package com.example.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EvenementDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "EvenementManager";

    private static final String TABLE_EVENEMENTS = "table_Evenements";

    private static final String COLONNE_ID = "id";
    private static final String COLONNE_TITRE = "titre";
    private static final String COLONNE_LIEU = "lieu";
    private static final String COLONNE_DATE = "date";
    private static final String COLONNE_HEURE_DEBUT = "heure_debut";
    private static final String COLONNE_HEURE_FIN = "heure_fin";

    String CREATE_EVENEMENTS_TABLE = "CREATE TABLE " + TABLE_EVENEMENTS + "("
            + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLONNE_TITRE + " TEXT not null, "
            + COLONNE_LIEU + " TEXT not null, "
            + COLONNE_DATE + " TEXT not null, "
            + COLONNE_HEURE_DEBUT + " TEXT not null, "
            + COLONNE_HEURE_FIN + " TEXT not null"
            + ")";

    public EvenementDatabaseHandler(Context context, String titre, SQLiteDatabase.CursorFactory cursorfactory, int version) {
        super(context, titre , cursorfactory, version );
    }

    public EvenementDatabaseHandler(Context context) {
        super(context, DATABASE_NAME ,null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENEMENTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENEMENTS);
        onCreate(db);
    }

    public void insertEvenement(Evenement evenement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(COLONNE_TITRE, evenement.getTitre());
        valeurs.put(COLONNE_LIEU, evenement.getLieu());
        valeurs.put(COLONNE_DATE, evenement.getDate());
        valeurs.put(COLONNE_HEURE_DEBUT, evenement.getHeure_debut());
        valeurs.put(COLONNE_HEURE_FIN, evenement.getHeure_fin());
        db.insert(TABLE_EVENEMENTS, null, valeurs);
        db.close();
    }

    public int updateEvenement (int id, Evenement evenementToUpdate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(COLONNE_TITRE, evenementToUpdate.getTitre());
        valeurs.put(COLONNE_LIEU, evenementToUpdate.getLieu());
        valeurs.put(COLONNE_DATE, evenementToUpdate.getDate());
        valeurs.put(COLONNE_HEURE_DEBUT, evenementToUpdate.getHeure_debut());
        valeurs.put(COLONNE_HEURE_FIN, evenementToUpdate.getHeure_fin());
        int res =db.update(TABLE_EVENEMENTS,valeurs, COLONNE_ID + " = " + id, null);

        db.close();

        return res;
    }

    public void removeEvenement(int EvenementID) {
        SQLiteDatabase maBD = this.getWritableDatabase();
        maBD.delete(TABLE_EVENEMENTS, COLONNE_ID + " = "+ EvenementID, null);
        maBD.close();
    }

    public Evenement getEvenement(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_EVENEMENTS,
                new String[]{COLONNE_ID, COLONNE_TITRE, COLONNE_LIEU, COLONNE_DATE, COLONNE_HEURE_DEBUT, COLONNE_HEURE_FIN},
                COLONNE_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        return cursorToEvenement(c);
    }

    public ArrayList<Evenement> getAllEvenements() {
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_EVENEMENTS,
                new String[] { COLONNE_ID, COLONNE_TITRE, COLONNE_DATE, COLONNE_HEURE_DEBUT,COLONNE_HEURE_FIN },
                null, null, null,null, null);
        return cursorToEvenements(c);
    }

    private Evenement cursorToEvenement(Cursor c) {
        if (c==null || c.getCount() == 0)
            return null;
        c.moveToFirst();
        Evenement retevenement = new Evenement();

        retevenement.setId(c.getInt(0));
        retevenement.setTitre(c.getString(1));
        retevenement.setLieu(c.getString(2));
        retevenement.setDate(c.getString(3));
        retevenement.setHeure_debut(c.getString(4));
        retevenement.setHeure_fin(c.getString(5));

        c.close();
        return retevenement;
    }


    private ArrayList<Evenement> cursorToEvenements(Cursor c) {

        if (c.getCount() == 0)
            return new ArrayList<Evenement>(0);
        ArrayList<Evenement>retEvenements = new ArrayList<Evenement>(c.getCount());
        c.moveToFirst();
        do {
            Evenement evenement = new Evenement();
            evenement.setId(c.getInt(0));
            evenement.setTitre(c.getString(1));
            evenement.setLieu(c.getString(2));
            evenement.setDate(c.getString(3));
            evenement.setHeure_debut(c.getString(4));
            evenement.setHeure_fin(c.getString(5));
            retEvenements.add(evenement);
        } while (c.moveToNext());
        c.close();
        return retEvenements;
    }

}
