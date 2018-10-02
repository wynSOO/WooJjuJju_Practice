package app.com.woo_project.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.com.woo_project.R;
import app.com.woo_project.models.GoDataListVO;

public class GoDataViewAdapter extends RecyclerView.Adapter {

    List<GoDataListVO> goDataLists;
    Context context;

    public GoDataViewAdapter(List<GoDataListVO> goDataLists) {
        this.goDataLists = goDataLists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_go_data,parent,false);
        return new GoDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GoDataViewHolder goDataViewHolder = (GoDataViewHolder)holder;
        GoDataListVO vo = goDataLists.get(position);
        goDataViewHolder.item_txt_serv_nm.setText(vo.getServNm());
        // Html.fromHtml(Text) : 문자열에 포함된 HTML 코드를
        // 실제 기능으로 변환시키는 method
        goDataViewHolder.item_txt_serv_dgst.setText(Html.fromHtml(vo.getServDgst()));
        goDataViewHolder.item_txt_jur_mnof_nm.setText(vo.getJurMnofNm());
        goDataViewHolder.item_txt_serv_dtl_link.setText(vo.getServDtlLink());
        goDataViewHolder.item_go_link.setTag(vo.getServDtlLink());

        goDataViewHolder.item_go_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (String)v.getTag();

                // activity를 하나 생성하여
                // activity에서 url 링크를 열기
//                Intent intent = new Intent(context, WebViewActivity.class);
//                intent.putExtra("HTML",url);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goDataLists.size();
    }

    class GoDataViewHolder extends RecyclerView.ViewHolder{

        TextView item_txt_serv_nm;
        TextView item_txt_jur_mnof_nm;
        TextView item_txt_serv_dgst;
        TextView item_txt_serv_dtl_link;
        TextView item_go_link;

        public GoDataViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            item_txt_serv_nm = itemView.findViewById(R.id.item_txt_serv_nm);
            item_txt_jur_mnof_nm = itemView.findViewById(R.id.item_txt_jur_mnof_nm);
            item_txt_serv_dgst = itemView.findViewById(R.id.item_txt_serv_dgst);
            item_txt_serv_dtl_link = itemView.findViewById(R.id.item_txt_serv_dtl_link);
            item_go_link = itemView.findViewById(R.id.item_go_link);



        }
    }
}
