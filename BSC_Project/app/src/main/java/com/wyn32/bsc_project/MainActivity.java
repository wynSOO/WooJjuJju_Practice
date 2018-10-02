package com.wyn32.bsc_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wyn32.bsc_project.database.MemberVO;

import java.lang.reflect.Member;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    TextView txt_userId;
    TextView txt_password;
    TextView txt_nick;
    TextView txt_email;
    RadioGroup radioGroup;
    RadioButton txt_gender;
    Spinner txt_age;
    String str_age;

    Button btn_join;

    Button btn_login;
    MemberVO memberVO;
    DatabaseReference databaseReference;


    // 중복ID를 검사하기 위한 Data 검색 event
    ValueEventListener checkId = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
            while (child.hasNext()){
                if (child.next().getKey().equalsIgnoreCase(txt_userId.getText().toString())){

                    Toast.makeText(getApplicationContext(),"존재하는Id",Toast.LENGTH_LONG).show();

                    //이벤트가 반복 실행되는 것을 방지하기 위해 이벤트를 제거
                    databaseReference.removeEventListener(this);
                    return;
                }
            }

            // 중복된 Id가 없으면 새로운 member로 등록처리
            databaseReference.child(memberVO.getUserID()).setValue(memberVO);
            Toast.makeText(getApplicationContext(),"회원가입완료",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberVO = new MemberVO();
        txt_userId = findViewById(R.id.txt_userid);
        txt_password = findViewById(R.id.txt_password);
        txt_nick = findViewById(R.id.txt_nick);
        txt_email = findViewById(R.id.txt_email);
//        txt_gender = findViewById(R.id.gender)

        radioGroup = findViewById(R.id.layout_gender);

        txt_age = findViewById(R.id.txt_age);

        txt_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_age = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("members");
        btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (txt_userId.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"아이디를 입력하세요",Toast.LENGTH_LONG).show();
                    return;
                }
                memberVO.setUserID(txt_userId.getText().toString());

                if (txt_password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"비밀번호를 입력하세요",Toast.LENGTH_LONG).show();
                    return;
                }
                memberVO.setPassword(txt_password.getText().toString());



                //RadioGroup에서 현재 선택된 RadioButton Id를 추출하고
                // 그 Id를 이용해서 RadioButton 초기화
                int rid = radioGroup.getCheckedRadioButtonId();
                txt_gender = findViewById(rid);
                memberVO.setGender(txt_gender.getText().toString());


                if (str_age.equalsIgnoreCase("연령선택")){
                    Toast.makeText(MainActivity.this,"연령을 선택하세요",Toast.LENGTH_LONG).show();
                    return;
                }
                memberVO.setAge(str_age);

                memberVO.setEmail(txt_email.getText().toString());
                memberVO.setNick(txt_nick.getText().toString());

                // Firebase에 저장하기
//                DatabaseReference databaseReference;
                // members라는 table을 열어라 없으면 새로 생성
                databaseReference = FirebaseDatabase.getInstance().getReference("members");

                // members table에 userid를 키값으로 하는 데이터를 생성하거나, 이미 있으면 Update를 실행하라
//                databaseReference.child(memberVO.getUserID()).setValue(memberVO);

                // 이벤트 선언
                databaseReference.addListenerForSingleValueEvent(checkId);
                Toast.makeText(MainActivity.this,"회원가입완료",Toast.LENGTH_SHORT).show();

            }
        });




//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
