package com.coldmirrorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private SearchView searchField;
    private MainActivity ma = this;
    private Menu menu;
    private GridView quoteList;
    private MediaPlayer mediaPlayer;
    private boolean doubleBackToExitPressedOnce;

    Quote[] quoteArray = {
            new Quote(Category.harrypotter, "blitzaufderstirn", "Blitzchen auf der Stirn"),
            new Quote(Category.harrypotter, "geilekarten", "Geile Karten"),
            new Quote(Category.harrypotter, "hellemal", "Das helle Mal"),
            new Quote(Category.harrypotter, "irischeiren", "Irische Iren"),
            new Quote(Category.harrypotter, "istderhaesslich", "Ist der hässlich"),
            new Quote(Category.harrypotter, "jetztsindsiealletot", "Jetzt sind sie alle tot."),
            new Quote(Category.harrypotter, "jogurth", "Jogurth"),
            new Quote(Category.harrypotter, "journalist1", "Ich als Journalist"),
            new Quote(Category.harrypotter, "journalist2", "Ich als Journalist 2"),
            new Quote(Category.harrypotter, "keinetraenen", "Keine Tränen"),
            new Quote(Category.harrypotter, "lutschen", "Lutschen!"),
            new Quote(Category.harrypotter, "nicerdumbledore", "Netter Dumbledore"),
            new Quote(Category.harrypotter, "normalersatzbau", "Normaler lateinischer Satzbau"),
            new Quote(Category.harrypotter, "purezauberei", "Pure Zauberei"),
            new Quote(Category.harrypotter, "schlechterfilm", "Schlechter Film"),
            new Quote(Category.harrypotter, "schwulbullshit", "Schwul, Bullshit"),
            new Quote(Category.harrypotter, "sogebildet", "So Gebildet"),
            new Quote(Category.harrypotter, "tannenzapfen", "Tannenzapfen."),
            new Quote(Category.harrypotter, "toastrack", "Toast Rack"),
            new Quote(Category.harrypotter, "topbesetzung", "Top Besetzung"),
            new Quote(Category.harrypotter, "volldumm", "Ah, voll dumm"),
            new Quote(Category.harrypotter, "yaytot", "Yay, Tot :)"),
            new Quote(Category.avengers, "bratwurstmitsenf", "Bratwurst mit Senf"),
            new Quote(Category.avengers, "einbausparvertrag", "Sweet, ein Bausparvertrag"),
            new Quote(Category.avengers, "ichhabnstander", "Ich hab n Ständer"),
            new Quote(Category.avengers, "nebanane", "Ne Banane"),
            new Quote(Category.avengers, "nice", "Nice"),
            new Quote(Category.avengers, "wasistdeinlieblingstrinken", "Was ist dein Lieblingsgetränk"),
            new Quote(Category.random, "ahahahalustig", "Ahahaha Lustig!"),
            new Quote(Category.random, "boahistdaslustig", "Boah ist das lustig"),
            new Quote(Category.random, "brutalekillerspiele", "Grund: Brutale Killerspiele"),
            new Quote(Category.random, "coldmirrormachtpornos", "Coldmirror macht Pornos"),
            new Quote(Category.random, "fickmichblick", "\'Fick mich\' Blick"),
            new Quote(Category.random, "hi1", "Hiii 1"),
            new Quote(Category.random, "istdasnichtgenial", "Ist das nicht genial?"),
            new Quote(Category.random, "kindheitgeloescht", "Kindheitserinnerung gelöscht"),
            new Quote(Category.random, "krankenwagen", "Krankenwagen"),
            new Quote(Category.random, "lache1", "Lache 1"),
            new Quote(Category.random, "sodummkanndiedochnichtsein", "So dumm kann die doch nicht sein"),
            new Quote(Category.random, "sotalentiert", "So talentiert"),
            new Quote(Category.random, "unzufrieden", "Unzufriedenes Volk")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = (SearchView) findViewById(R.id.searchField);
        quoteList = (GridView) findViewById(R.id.quoteList);
        modifySearch(true);

        searchField.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchField.setVisibility(View.GONE);
                return false;
            }
        });

        searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addQuotesToList(searchField.getQuery().toString());
                searchField.clearFocus();
                addQuotesToList(null);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                addQuotesToList(searchField.getQuery().toString());
                return false;
            }
        });

        quoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuoteAdapter qa = (QuoteAdapter) parent.getAdapter();

                // Removing the following line for sound overload
                stop();
                play(qa.getItem(position));
            }
        });

        quoteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                QuoteAdapter qa = (QuoteAdapter) parent.getAdapter();
                String filename = qa.getItem(position).getId() + ".mp3";

                ///TODO: Copy files to cache dir instead of files dir
                File imageFile = new File(getFilesDir().getPath() + "/" + filename);

                Uri uri = FileProvider.getUriForFile(ma, getApplication().getPackageName() + ".fileprovider", imageFile);

                try {
                    if (!imageFile.exists()) {
                        final InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(qa.getItem(position).getId(), "raw", getPackageName()));
                        final FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);


                        byte buf[] = new byte[1024];
                        int len;

                        while ((len = inputStream.read(buf)) > 0) {
                            outputStream.write(buf, 0, len);
                        }

                        outputStream.flush();
                        outputStream.getFD().sync();
                        outputStream.close();
                        inputStream.close();
                    }

                    Intent intent = ShareCompat.IntentBuilder.from(ma)
                            .setType("audio/*")
                            .setStream(uri)
                            .setChooserTitle(getResources().getText(R.string.share))
                            .createChooserIntent()
                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    ma.startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    public void modifySearch(boolean clear) {
        if (clear) {
            searchField.setVisibility(View.GONE);
            searchField.setIconified(true);
            addQuotesToList(null);
            searchField.clearFocus();
        } else {
            searchField.setVisibility(View.VISIBLE);
            searchField.setIconified(false);
            searchField.requestFocus();
        }
    }

    public void addQuotesToList(String filter) {
        QuoteAdapter quoteAdapter = new QuoteAdapter(this, new ArrayList<Quote>());
        quoteList.setAdapter(quoteAdapter);

        for (Quote aQuoteArray : quoteArray) {
            if (filter != null) {
                if (aQuoteArray.getName().toLowerCase().contains(filter.toLowerCase())) {
                    quoteAdapter.add(aQuoteArray);
                }
            } else {
                quoteAdapter.add(aQuoteArray);
            }
        }
    }

    public void play(Quote q) {
        try {
            int resId = getResources().getIdentifier(q.getId(), "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });
            menu.findItem(R.id.stop).setVisible(true);
            mediaPlayer.start();

        } catch (Exception e) {
            Toast.makeText(this, R.string.notworking, Toast.LENGTH_SHORT).show();
        }
    }

    public void stop() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                mediaPlayer.release();
            }
            mediaPlayer = null;
            menu.findItem(R.id.stop).setVisible(false);
        } catch (Exception e) {
            //e Print Stack Trace <-- Fachbegriff für eingefleischte Informatiker
            e.printStackTrace();
        }
    }


    /**
     * Layout-Stuff
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.stop).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                if (searchField.getVisibility() == View.GONE) {
                    modifySearch(false);
                } else {
                    modifySearch(true);
                }
                break;
            case R.id.stop:
                stop();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (searchField.getVisibility() == View.VISIBLE) {
            modifySearch(true);
            return;
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.PressAgainToLeave, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1700);
    }
}
