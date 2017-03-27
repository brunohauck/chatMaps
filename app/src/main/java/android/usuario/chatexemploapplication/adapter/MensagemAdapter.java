package android.usuario.chatexemploapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.usuario.chatexemploapplication.R;
import android.usuario.chatexemploapplication.model.Mensagem;
import android.usuario.chatexemploapplication.model.User;
import android.util.Log;
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


public class MensagemAdapter extends RecyclerView.Adapter<MensagemAdapter.ViewHolder> {

    private List<Mensagem> mensagemList;

    private Context mContext;

    private OnCardViewClickListener onCardViewClickListener;

    public OnCardViewClickListener getOnCardViewClickListener() {
        return onCardViewClickListener;
    }

    public void setOnCardViewClickListener(OnCardViewClickListener onCardViewClickListener) {
        this.onCardViewClickListener = onCardViewClickListener;
    }

    public MensagemAdapter(Context context, List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
        this.mContext = context;
        //this.fmAdapter = fm;
    }

    @Override
    public MensagemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_chat_msg, null);
        ViewHolder mh = new ViewHolder(v);

        return mh;
    }
    // Inserir isso depois no
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        //ImageView foto;
        TextView mensagem;
        //TextView email;
        public ViewHolder(View itemView){
            super(itemView);
            //foto = (ImageView) itemView.findViewById(R.id.foto);
            //nome = (TextView) itemView.findViewById(R.id.nome);
            mensagem = (TextView) itemView.findViewById(R.id.mensagem);
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
        final Mensagem msg =  mensagemList.get(i);
        Log.d("MSG",msg.getMsgEnviada());
        feedListRowHolder.mensagem.setText(msg.getMsgEnviada().toString());
    }

    @Override
    public int getItemCount() {
        return (null !=  mensagemList ?  mensagemList.size() : 0);
    }


    public interface OnCardViewClickListener {

        void onClick(
                User user
        );

    }
}