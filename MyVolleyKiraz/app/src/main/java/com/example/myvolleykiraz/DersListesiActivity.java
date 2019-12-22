package com.example.myvolleykiraz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DersListesiActivity extends AppCompatActivity {

    ListView listViewListe;
    String urlherders = "https://service.inciryazilim.com.tr/WebServicebesici.asmx/Dersler";
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_listesi);

        listViewListe = (ListView) findViewById(R.id.listView);

        // istek bitene kadar dilaog gösterilir. istek sonuçlanınca dialog kapatılacak
        dialog = new ProgressDialog(this);
        dialog.setMessage("Veriler Okunuyor...");
        dialog.setCancelable(false);
        dialog.show();


        /* StringRequest yerine JsonObjectRequest veya JsonArrayRequest de kullanılabilir.
         Fakat StringRequest hepsini kapsadığı için bunu kullandık. */
        // VOLLEY İSTEĞİ BURADA YAPILIYOR.
        StringRequest request = new StringRequest(Request.Method.GET, urlherders, new Response.Listener<String>() {
            @Override
            public void onResponse(String sonuc) { // sonuç başarılı dönerse onResponse çağrılır
                ArrayList<ModelListe> kullaniciList = okunanlariParseEt(sonuc); // parse metodu çağrıldı
                ArrayAdapter<ModelListe> adapter = new ArrayAdapter<>(DersListesiActivity.this, android.R.layout.simple_list_item_1, kullaniciList);
                listViewListe.setAdapter(adapter);
            }
        }, new Response.ErrorListener() { // hata oluşursa burası çağrılır
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Veriler Okunurken Hata Oluştu", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

    ArrayList<ModelListe> okunanlariParseEt(String okunanJson) {
        ArrayList<ModelListe> kullaniciList = new ArrayList<>();
        try {
            JSONArray arrayKullanici = new JSONArray(okunanJson);
            for (int i = 0; i < arrayKullanici.length(); ++i) {
                kullaniciList.add(new ModelListe(
                        Integer.valueOf(arrayKullanici.getJSONObject(i).get("Id").toString()),
                        arrayKullanici.getJSONObject(i).get("DersAdi").toString(),
                        Integer.valueOf(arrayKullanici.getJSONObject(i).get("DersKategoriId").toString()),
                        arrayKullanici.getJSONObject(i).get("Durum").toString()));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
        return kullaniciList;
    }
}
