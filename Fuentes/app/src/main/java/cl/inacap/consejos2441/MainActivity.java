package cl.inacap.consejos2441;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.consejos2441.adapters.ConsejosAdapter;
import cl.inacap.consejos2441.dto.Consejo;

public class MainActivity extends AppCompatActivity {


    private List<Consejo> consejos = new ArrayList<>();
    private ListView consejosList;
    private ConsejosAdapter consejosAdapter;
    private RequestQueue queue;
    private Button boton;
    private Spinner spinner;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.toolbar = findViewById(R.id.toolabar);
        setContentView(R.layout.activity_main);
        this.boton = findViewById(R.id.boton);
        this.spinner = findViewById(R.id.spinner);
        queue = Volley.newRequestQueue(getApplicationContext());
        this.consejosList = findViewById(R.id.list_view);
        this.consejosAdapter = new ConsejosAdapter(this,R.layout.list_consejo,this.consejos);
        this.consejosList.setAdapter(this.consejosAdapter);
        this.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String citas = (String) spinner.getSelectedItem();
                String url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=" + citas;
                StringRequest JsonR = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    consejos.clear();
                                    Consejo[] arreglo = new Gson().fromJson(response,Consejo[].class);
                                    consejos.addAll(Arrays.asList(arreglo));
                                }catch(Exception ex){
                                    consejos.clear();
                                    Log.e("consejos","Error de peticion onResponse");
                                }finally {
                                    consejosAdapter.notifyDataSetChanged();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                consejos.clear();
                                Log.e("consejos","Error de peticion onErrorResponse");
                                consejosAdapter.notifyDataSetChanged();
                            }
                        });
                queue.add(JsonR);
            }
        });
        final String[] num = {"1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,num);
        spinner.setAdapter(adapter);

    }
}