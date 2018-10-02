package app.com.join_form;

import android.animation.StateListAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinActivity extends AppCompatActivity {

    ArrayAdapter sAdapter;
    Spinner guSpin;
    List<String> gwGuList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        guSpin = findViewById(R.id.gu_spin);

        gwGuList = new ArrayList<>();
        gwGuList.add("북구");
        gwGuList.add("서구");
        gwGuList.add("남구");
        gwGuList.add("북구");
        gwGuList.add("광산구");

        // 배열이나 ArryaList를 이용해서 spinner를 사용할때는 ArrayAdapter라는 클래스를 사용해서 spinner를 구성한다.
        sAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gwGuList);

        Spinner city_spin = findViewById(R.id.city_spin);
        city_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String txt = parent.getItemAtPosition(position).toString();
                if (!txt.equalsIgnoreCase("시도선택")){
                Toast.makeText(JoinActivity.this,txt,Toast.LENGTH_LONG).show();
                if(txt.equalsIgnoreCase("서울특별시")){

                    // xml 파일에 정의된 string-arry를 List로 변환시키는 코드
                    List<String> seoul = Arrays.asList(getResources().getStringArray(R.array.seoul_gus));

                    sAdapter = new ArrayAdapter(JoinActivity.this,R.layout.support_simple_spinner_dropdown_item,seoul);
                    guSpin.setAdapter(sAdapter);
                }else if(txt.equalsIgnoreCase("광주광역시")){
                    sAdapter = new ArrayAdapter(JoinActivity.this,R.layout.support_simple_spinner_dropdown_item,gwGuList);
                    guSpin.setAdapter(sAdapter);
                }
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); // city_spin.setOnItemSele... END


        guSpin.setAdapter(sAdapter);

        guSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String txt = parent.getItemAtPosition(position).toString();
                Toast.makeText(JoinActivity.this,txt,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
