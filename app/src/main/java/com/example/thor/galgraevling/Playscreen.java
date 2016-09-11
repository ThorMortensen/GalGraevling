package com.example.thor.galgraevling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import galgeleg.Galgelogik;

public class Playscreen extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Galgelogik galgelogik = new Galgelogik();
    EditText brugerInput;
    Playscreen parrent = this;
    TextView brugtBogstaver;
    TextView ordet;
    ImageView hangBilled;
    int billedID[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playscreen);
          Toast.makeText(this, galgelogik.getOrdet(), Toast.LENGTH_LONG).show();
        findViewById(R.id.buttonStartNy).setOnClickListener(this);
        brugerInput = (EditText) findViewById(R.id.editTextBrugerInput);
        brugtBogstaver = (TextView) findViewById(R.id.textViewBrugt);
        ordet = (TextView) findViewById(R.id.textViewGetteOrd);
        hangBilled = (ImageView) findViewById(R.id.imageView);
        brugerInput.setOnKeyListener(this);
        brugerInput.setOnClickListener(this);

        billedID = new int[R.drawable.class.getFields().length];
        billedID[0] = R.drawable.galge;
        billedID[1] = R.drawable.forkert1;
        billedID[2] = R.drawable.forkert2;
        billedID[3] = R.drawable.forkert3;
        billedID[4] = R.drawable.forkert4;
        billedID[5] = R.drawable.forkert5;
        billedID[6] = R.drawable.forkert6;


        brugerInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                parrent.brugerInput.setSelection(0, s.length());
            }
        });
        ordet.setAllCaps(true);
        ordet.setText(galgelogik.getSynligtOrd());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStartNy:
                galgelogik.nulstil();
                break;

            case R.id.editTextBrugerInput:
                System.out.println("click");
                brugerInput.setSelection(0, brugerInput.getText().length());
                break;


            default:
                Toast.makeText(this, "SOMETHING BAD HAPPENED!!! Defaulted in: onClick", Toast.LENGTH_SHORT).show();
        }

    }

    int foo = 0;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        foo++;
        if (event.getAction() == KeyEvent.ACTION_UP) {
            System.out.println("" + foo);
            galgelogik.gætBogstav(brugerInput.getText().toString());
            if(galgelogik.erSidsteBogstavKorrekt()){
                ordet.setText(galgelogik.getSynligtOrd());
            }else {
                brugtBogstaver.setText(galgelogik.getBrugteBogstaver().toString());
                hangBilled.setImageResource(billedID[galgelogik.getAntalForkerteBogstaver()%billedID.length]);
            }

//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            // Toast.makeText(this, brugerInput.getText(), Toast.LENGTH_SHORT).show();
        }

        return false;
    }


}
