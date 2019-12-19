package com.example.crudsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddContact extends Activity implements View.OnClickListener {
	private Button addTodoBtn;
	private EditText namaText;
	private EditText usernameText;
	private EditText passwordText;
	private EditText emailText;
	private DBManager dbManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Tambah Data");
		setContentView(R.layout.activity_addcontact);
		namaText = (EditText) findViewById(R.id.txtNama);
		usernameText = (EditText) findViewById(R.id.txtUsername);
		passwordText = findViewById(R.id.txtPassword);
		emailText = (EditText) findViewById(R.id.txtEmail);
		addTodoBtn = (Button) findViewById(R.id.btnSimpan);
		// Membuat objek dari kelas DBManager
		dbManager = new DBManager(this);
		dbManager.open();
		addTodoBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSimpan:
				// Mengambil data inputan user
				final String nama = namaText.getText().toString();
				final String username = usernameText.getText().toString();
				final String password = passwordText.getText().toString();
				final String email = emailText.getText().toString();

        /* Memanggil fungsi insert melalui objek dbManager dengan parameter
                nilaikelas dan nama */
				dbManager.insert(nama, username, password, email);
				// Memindahkan halaman kembali ke daftar siswa
				Intent main = new Intent(ActivityAddContact.this, ActivityContactList.class)
						.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(main);
				break;

		} }

	public View Kembali(View v){
		Intent i = new Intent(this, ActivityContactList.class);
		startActivity(i);
		finish();
		return v;
	}
}
