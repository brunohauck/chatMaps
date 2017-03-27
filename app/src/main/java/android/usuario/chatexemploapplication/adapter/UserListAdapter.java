package android.usuario.chatexemploapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.usuario.chatexemploapplication.R;
import android.usuario.chatexemploapplication.model.User;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 18/03/2017.
 */


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> userList;

    private Context mContext;

    private OnCardViewClickListener onCardViewClickListener;

    public OnCardViewClickListener getOnCardViewClickListener() {
        return onCardViewClickListener;
    }

    public void setOnCardViewClickListener(OnCardViewClickListener onCardViewClickListener) {
        this.onCardViewClickListener = onCardViewClickListener;
    }

    public UserListAdapter(Context context, List<User> userList) {
        this.userList = userList;
        this.mContext = context;
        //this.fmAdapter = fm;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_chat, null);
        ViewHolder mh = new ViewHolder(v);

        return mh;
    }
    // Inserir isso depois no
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView foto;
        TextView nome;
        TextView email;
        public ViewHolder(View itemView){
            super(itemView);
            foto = (ImageView) itemView.findViewById(R.id.foto);
            nome = (TextView) itemView.findViewById(R.id.nome);
            email = (TextView) itemView.findViewById(R.id.email);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // CallIntent(getAdapterPosition());
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder feedListRowHolder, int i) {

        final User user = userList.get(i);

        Picasso.with(mContext).load(user.getImageUrl())
                .error(R.drawable.placeholder)
                .resize(250, 250)
                .placeholder(R.drawable.placeholder)
                .into(feedListRowHolder.foto);

        feedListRowHolder.nome.setText(Html.fromHtml(user.getNome()));

        String info = "<br />Email -> "+user.getEmail();

        //String info = enderecoPrint + "<br />Tipo: " + restaurant.getTipo().toString() + "<br />Telefone: " + restaurant.getTelefone().toString() + "<br />Celular: " + restaurant.getCelular().toString();


        feedListRowHolder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(onCardViewClickListener!=null){
                    onCardViewClickListener.onClick(user);
                }

            }
        });

        feedListRowHolder.email.setText(Html.fromHtml(info));
    }

    @Override
    public int getItemCount() {
        return (null != userList ? userList.size() : 0);
    }

    public interface OnCardViewClickListener {

        void onClick(
                User user
        );

    }
}