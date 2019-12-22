package com.example.myvolleykiraz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import org.json.JSONObject;

import java.util.ArrayList;

public class DersActivity extends AppCompatActivity {
    ListView listViewDers;
    ProgressDialog dialog;
    String urlherders = "https://service.inciryazilim.com.tr/WebServicebesici.asmx/Dersler";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders);

        listViewDers = (ListView) findViewById(R.id.listView);

        // istek bitene kadar dialog gösterilir. istek sonuçlanınca dialog kapatılacak
        dialog = new ProgressDialog(this);
        dialog.setMessage("Veriler Okunuyor...");
        dialog.setCancelable(false);
        dialog.show();

         /* StringRequest yerine JsonObjectRequest veya JsonArrayRequest de kullanılabilir.
         Fakat StringRequest hepsini kapsadığı için bunu kullandık. */
        // VOLLEY İSTEĞİ BURADA YAPILIYOR.
        StringRequest request = new StringRequest(Request.Method.GET, urlherders, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) { // sonuç başarılı dönerse onResponse çağrılır
                okunanlariParseEt(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) { // hata olursa volleyError nesnesinde hata sebebi yazar
                Toast.makeText(getApplicationContext(), "Veriler Okunurken Hata Oluştu", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        // isteği kuyruğa ekledik. "markalar" etiketini ise isteği iptal etmek istediğimizde kullanacağız. Zorunlu değildir
        AppController.getInstance().addToRequestQueue(request);
    }

    void okunanlariParseEt(String okunanJson) { // dönen sonucu parse ediyoruz
        try {
            JSONObject jsonObj = new JSONObject(okunanJson);
            JSONArray arrayMarka = jsonObj.getJSONArray("");
            ArrayList<String> sonucList = new ArrayList<>();

            for (int i = 0; i < arrayMarka.length(); ++i) { // markaları tek tek listeye ekledik
                sonucList.add(arrayMarka.getString(i));
            }

            // oluşturduğumuz sonucList listesini, listview'ın adaptörüne verdik ve listede gösterilmesini sağladık
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sonucList);
            listViewDers.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }
}
