package com.izv.inmobiliaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Fotos extends Activity {
    Inmueble inm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        inm =(Inmueble) getIntent().getExtras().getSerializable("inmueble");
        FragmentoDetalle fd = (FragmentoDetalle)getFragmentManager().findFragmentById(R.id.fragment4);
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
        i.putExtras(bundle);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
