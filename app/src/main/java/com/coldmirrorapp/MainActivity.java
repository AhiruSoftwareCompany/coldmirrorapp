package com.coldmirrorapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {
    private SearchView searchField;
    private MainActivity ma = this;
    private Menu menu;
    private GridView quoteList;
    private MediaPlayer mediaPlayer;
    private boolean doubleBackToExitPressedOnce;
    private SharedPreferences shPrefs;
    private SharedPreferences.Editor shPrefsEdit;

    private Quote[] quoteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteArray = Quote.getAll();

        searchField = findViewById(R.id.searchField);
        quoteList = findViewById(R.id.quoteList);
        modifySearch(true);
        shPrefs = getSharedPreferences("stats", 0);
        shPrefsEdit = shPrefs.edit();

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
                final AdapterView<?> aVP = parent; // AdapterView Parent
                final int pos = position;

                PopupMenu pM = new PopupMenu(ma, view);
                pM.getMenu().add(Menu.FLAG_ALWAYS_PERFORM_CLOSE, 1, 1, getApplication().getString(R.string.share));
              //  pM.getMenu().add(Menu.FLAG_ALWAYS_PERFORM_CLOSE, 2, 1, "Set as ringtone");
                pM.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 1: //Share quote
                                return shareQuote(aVP, pos);
                            case 2: //Set quote as ...
                                QuoteAdapter qa = (QuoteAdapter) aVP.getAdapter();
                                String filename = String.format("%s.mp3", qa.getItem(pos).getId());

                                ///TODO: Copy files to cache dir instead of files dir
                                File imageFile = new File(getFilesDir().getPath() + "/" + filename);

                                Uri uri = FileProvider.getUriForFile(ma, String.format("%s.fileProvider", getApplication().getPackageName()), imageFile);


                               // RingtoneManager.setActualDefaultRingtoneUri(ma, RingtoneManager.TYPE_NOTIFICATION, uri);
                                //System.out.println(RingtoneManager.getActualDefaultRingtoneUri(ma, RingtoneManager.TYPE_NOTIFICATION));



                                break;
                        }
                        return true;
                    }
                });
                pM.show();
                return true;
            }
        });
    }

    private boolean shareQuote(AdapterView<?> parent, int position){
        QuoteAdapter qa = (QuoteAdapter) parent.getAdapter();
        String filename = String.format("%s.mp3", qa.getItem(position).getId());

        ///TODO: Copy files to cache dir instead of files dir
        File imageFile = new File(getFilesDir().getPath() + "/" + filename);

        Uri uri = FileProvider.getUriForFile(ma, String.format("%s.fileProvider", getApplication().getPackageName()), imageFile);

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

    private void modifySearch(boolean clear) {
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

    private void addQuotesToList(String filter) {
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

    private void play(Quote q) {
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
            Log.e("Error with quote: '" + q.getId() + "'", "" + e);
            Toast.makeText(this, R.string.notWorking, Toast.LENGTH_SHORT).show();
        }

        shPrefsEdit.putInt(q.getId(), shPrefs.getInt(q.getId(), 0) + 1);
        shPrefsEdit.commit();
        Log.d("Play sound", q.getId() + " was played " + shPrefs.getInt(q.getId(), 0) + " time(s)");
    }

    private void stop() {
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
                modifySearch(searchField.getVisibility() != View.GONE);
                break;
            case R.id.stop:
                stop();
                break;
            case R.id.random:
                play(Quote.getRandom());
                break;
            case R.id.about:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle(R.string.aboutLong);
                b.setMessage(R.string.aboutText);

                AlertDialog d = b.show();
                TextView messageView = d.findViewById(android.R.id.message);
                TextView titleView = d.findViewById(getResources().getIdentifier("alertTitle", "id", "android"));
                messageView.setGravity(Gravity.CENTER);
                titleView.setGravity(Gravity.CENTER);
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
        Toast.makeText(this, R.string.pressAgainToLeave, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1700);
    }
}
