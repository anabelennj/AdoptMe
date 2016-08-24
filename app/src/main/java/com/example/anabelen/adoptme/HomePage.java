package com.example.anabelen.adoptme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private List<Mascota> mascotasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MascotasAdapter mAdapter;
    private ImageView mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mascotas = (ImageView) findViewById(R.id.mascotas);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MascotasAdapter(mascotasList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Mascota mascota = mascotasList.get(position);
                Toast.makeText(getApplicationContext(), mascota.getTipo() + " fue seleccionado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();

    }

    private void prepareMovieData() {
        Mascota mascota = new Mascota("Perro", "Husky", "1-3 años","Saludable","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Gato", "Persa", "4-6 años","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Conejo", "Otro", "10-12 meses","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Ave", "Otro", "4-6 años","Saludable","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Perro", "Labrador", "7-9 años","Lesión física","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Perro", "Pitbull", "1-3 años","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Perro", "Golden Retriever", "4-6 meses","Saludable","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Conejo", "Otro", "1-3 meses","Saludable","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Ave", "Otro", "1-3 años","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Gato", "Siamés", "4-6 años","Saludable","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Gato", "Romano", "7-9 años","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Perro", "Boxer", "4-6 años","Saludable","Macho");
        mascotasList.add(mascota);

        mascota = new Mascota("Gato", "Persa", "1-3 años","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Ave", "Otro", "4-6 meses","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Perro", "Husky", "7-9 meses","Saludable","Hembra");
        mascotasList.add(mascota);

        mascota = new Mascota("Perro", "Chihuahua", "4-6 años","Saludable","Macho");
        mascotasList.add(mascota);

        mAdapter.notifyDataSetChanged();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuactionbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_agregar_mascota:
                startActivity(new Intent(this,RegistroMasc.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private HomePage.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final HomePage.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
