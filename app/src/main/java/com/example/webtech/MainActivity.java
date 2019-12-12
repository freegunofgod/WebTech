package com.example.webtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private ListFragment listFragment;
    private ProfilFragment profilFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Song> songList= (List<Song>) getIntent().getSerializableExtra("KEY_DATA");

        listFragment = new ListFragment();
        profilFragment = new ProfilFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

         //Méthode pour définir ce qu'il se passe lors d'un appuie sur les icônes
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item1:
                        loadFragment(listFragment);
                                return true;
                    case R.id.item2:
                        loadFragment(profilFragment);
                        return true;
                }
                return false;
            }
        });

        //Récupérer les données récolter par l'instance SplashScreen dans une variable
        // String result = getIntent().getStringExtra("KEY_DATA");
        //Mettre ces données dans un conteneur
        Bundle bundle = new Bundle();

        bundle.putSerializable("KEY_DATA", (Serializable) songList);
        //Assignez ces données aux bundles
        listFragment.setArguments(bundle);
        loadFragment(listFragment);

        //String data = getIntent().getStringExtra("KEY_DATA");
        //TextView tvHello =findViewById(R.id.tvHello);
        //tvHello.setText(data);

        //Toast.makeText((getApplicationContext(),"MESSAGE", Toast.LENGTH_SHORT)).show();;
    }

    //Méthode permettant de charger les fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}
