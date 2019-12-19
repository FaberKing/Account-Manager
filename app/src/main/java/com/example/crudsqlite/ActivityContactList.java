package com.example.crudsqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityContactList extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAMA, DatabaseHelper.USERNAME, DatabaseHelper.PASSWORD, DatabaseHelper.EMAIL };
    final int[] to = new int[] { R.id.tvNomor, R.id.nama, R.id.username,R.id.password, R.id.email };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Memilih layout
        setContentView(R.layout.activity_contactlist);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.listView);


        /* Adapter untuk menunjuk data di database
        untuk di tampilkan dalam list siswa yang mana data tersebut
        menunjuk ke fragment dari ListView */
        adapter = new SimpleCursorAdapter(this, R.layout.activity_session, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner untuk Data Siswa yang telah ada di database
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Mengambil nilai list yang dipilih
                TextView idTextView = (TextView) view.findViewById(R.id.tvNomor);
                TextView namaTextView = (TextView) view.findViewById(R.id.nama);
                TextView usernameTextView = (TextView) view.findViewById(R.id.username);
                TextView passwordTextView = (TextView) view.findViewById(R.id.password);
                TextView emailTextView = (TextView) view.findViewById(R.id.email);

                // Menyimpan nilai list yang di pilih ke variabel
                String id = idTextView.getText().toString();
                String nama = namaTextView.getText().toString();
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String email = emailTextView.getText().toString();

                // Proses Intent untuk mengirim data ke halaman Edit
                Intent add_intent = new Intent(getApplicationContext(), ActivityModifyContact.class);
                add_intent.putExtra("id", id);
                add_intent.putExtra("nama", nama);
                add_intent.putExtra("username", username);
                add_intent.putExtra("password", password);
                add_intent.putExtra("email", email);

                startActivity(add_intent);
            }
        }); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public View Tambah(View v){
        Intent i = new Intent(getApplicationContext(),ActivityAddContact.class);
        i.putExtra(null, "Tambah");
        startActivity(i);
        finish();
        return v;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_reset) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityContactList.this);
            alertDialog.setTitle("Reset Data");
            alertDialog.setMessage("Are You Sure?");
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                            db.prosesResetData();
                            Intent intent2 = new Intent(getApplicationContext(), ActivityContactList.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);
                        }
                    });

            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
            alertDialog.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}