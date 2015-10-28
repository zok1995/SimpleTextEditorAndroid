package com.example.oleksandr.text;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
    private final static String FILENAME = "doit.txt";
    private EditText mEditTextMain;
    private Button mButtonTextSize;
    private Spinner mSpinnerTypeText;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;
    final int MENU_SIZE_34 = 7;
    final int MENU_SIZE_38 = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextMain = (EditText) findViewById(R.id.editTextMain);

        mButtonTextSize = (Button) findViewById(R.id.buttonTextSize);

        mSpinnerTypeText = (Spinner) findViewById(R.id.spinnerTypeText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerTypeText, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerTypeText.setAdapter(adapter);

        registerForContextMenu(mButtonTextSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.buttonTextSize:
                menu.add(0,MENU_SIZE_22,0, "22");
                menu.add(0,MENU_SIZE_26, 0, "26");
                menu.add(0,MENU_SIZE_30, 0, "30");
                menu.add(0,MENU_SIZE_34, 0, "34");
                menu.add(0,MENU_SIZE_38, 0, "38");
                break;

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_SIZE_22:
                mEditTextMain.setTextSize(22);
                break;
            case MENU_SIZE_26:
                mEditTextMain.setTextSize(26);
                break;
            case MENU_SIZE_30:
                mEditTextMain.setTextSize(30);
                break;
            case MENU_SIZE_34:
                mEditTextMain.setTextSize(34);
                break;
            case MENU_SIZE_38:
                mEditTextMain.setTextSize(38);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                openFile(FILENAME);
                return true;
            case R.id.action_save:
                saveFile(FILENAME);
                return true;
            default:
                return true;
        }
    }

    private void openFile(String fileName){
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }

                inputStream.close();
                mEditTextMain.setText(stringBuilder.toString());
                Toast.makeText(getApplicationContext(),"Opened successfully", Toast.LENGTH_LONG).show();
            }
        }catch (Throwable t){
            Toast.makeText(getApplicationContext(), "Eror:  " + toString(),Toast.LENGTH_LONG).show();

        }
    }
    private void saveFile(String fileName){
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(mEditTextMain.getText().toString());
            osw.close();
            Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Mistic Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
