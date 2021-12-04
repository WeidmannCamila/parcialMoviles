package com.example.parcialmoviles.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.parcialmoviles.Model.Users;
import com.example.parcialmoviles.db.dbHelper;

import java.util.ArrayList;

public class dbUsers extends dbHelper {

    Context context;
    private ListView listUsuarios;

    public dbUsers(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long addUsers(String nombre, String apellido, String email, String profesion) {

        long id = 0;

        try {
            dbHelper dbHelper = new dbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("email", email);
            values.put("profesion", profesion);

            id = db.insert(USER_TABLE, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Users> showUsers() {

        dbHelper dbHelper = new dbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Users> listUsers = new ArrayList<>();
        Users user;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + USER_TABLE + " ORDER BY nombre ASC", null);

        if (cursorUsuarios.moveToFirst()) {
            do {
                user = new Users();
                user.setId(cursorUsuarios.getInt(0));
                user.setNombre(cursorUsuarios.getString(1));
                user.setApellido(cursorUsuarios.getString(2));
                user.setEmail(cursorUsuarios.getString(3));
                user.setProfesion(cursorUsuarios.getString(3));
                listUsers.add(user);
            } while (cursorUsuarios.moveToNext());
        }

        cursorUsuarios.close();

        return listUsers;
    }




}