package android.usuario.chatexemploapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.usuario.chatexemploapplication.Webservice.GenericRequest;
import android.usuario.chatexemploapplication.adapter.UserListAdapter;
import android.usuario.chatexemploapplication.model.Requisicao;
import android.usuario.chatexemploapplication.model.User;
import android.usuario.chatexemploapplication.model.UserList;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private Context ctx;
    private List<User> userList;
    private RecyclerView mRecyclerView;
    private UserListAdapter adapter;
    private ProgressDialog pDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ctx = this;
        RequestQueue queue = Volley.newRequestQueue(ctx);
        Requisicao req = new Requisicao();
        req.setMsg("GET_USER");

        id = getIntent().getExtras().getString("id");

        Log.d("ID ->", id);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Carregando os restaurantes. Por favor aguarde...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        GenericRequest<UserList> myReq = new GenericRequest<UserList>(Request.Method.POST, "http://deverp.idsgeo.com/page/get_user_json",
                UserList.class, req, createMyReqSuccessListener(), createMyReqErrorListener());
        Log.d("DEBUG", "Entrou no response");
        queue.add(myReq);
    }
    private Response.Listener<UserList> createMyReqSuccessListener() {
        return new Response.Listener<UserList>() {
            @Override
            public void onResponse(UserList response) {
                userList = response.getUserList();
                pDialog.dismiss();
                adapter = new UserListAdapter(ctx , userList);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnCardViewClickListener(new UserListAdapter.OnCardViewClickListener() {
                    @Override
                    public void onClick(User user) {
                        Intent intent = new Intent(ctx, ChatActivity.class);
                        intent.putExtra("object", (Serializable) user);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }

                });

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
