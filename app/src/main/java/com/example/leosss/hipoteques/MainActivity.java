package com.example.leosss.hipoteques;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends ActionBarActivity {

    private TextView Total;
    private TextView Mes;



    private static double Redondear(double a){
        int cifras=(int) Math.pow(10,2);
        return Math.rint(a*cifras)/cifras;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Llamando a control
        Button calc = (Button) findViewById(R.id.button);
        final EditText preu = (EditText) findViewById(R.id.editText);
        final EditText estalvis = (EditText) findViewById(R.id.editText2);
        final EditText termini = (EditText) findViewById(R.id.editText3);
        final EditText euribor = (EditText) findViewById(R.id.editText4);
        final EditText diferencial = (EditText) findViewById(R.id.editText5);

        Mes = (TextView) findViewById(R.id.textView8);
        Total = (TextView) findViewById(R.id.textView9);


        //Configuración de botón Calcular
        calc.setOnClickListener (new View.OnClickListener(){
            public void onClick(View V) {

                double capital = Double.parseDouble(preu.getText().toString()) - Double.parseDouble(estalvis.getText().toString());
                double terminiMes = Double.parseDouble(termini.getText().toString())*12;
                double interes = Double.parseDouble(euribor.getText().toString())+Double.parseDouble(diferencial.getText().toString());

                    //calculos
                double cuota = (float) (capital*(interes/12)) / (100*(1-(Math.pow(1+(interes/12/100),-terminiMes))));

                String r = String.valueOf(Redondear(cuota));
                String y = String.valueOf(Redondear(cuota)*terminiMes);
                Mes.setText(r);
                Total.setText(y);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
