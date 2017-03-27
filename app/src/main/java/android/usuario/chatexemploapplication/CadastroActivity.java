package android.usuario.chatexemploapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.usuario.chatexemploapplication.Webservice.GenericRequest;
import android.usuario.chatexemploapplication.adapter.MensagemAdapter;
import android.usuario.chatexemploapplication.model.MensagemList;
import android.usuario.chatexemploapplication.model.Retorno;
import android.usuario.chatexemploapplication.model.User;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class CadastroActivity extends AppCompatActivity {


    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText nome = (EditText) findViewById(R.id.nome);
        final EditText email = (EditText) findViewById(R.id.email);
        final User user = new User();
         ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(nome.getText().toString())){

                    Snackbar.make(view, "Favor preencher o nome", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(view, "Favor preencher o email", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{

                    RequestQueue queue = Volley.newRequestQueue(ctx);

                    user.setNome(nome.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setImageUrl("teste");

                    GenericRequest<Retorno> myReq = new GenericRequest<Retorno>(Request.Method.POST, "http://deverp.idsgeo.com/page/cadastro_user",
                            Retorno.class, user, createMyReqSuccessListener(), createMyReqErrorListener());
                    queue.add(myReq);

                }
            }
        });


    }

    private Response.Listener<Retorno> createMyReqSuccessListener() {
        return new Response.Listener<Retorno>() {
            @Override
            public void onResponse(Retorno ret) {
                Log.d("DEBUG", "Entro 02");
                //ret = ret.getMensagemList();
                //pDialog.dismiss();

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
            }
        };
    }

}
