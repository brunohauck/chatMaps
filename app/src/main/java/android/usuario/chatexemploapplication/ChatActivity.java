package android.usuario.chatexemploapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.usuario.chatexemploapplication.Webservice.GenericRequest;
import android.usuario.chatexemploapplication.adapter.MensagemAdapter;
import android.usuario.chatexemploapplication.adapter.UserListAdapter;
import android.usuario.chatexemploapplication.model.Mensagem;
import android.usuario.chatexemploapplication.model.MensagemList;
import android.usuario.chatexemploapplication.model.Requisicao;
import android.usuario.chatexemploapplication.model.User;
import android.usuario.chatexemploapplication.model.UserList;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    private Context ctx;
    private List<Mensagem> mensagemList;
    private RecyclerView mRecyclerView;
    private MensagemAdapter adapter;
    private ProgressDialog pDialog;
    private double lat;
    private double lng;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        final EditText editTextMsg = (EditText) findViewById(R.id.editTextMsg);
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Carregando os restaurantes. Por favor aguarde...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        id = getIntent().getExtras().getString("id");
        getLocationbyLatLng();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pDialog.show();
                RequestQueue queue = Volley.newRequestQueue(ctx);
                Requisicao req = new Requisicao();
                req.setMsg("GET_USER");

                Intent i = getIntent();
                User user = new User();
                user = ((User) i.getSerializableExtra("object"));

                Mensagem men = new Mensagem();
                men.setMsgEnviada(editTextMsg.getText().toString());
                men.setUserIdDestino(user.getId().toString());
                id = getIntent().getExtras().getString("id");
                men.setUserIdOrigem(id);
                men.setLatitude(String.valueOf(lat));
                men.setLongitude(String.valueOf(lng));

                GenericRequest<MensagemList> myReq = new GenericRequest<MensagemList>(Request.Method.POST, "http://deverp.idsgeo.com/page/get_mensagem_json",
                        MensagemList.class, men, createMyReqSuccessListener(), createMyReqErrorListener());
                Log.d("DEBUG", "Entrou no response");
                queue.add(myReq);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ctx, MapsActivity.class);
            intent.putExtra("object", (Serializable) mensagemList);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Response.Listener<MensagemList> createMyReqSuccessListener() {
        return new Response.Listener<MensagemList>() {
            @Override
            public void onResponse(MensagemList response) {
                Log.d("DEBUG", "Entro 02");
                mensagemList = response.getMensagemList();
                pDialog.dismiss();
                adapter = new MensagemAdapter(ctx, mensagemList);
                mRecyclerView.setAdapter(adapter);
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do whatever you want to do with error.getMessage();

                Log.d("DEBUG", "Entrou no ERRO");
                Toast.makeText(ctx, "ERRO", Toast.LENGTH_LONG).show();
                Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show();
            }
        };
    }

    public void getLocationbyLatLng() {
        double[] d = fetchCurrentLocation();
        lat = d[0];
        lng = d[1];
        if (d[0] == 0.0) {
            Toast.makeText(ctx, "Não foi possível obter sua localização", Toast.LENGTH_LONG).show();

            lat = -23.5711068;
            lng = -46.6521656;

        } else {
            //latitude.setText(String.valueOf(lat));
            //longitude.setText(String.valueOf(lng));
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(lat, lng, 1);
//                address = addresses.get(0).getAddressLine(0);
//                city = addresses.get(0).getLocality();
//                state = addresses.get(0).getAdminArea();
//                country = addresses.get(0).getCountryName();
//                postalCode = addresses.get(0).getPostalCode();
//                knownName = addresses.get(0).getFeatureName();
//                String enderecoConcatenado = address + ", " + city + "," + state + "," + country;
//                endereco.setText(enderecoConcatenado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private double[] fetchCurrentLocation() {
        double[] d = getlocation();
        return d != null && d.length > 0 && d[0] != 0.0 ? d : new double[]{lat, lng};
    }


    public double[] getlocation() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location l = null;
        for (int i = 0; i < providers.size(); i++) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ctx, "Você deve abiliar a permissão de localização!", Toast.LENGTH_LONG).show();
            }
            l = lm.getLastKnownLocation(providers.get(i));

            if (l != null)
                break;
        }
        double[] gps = new double[2];
        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
        return gps;

    }




}
