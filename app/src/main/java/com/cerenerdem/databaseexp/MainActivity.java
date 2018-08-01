package com.cerenerdem.databaseexp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtv_name;
    TextView txtv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtv_name = (TextView) findViewById(R.id.txtv_name);
        txtv_age = (TextView) findViewById(R.id.txtv_age);


        try{
            //Database'i yarattık.
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);


            //Eğer bir tablo yoksa benim için oluştur. Tablo oluşturduk.
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT(2))");


            //Musicians database 'imin içine, musicians tabloma bu veri kaydolacak. Veri kaydettik.
            //myDatabase.execSQL("INSERT INTO musicians(name, age) VALUES ('James', 50)");


            //Veri okuma. Tüm tabloyu oku.
            Cursor cursor = myDatabase.rawQuery("SELECT * FROM musicians",null);


            //Tablo içerisinde oluşturduğum kolonlara ait indexleri oku diyorum.
            //Bu işlem sayesinde x indexli kolonun, y indexli satırında yazan veriyi bana ver diyebileceğim.
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");


            //Select sonrası gelen tüm verilerin en başına git.
            cursor.moveToFirst();


            //Eğer cursor içerisinde data varsa yani tablomda veri varsa
            while (cursor != null ){

                //Ekrana cursorun gezdiği ad ve yaş bilgisini yazdırıyoruz.
                txtv_name.setText(cursor.getString(nameIx));
                txtv_age.setText(cursor.getString(ageIx));

                //bir sonraki satıra geç.
                cursor.moveToNext();
            }


        } catch (Exception e)
        {
            e.toString();

        }

    }

}
