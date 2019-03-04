package com.example.td1.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.td1.R;
import com.example.td1.Modele.Revue;
import com.example.td1.DAO.RevueDAO;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ArrayList<Revue> listeRevues = new ArrayList<Revue>();
    private TextView welcome;
    private ListView listView;
    private String utilisateur;
    private RevueDAO rDAO = RevueDAO.getInstance(this);
    private int indiceMAJ;
    public static final int APPEL_NOUVELLE=1;
    public static final int APPEL_MAJ=2;
    public static final int ANNULER=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.listeRevues= rDAO.findAll();

        if (savedInstanceState != null) {
            this.utilisateur = savedInstanceState.getString("mail");
            this.listeRevues = (ArrayList<Revue> )savedInstanceState.getSerializable("liste");
            this.indiceMAJ = savedInstanceState.getInt("indice");
        }
        else{
            this.utilisateur = this.getIntent().getStringExtra("mail");
            Log.i("texte", this.utilisateur + "Chaine vide");
            this.indiceMAJ = -1;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.listView = this.findViewById(R.id.la_liste);
        ArrayAdapter<Revue> adaptateur = new ArrayAdapter<Revue>(this,android.R.layout.simple_list_item_1,this.listeRevues);
        this.listView.setAdapter(adaptateur);
        this.listView.setOnItemClickListener(this);
        this.listView.setOnItemLongClickListener(this);

        this.welcome = (TextView)this.findViewById(R.id.la_bienvenue);
        welcome.setText(String.format(getResources().getString(R.string.WelcomeMessage), this.utilisateur));
    }



    protected void onPause(){
        super.onPause();
        Log.i("cycle","onPause");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("mail",this.utilisateur);
        outState.putSerializable("liste", this.listeRevues);
        outState.putInt("indice", this.indiceMAJ);
    }

    protected void onStop() {
        super.onStop();
        Log.i("cycle", "onStop");
    }

    public void onClickNouvelleRevue(View view){
        Intent intent = new Intent(ListeActivity.this, SaisieActvity.class);
        startActivityForResult(intent, APPEL_NOUVELLE);
    }

    public void onClickReturn(View view){
        this.setResult(ANNULER);
        this.finish();
    }

    @Override
    public void onBackPressed(){
        this.onClickReturn(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ListeActivity.this, SaisieActvity.class);
        intent.putExtra("revue",listeRevues.get(position));
        indiceMAJ = position;
        startActivityForResult(intent, APPEL_MAJ);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data){

        if (resultcode==SaisieActvity.ACTION_ANNULER){
            return;
        }

        if (requestcode==APPEL_NOUVELLE && resultcode==SaisieActvity.ACTION_VALIDEE) {
            listeRevues.add((Revue) data.getSerializableExtra("revue"));
        } else{
            listeRevues.set(indiceMAJ,(Revue) data.getSerializableExtra("revue"));
        }
        rDAO.Ecriture(listeRevues);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Toast.makeText(ListeActivity.this, "Suppression", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder adb = new AlertDialog.Builder(ListeActivity.this);
        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete " + position);
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                listeRevues.remove(position);

            }});
        adb.show();
        return true;
    }
}
