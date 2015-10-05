package com.example.oleksandr.text;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
    private final static String FILENAME = "doit.txt";
    private EditText mEditTextMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextMain = (EditText) findViewById(R.id.editTextMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
