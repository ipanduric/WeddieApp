package com.rma.ipanduric.weddie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SLActivity extends AppCompatActivity implements View.OnClickListener {

    Button bDodajStavke;
    ListView lvSL;
    Context context;
    SLAdapter adapter;
    ArrayList<SLItem> slItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sl);
        initialize();
        SetUpAdapter();
    }

    private void initialize() {
        bDodajStavke = (Button) findViewById(R.id.bDodajStavke);
        bDodajStavke.setOnClickListener(this);


        if (adapter != null) {
            lvSL.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SLActivity.this);
                    dialogBuilder.setMessage("Želite li obrisati stavku?");
                    dialogBuilder.setCancelable(true);
                    dialogBuilder.setPositiveButton(
                            "Da",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    adapter.deleteAt(position);

                                    dialog.cancel();
                                }
                            });

                    dialogBuilder.setNegativeButton(
                            "Ne",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                    return true;
                }
            });
        }
    }


    public void SetUpAdapter () {
        lvSL = (ListView) findViewById(R.id.lvSL);
        ArrayList<SLItem> items = Database.getInstance(this).getAllSL();
        final SLAdapter adapter = new SLAdapter(this,items);
        lvSL.setAdapter(adapter);

        lvSL.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SLActivity.this);
                dialogBuilder.setTitle("Kupili ste proizvod?");
                dialogBuilder.setMessage("Želite li ga obrisati?");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton(
                        "Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Database.getInstance(getApplicationContext()).obrisiSL((SLItem) adapter.getItem(position));
                                adapter.deleteAt(position);
                                dialog.cancel();
                            }
                        });

                dialogBuilder.setNegativeButton(
                        "Ne",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }

        @Override
        public void onClick(View v) {

                    //https://www.androidcode.ninja/android-alertdialog-example/

                    // where we will store or remove selected items
                    final ArrayList<Integer> mSelectedItems = new ArrayList<Integer>();

                    AlertDialog.Builder builder = new AlertDialog.Builder(SLActivity.this);

                    // set the dialog title
                    builder.setTitle("Odaberite stavke")

                            // specify the list array, the items to be selected by default (null for none),
                            // and the listener through which to receive call backs when items are selected
                            // R.array.choices were set in the resources res/values/strings.xml
                            .setMultiChoiceItems(R.array.choices, null, new DialogInterface.OnMultiChoiceClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                    if (isChecked) {
                                        // if the user checked the item, add it to the selected items
                                        mSelectedItems.add(which);

                                    }

                                    else if (mSelectedItems.contains(which)) {
                                        // else if the item is already in the array, remove it
                                        mSelectedItems.remove(Integer.valueOf(which));
                                    }

                                    // you can also add other codes here,
                                    // for example a tool tip that gives user an idea of what he is selecting
                                    // showToast("Just an example description.");
                                }

                            })

                            // Set the action buttons
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {

                                    // user clicked OK, so save the mSelectedItems results somewhere
                                    // here we are trying to retrieve the selected items indices
                                    String selectedIndex = "";
                                    String[] androidStrings = getResources().getStringArray(R.array.choices);
                                    slItem = new ArrayList<SLItem>();

                                    for(Integer i : mSelectedItems){
                                        selectedIndex += i + ", ";
                                        String array = androidStrings[i];
                                        Log.d("DEBUG1", array);
                                        slItem.add(new SLItem(array));
                                        Log.d("DEBUG2", array);
                                        SLItem sli = new SLItem(array);
                                        Log.d("DEBUG5555", String.valueOf(sli));
                                        Log.d("DEBUG66666", sli.getSlNaziv());
                                        Database.getInstance(getApplicationContext()).dodajSL(sli);

                                    }

                                    Log.d("DEBUG3", mSelectedItems.toString());
                                    SetUpAdapter();

                                }
                            })

                            .setNegativeButton("ODUSTANI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    // removes the AlertDialog in the screen
                                }
                            })

                            .show();

            }

}


