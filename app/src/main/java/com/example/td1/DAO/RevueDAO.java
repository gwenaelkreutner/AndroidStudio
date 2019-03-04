package com.example.td1.DAO;

import android.content.Context;

import com.example.td1.Modele.Revue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RevueDAO {

    private Context context;
    private static RevueDAO instance;

    private RevueDAO (Context context){
        this.context=context;
    }

    public static RevueDAO getInstance (Context context){
        if (instance==null){
            instance = new RevueDAO(context);
        }
        return instance;
    }

    private static final String NOM_FICHIER = "name";

    public void Ecriture (ArrayList<Revue> revues){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(this.context.openFileOutput(NOM_FICHIER, context.MODE_PRIVATE));
            oos.writeObject(revues);
            oos.close();
        }
        catch (IOException ioe){

        }
    }

    public ArrayList<Revue> Lecture() {
        ArrayList<Revue> resultat = new ArrayList<>();
        try{
            ObjectInputStream ois = new ObjectInputStream(this.context.openFileInput(NOM_FICHIER));
            resultat = (ArrayList<Revue>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultat;
    }

    public ArrayList<Revue> findAll(){
        return Lecture();
    }

}
