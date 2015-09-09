package com.xgtongcheng.xgtc.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.github.mrengineer13.snackbar.SnackBar;
import com.xgtongcheng.xgtc.AppConfig;
import com.xgtongcheng.xgtc.R;
import com.xgtongcheng.xgtc.adapter.MenuAdapter;
import com.xgtongcheng.xgtc.adapter.model.MenuModel;
import com.xgtongcheng.xgtc.common.util.AUtil;
import com.xgtongcheng.xgtc.common.util.SBar;
import com.xgtongcheng.xgtc.fragment.ChaxunFragment;
import com.xgtongcheng.xgtc.fragment.FenfaFragment;
import com.xgtongcheng.xgtc.fragment.LanshouFragment;
import com.xgtongcheng.xgtc.fragment.QianshouFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private List<MenuModel> menuList;
    private MenuAdapter menuAdapter;
    private RelativeLayout menuLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @BindString(R.string.lanshou)
    String lanshou;
    @BindString(R.string.fenfa)
    String fenfa;
    @BindString(R.string.qianshou)
    String qianshou;
    @BindString(R.string.chaxun)
    String chaxun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        menuLayout = (RelativeLayout) findViewById(R.id.menu_rellayout);

        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);
        // 打開 up bottom
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        // 實作 drawer toggle 並放入 toolbar
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
//        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        initMenu();
    }

    private void initMenu(){
        ListView listView= ButterKnife.findById(menuLayout,R.id.menu_listview);

        menuList=new ArrayList<MenuModel>();

        menuList.add(new MenuModel(R.mipmap.lanshou, lanshou, AppConfig.LANSHOU));
        menuList.add(new MenuModel(R.mipmap.fenfa, fenfa,AppConfig.FENFA));
        menuList.add(new MenuModel(R.mipmap.qianshou, qianshou,AppConfig.QIANSHOU));
        menuList.add(new MenuModel(R.mipmap.chaxun, chaxun,AppConfig.CHAXUN));

        menuAdapter=new MenuAdapter(this, menuList);


        listView.setOnItemClickListener(new OnMenuItemClickListener());
        listView.setAdapter(menuAdapter);
    }

    private Fragment fragment  ;

    class OnMenuItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fm = getSupportFragmentManager();
            fragment = fm.findFragmentByTag(menuList.get(position).getFragmentTag());

            if(fragment == null )
            {
                switch (position){
                    case 0:
                        fragment = new LanshouFragment();
                        break;
                    case 1:
                        fragment = new FenfaFragment();
                        break;
                    case 2:
                        fragment = new QianshouFragment();
                        break;
                    case 3:
                        fragment = new ChaxunFragment();
                }

                fm.beginTransaction().add(R.id.fragment_layout, fragment,menuList.get(position).getFragmentTag()).commit();
            }else{
                fm.beginTransaction().show(fragment).commit();
            }

            for (int i=0;i<menuList.size();i++){
                if (i!=position && fm.findFragmentByTag(menuList.get(i).getFragmentTag())!=null){
                    fm.beginTransaction().hide(fm.findFragmentByTag(menuList.get(i).getFragmentTag())).commit();
                }
            }

            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SBar.showActionLong("111", this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            AUtil.exitBy2Click(this);
        }
        return false;
    }

}
