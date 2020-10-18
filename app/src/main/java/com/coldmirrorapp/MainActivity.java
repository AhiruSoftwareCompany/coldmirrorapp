package com.coldmirrorapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private SearchView searchField;
    private final MainActivity ma = this;
    private Menu menu;
    private GridView quoteList;
    private MediaPlayer mediaPlayer;
    private boolean doubleBackToExitPressedOnce;
    private SharedPreferences shPrefs;
    private SharedPreferences.Editor shPrefsEdit;
    private QuoteAdapter qa;
    private ListStatus listStatus;

    private Quote[] quoteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteArray = Quote.getAll();

        searchField = findViewById(R.id.searchField);
        quoteList = findViewById(R.id.quoteList);
        setGridLayout(ListStatus.BUTTON);

        modifySearch(true);
        shPrefs = getSharedPreferences("stats", 0);
        shPrefsEdit = shPrefs.edit();
        shPrefsEdit.apply();

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
                final int pos = position;
                qa = (QuoteAdapter) parent.getAdapter();
                final String filename = String.format("%s.mp3", qa.getItem(pos).getId());
                final File imageFile = new File(getFilesDir().getPath() + "/" + filename);
                final Uri uri = FileProvider.getUriForFile(ma, String.format("%s.fileProvider", getApplication().getPackageName()), imageFile);


                ///TODO: Copy files to cache dir instead of files dir --done
                ///TODO: Currently the created files is empty.. something about the reading -> output process doesn't work
                final File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS), "coldmirrorapp");
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Log.e("sharing quotes", "failed to create directory");
                    } else {
                        Log.i("sharing quotes", "directory created");
                    }
                }
                try {
                    int size = (int) imageFile.length();
                    byte[] bytes = new byte[size];
                    OutputStream out = new FileOutputStream(new File(mediaStorageDir.getPath() + File.separator + filename));
                    BufferedInputStream buf = new BufferedInputStream(new FileInputStream(imageFile));
                    out.write(buf.read(bytes, 0, bytes.length));
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(getFilesDir().getPath());

                PopupMenu pM = new PopupMenu(ma, view);
                pM.getMenu().add(Menu.FLAG_ALWAYS_PERFORM_CLOSE, 1, 1, getApplication().getString(R.string.share));
                // pM.getMenu().add(Menu.FLAG_ALWAYS_PERFORM_CLOSE, 2, 1, "Set as ringtone");
                pM.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 1: //Share quote
                                try {
                                    if (!imageFile.exists()) {
                                        final InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(qa.getItem(pos).getId(), "raw", getPackageName()));
                                        final FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

                                        byte[] buf = new byte[1024];
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
                            case 2: //Set quote as ...

                                if (Build.VERSION.SDK_INT >= 23) {
                                    if (ContextCompat.checkSelfPermission(ma, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                        Log.v("setup", "Permission is granted");
                                    } else {
                                        Log.v("setup", "Permission is revoked");
                                        ActivityCompat.requestPermissions(ma, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                                    }
                                } else { //permission is automatically granted on sdk<23 upon installation
                                    Log.v("setup", "Permission is granted");
                                }


                                File src = new File(getFilesDir().getPath() + "/" + filename);
                                File dst = new File(mediaStorageDir.getPath() + File.separator + filename);

                                FileInputStream in = null;
                                try {
                                    in = new FileInputStream(src);
                                    FileOutputStream out = null;
                                    try {
                                        out = new FileOutputStream(dst);
                                        byte[] buf = new byte[1024];
                                        int len;
                                        while ((len = in.read(buf)) > 0)
                                            out.write(buf, 0, len);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        try {
                                            if (out != null)
                                                out.close();
                                            in.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (in != null)
                                            in.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }


                                //commented code below makes your settings app crash because a notification sound is set that is unreachable outside this app
                            /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(ma)) {
                                    Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                    ma.startActivity(i);
                                } else {
                                    try {
                                        // The line below will set it as a default ring tone replace
                                        // RingtoneManager.TYPE_RINGTONE with RingtoneManager.TYPE_NOTIFICATION
                                        // to set it as a notification tone
                                        RingtoneManager.setActualDefaultRingtoneUri(
                                                getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION,
                                                uri);
                                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), uri);
                                        r.play();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    try {
                                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                        r.play();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                */
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

    private boolean shareQuote(AdapterView<?> parent, int position) {

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
        //QuoteAdapter quoteAdapter = new QuoteButtonAdapter(this, new ArrayList<Quote>());
        QuoteAdapter quoteAdapter = new QuoteAdapter(this, new ArrayList<Quote>(), listStatus);
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

    public void setGridLayout(ListStatus listStatus) {
        this.listStatus = listStatus;
        // setting the correct number of columns
        quoteList.setNumColumns(listStatus.numColumns);

        if (listStatus == ListStatus.LIST) {
            //do stuff for list
            quoteList.setNumColumns(1);
            quoteList.setVerticalSpacing(5); // equals 2dp?
        } else {
            // do stuff for button
            quoteList.setNumColumns(2);
            quoteList.setVerticalSpacing(0);
        }
        // Clear search and rebuild thing
        modifySearch(true);
    }

    public void switchGridLayout() {
        if (listStatus == ListStatus.LIST) {
            setGridLayout(ListStatus.BUTTON);
        } else {
            setGridLayout(ListStatus.LIST);
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
            case R.id.list:
                switchGridLayout();
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
