package com.example.crudsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityModifyContact extends Activity implements View.OnClickListener {
    private long _id;
    private EditText namaText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText emailText;
    private Button updateBtn, deleteBtn;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Data");
        setContentView(R.layout.activity_modifycontact);
        dbManager = new DBManager(this);
        dbManager.open();

        namaText = (EditText) findViewById(R.id.txtNama);
        usernameText = (EditText) findViewById(R.id.txtUsername);
        passwordText = (EditText) findViewById(R.id.txtPassword);
        emailText = (EditText) findViewById(R.id.txtEmail);
        updateBtn = (Button) findViewById(R.id.btnSimpan);
        deleteBtn = (Button) findViewById(R.id.btnDelete);

        /* Membuat objek Intent dengan nilai yang dikirim objek Intent
        yang telah memanggil kelas ini sebelumnya */
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String email = intent.getStringExtra("email");

        _id = Long.parseLong(id);
        namaText.setText(nama);
        usernameText.setText(username);
        passwordText.setText(password);
        emailText.setText(email);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    // Pemilihan untuk tombol yang disentuh user (update/ delete)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Pilihan Update
            case R.id.btnSimpan:
                // Menyimpan nilai kelas dan nama baru ke variabel
                String nama = namaText.getText().toString();
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String email = emailText.getText().toString();
                /* Memanggil fungsi update melalui objek dbManager
                fungsi ini membawa tiga parameter yakni _id, kelas, nama */
                dbManager.update(_id, nama, username, password, email);
                /* Setelah selesai, akan memanggil fungsi returnHome()
                untuk kembali kehalaman utama */
                this.returnHome();
                break;
            // Pilihan Update
            case R.id.btnDelete:
                // Memanggil fungsi delete dengan parameter _id
                dbManager.delete(_id);
                this.returnHome();
                break;

        }
    }



    public View Kembali(View v){
        this.returnHome();
        return v;
    }

    // Fungsi untuk kembali ke halaman awal
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ActivityContactList.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
