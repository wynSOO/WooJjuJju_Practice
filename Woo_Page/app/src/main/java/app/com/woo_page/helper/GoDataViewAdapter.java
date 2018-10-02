package app.com.woo_page.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.com.woo_page.R;
import app.com.woo_page.database.goDataVO;

public class GoDataViewAdapter extends RecyclerView.Adapter {

    List<goDataVO> goDataList;

    public GoDataViewAdapter(List<goDataVO> goDataList) {
        this.goDataList = goDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.go_item_view,viewGroup,false);
        return new GoDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        GoDataViewHolder goDataViewHolder = (GoDataViewHolder)viewHolder;
        goDataViewHolder.servNm.setText(goDataList.get(i).servNm);
        goDataViewHolder.jurMnofNm.setText(goDataList.get(i).jurMnofNm);
        goDataViewHolder.jurOrgNm.setText(goDataList.get(i).jurOrgNm);


    }

    @Override
    public int getItemCount() {
        return goDataList.size();
    }

    class GoDataViewHolder extends RecyclerView.ViewHolder {

        TextView servNm;
        TextView jurMnofNm;
        TextView jurOrgNm;

        public GoDataViewHolder(@NonNull View itemView) {
            super(itemView);
            servNm = itemView.findViewById(R.id.go_item_serv_nm);
            jurMnofNm = itemView.findViewById(R.id.go_item_jur_nm);
            jurOrgNm = itemView.findViewById(R.id.go_item_org_nm);
        }
    }
}

