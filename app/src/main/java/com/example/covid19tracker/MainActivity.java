package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity
{

    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav=findViewById(R.id.bottom_nav);

        if(savedInstanceState==null)
        {
            bottomNav.setItemSelected(R.id.textualData,true);
            fragmentManager=getSupportFragmentManager();
            TextFragment textFragment = new TextFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,textFragment)
                    .commit();

        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id)
            {
                Fragment fragment = null;
                switch (id)
                {
                    case R.id.textualData:
                        fragment=new TextFragment();
                        break;
                    case R.id.graphicalData:
                        fragment=new GraphFragment();
                        break;
                    case R.id.aboutData:
                        fragment=new AboutFragment();
                        break;
                }
                if (fragment!=null)
                {
                    fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .commit();
                }else {
                    Log.e(TAG,"Error In creating fragment ");
                }
            }
        });
    }
}
