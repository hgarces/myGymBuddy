package com.android.ipm.mygymbuddy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.ipm.mygymbuddy.content.User;
import com.android.ipm.mygymbuddy.fragments.HomeFragment;
import com.android.ipm.mygymbuddy.fragments.NewActivityFragment;
import com.android.ipm.mygymbuddy.fragments.NutritionFragment;
import com.android.ipm.mygymbuddy.fragments.SettingsFragment;
import com.android.ipm.mygymbuddy.fragments.StatisticsFragment;

public class MainActivity extends AppCompatActivity {

    private User mUser;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mUser = (User) getIntent().getSerializableExtra(User.EXTRA);
        // inicializa a toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // adiciona fragment com o ecra inicial (calendario)
        loadFragment(R.id.nav_home);

        // inicializa Drawer Layout e ActionBarDrawerToggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();

        // inicializa Navigation View e faz as cenas quando se carrega num item
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                loadFragment(menuItem.getItemId());
                return true;
            }
        });

        if(mUser != null) {
            View header = mNavigationView.getHeaderView(0);
            TextView name = (TextView) header.findViewById(R.id.nav_header_username);
            TextView email = (TextView) header.findViewById(R.id.nav_header_email);
            name.setText(mUser.getNome());
            email.setText(mUser.getEmail());
        }
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    private void loadFragment(int menuItemId) {
        Fragment fragment = null;

        switch (menuItemId) {
            default:
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_exercises:
                fragment = new NewActivityFragment();
                break;
            case R.id.nav_nutrition:
                fragment = new NutritionFragment();
                break;
            case R.id.nav_statistics:
                fragment = new StatisticsFragment();
                break;
            case R.id.action_settings:
                fragment = new SettingsFragment();
                break;
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

}
