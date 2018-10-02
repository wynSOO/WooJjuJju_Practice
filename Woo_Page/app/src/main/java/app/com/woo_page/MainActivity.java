package app.com.woo_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import app.com.woo_page.database.BscVO;
import app.com.woo_page.database.GoData;
import app.com.woo_page.database.goDataVO;
import app.com.woo_page.helper.MainPageAdaper;
import app.com.woo_page.helper.StringPagerAdapter;
import app.com.woo_page.service.WooDao;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ViewPager vp;
    TabLayout tabLayout;
    WooDao wooDao;

    // firebase에서 data를 가져와서
    // tablayout에 tab list를 생성하기 위해 2개의 List변수를 선언한다.
    List<String> bscList;
    List<String> genList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            String[] pageTitle;
            MainPageAdaper adaper;
            StringPagerAdapter stringPagerAdapter;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    stringPagerAdapter = new StringPagerAdapter(getSupportFragmentManager(),bscList);
                    vp.setAdapter(stringPagerAdapter);
//                    pageTitle = wooDao.getTitle_preg();
//                    adaper = new MainPageAdaper(getSupportFragmentManager(),pageTitle);
//                    vp.setAdapter(adaper);
                    return true;
                case R.id.navigation_dashboard:
                    stringPagerAdapter = new StringPagerAdapter(getSupportFragmentManager(),genList);
                    vp.setAdapter(stringPagerAdapter);
//                    pageTitle = wooDao.getTitle_baby();
//                    adaper = new MainPageAdaper(getSupportFragmentManager(),pageTitle);
//                    vp.setAdapter(adaper);
                    return true;


                    // *****공공 DB********
                    case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);

                    GoData goData = new GoData(); // Async 호출 코드

//                    goData.execute(100);

                        List<goDataVO> goDataList = null;
                        try{
                            goDataList = (List)goData.execute(100).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        // goData가 공공DB 데이터를 모두 가져오기까지 대기
//                    while (!goData.isExeFlag());

                        if(goDataList.size()>0) {
                            Intent goIntent = new Intent(MainActivity.this, GoDataActivity.class);
                            goIntent.putCharSequenceArrayListExtra("goData",(ArrayList)goDataList);
                            startActivity(goIntent);
                        }



                    return true;

                case R.id.navigation_go:

//                    GoData goData = new GoData();
//                    goData.execute(100);
                    // goData.getData();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp=findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        wooDao = new WooDao(getApplicationContext());
        wooDao.makeTitle();
        String[] pageTitle = wooDao.getTitle_preg();

        bscList = new ArrayList<>();
        genList = new ArrayList<>();
        // firebase로 부터 데이터 변환에 대한 event를 수신할 method를 선언
        // firebase로 부터 이벤트 신호가 오면 데이터를 읽어서
        // bsclist와 genList에 데이터를 저장해 놓는다.
        FirebaseDatabase.getInstance() // db 정보 획득
        .getReference() // db 연결 객체 획득
        .child("doc") // doc 데이터를 감시하라
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // dataSnapshot
                // firebase의 doc 데이터가 변화되면, 자동으로 데이터를 수신해서
                // 보관할 객체

                // dataSanpshot으로 부터 필요한 데이터를 추출하는 코드

                bscList.clear();
                genList.clear();
                for (DataSnapshot sd : dataSnapshot.getChildren()){
                    /*
                    1. sd로부터 각 칼럼값을 추출
                    2. 추출된 값을 vo에 세팅하는 절차
                    BscVO vo = new BscVO();
                    vo.setVO(sd.getNO())
                    vo.setTitle(sd.getTitle())
                    vo.setMemo(sd.getMemo())
                    vo.setGenre(sd.getGenre())
                     */
                    BscVO vo = sd.getValue(BscVO.class);

                    bscList.add(vo.bsc);


                    genList.add(vo.genre);


                }
                // 현재 추출된 data는 중복된 data
                // >> 중복없는 data로 변환
                Set<String> set = new TreeSet<>(bscList); // 중복되지 않고, 오름차순 정렬
                bscList = new ArrayList<>(set);

                genList = new ArrayList<>(new TreeSet<>(genList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Data오류",databaseError.toString());
            }
        }); // 데이터가 변하되면 알려달라

        // 최초에 보여줄 page
        MainPageAdaper adaper = new MainPageAdaper(getSupportFragmentManager(),pageTitle);

        vp.setAdapter(adaper);
        tabLayout.setupWithViewPager(vp);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
