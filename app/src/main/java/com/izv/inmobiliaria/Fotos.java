package com.izv.inmobiliaria;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class Fotos extends Activity {
    Inmueble inm;
    ArrayList<File> fotos;
    FragmentoDetalle fd;
    int posActual;
    Button btSiguiente, btAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        posActual=0;
        btSiguiente = (Button)findViewById(R.id.btSiguiente);
        btAnterior = (Button)findViewById(R.id.btAnterior);
        btSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posMax;
                posMax=fotos.size()-1;
                if(posActual+1<=posMax){
                    fd.iv.setImageURI(Uri.fromFile(fotos.get(posActual+1)));
                    posActual++;
                }else{
                    fd.iv.setImageURI(Uri.fromFile(fotos.get(0)));
                    posActual=0;
                }
            }
        });

        btAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posMax;
                posMax=fotos.size()-1;
                if(posActual-1>=0){
                    fd.iv.setImageURI(Uri.fromFile(fotos.get(posActual-1)));
                    posActual--;
                }else{
                    fd.iv.setImageURI(Uri.fromFile(fotos.get(posMax)));
                    posActual=posMax;
                }
            }
        });
        inm =(Inmueble) getIntent().getExtras().getSerializable("inmueble");
        fotos = (ArrayList) getIntent().getExtras().getSerializable("fotos");
        fd = (FragmentoDetalle)getFragmentManager().findFragmentById(R.id.fragment4);
        if(fotos.size()>0){
            fd.iv.setImageURI(Uri.fromFile(fotos.get(0)));
        }
        String s = inm.getDireccion()+", "+inm.getLocalidad();
        fd.setText(s);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fotos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("inmueble", inm);
        bundle.putSerializable("fotos",fotos);
        i.putExtras(bundle);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
