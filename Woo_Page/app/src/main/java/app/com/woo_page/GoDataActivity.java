package app.com.woo_page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.com.woo_page.database.goDataVO;
import app.com.woo_page.helper.GoDataViewAdapter;

public class GoDataActivity extends AppCompatActivity {

    RecyclerView goDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_data);

//        Intent myIntent= new Intent();
        List<goDataVO> goList = (ArrayList) getIntent().getSerializableExtra("goData");

        goDataList = findViewById(R.id.go_data_list);
        GoDataViewAdapter adapter = new GoDataViewAdapter(goList);
        goDataList.setAdapter(adapter);
        goDataList.setLayoutManager(new LinearLayoutManager(this));


    }
}
