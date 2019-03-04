package com.example.td1.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.td1.Modele.Periodicity;
import com.example.td1.Modele.Revue;
import com.example.td1.R;

import static com.example.td1.R.layout.saisie_activity;

public class SaisieActvity extends AppCompatActivity {

    public static final int ACTION_ANNULER=1;
    public static final int ACTION_VALIDEE=2;
    private static Context mContext;
    private Spinner spinner;
    private Button btnAdd;
    private EditText editRef;
    private EditText editTitle;
    private EditText editDesc;
    private EditText editFee;
    private Revue revueMAJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(saisie_activity);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setPrompt("Periodicity");
        //ArrayAdapter<Periodicity> adapter = ArrayAdapter.createFromResource(this,android.R.layout.simple_spinner_item, Periodicity.values());
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(new ArrayAdapter<Periodicity>(this, android.R.layout.simple_spinner_item, Periodicity.values()));
        mContext = this;

        if (this.getIntent().getSerializableExtra("revue")!=null){
            this.revueMAJ = (Revue)this.getIntent().getSerializableExtra("revue");
        } else {
            this.revueMAJ = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.editRef = (EditText)this.findViewById(R.id.edit_ref);
        this.editTitle = (EditText)this.findViewById(R.id.edit_title);
        this.editDesc = (EditText)this.findViewById(R.id.edit_desc);
        this.editFee = (EditText)this.findViewById(R.id.edit_fee);
        this.btnAdd = this.findViewById(R.id.btn_add);

        if (revueMAJ != null){
            editRef.setText(revueMAJ.getReference());
            editTitle.setText(revueMAJ.getTitle());
            editDesc.setText(revueMAJ.getDescription());
            editFee.setText(String.valueOf(revueMAJ.getFee()));
            spinner.setSelection(revueMAJ.getPeriodicity().getIdPerid());
            btnAdd.setText(R.string.btn_addang);
        }
    }

    public static Context getContext() {
        return mContext;
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("revue",this.revueMAJ);
    }

    public void addActivity (View view) {
        String revue = this.editTitle.getText().toString() + " added (" + (this.editFee.getText().toString()) + " euros)";
        String erreur = "Veuillez saisir tous les champs";
        boolean allSelected;
        if (this.editTitle.getText().toString().isEmpty() || this.editRef.getText().toString().isEmpty() || this.editDesc.getText().toString().isEmpty() || this.editFee.getText().toString().isEmpty()) {
            allSelected = true;
        } else allSelected = false;

        if (allSelected)
            Toast.makeText(this, erreur, Toast.LENGTH_SHORT).show();
        else{
            Periodicity periodicity = (Periodicity) spinner.getSelectedItem();
            Revue r = new Revue();
            r.setReference(this.editRef.getText().toString());
            r.setTitle(this.editTitle.getText().toString());
            r.setDescription(this.editDesc.getText().toString());
            r.setFee(Float.parseFloat(this.editFee.getText().toString()));
            r.setPeriodicity(periodicity);
            Intent intent = new Intent();
            intent.putExtra("revue", r);
            this.setResult(ACTION_VALIDEE,intent);
            this.finish();
        }
    }

    public void onClickReturn(View view){
        this.setResult(ACTION_ANNULER);
        this.finish();
    }

    public void onBackPressed(){
        this.onClickReturn(null);
    }
}
