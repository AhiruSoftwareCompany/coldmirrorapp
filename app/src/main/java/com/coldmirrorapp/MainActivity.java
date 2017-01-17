package com.coldmirrorapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private MediaPlayer mediaPlayer;
	private SearchView searchField;
	private ViewFlipper vf;
	private ListView quoteList;
	private Menu menu;
	private boolean doubleBackToExitPressedOnce;

	//List of all quotes for indexing/searching
	Quote[] quoteArray = {
			new Quote(Category.random, "ahahahalustig", "Ahahaha Lustig!"),
			new Quote(Category.random, "boahistdaslustig", "Boah ist das lustig"),
			new Quote(Category.avengers, "bratwurstmitsenf", "Bratwurst mit Senf"),
			new Quote(Category.random, "brutalekillerspiele", "Grund: Brutale Killerspiele"),
			new Quote(Category.random, "coldmirrormachtpornos", "Coldmirror macht Pornos"),
			new Quote(Category.avengers, "einbausparvertrag", "Sweet, ein Bausparvertrag"),
			new Quote(Category.random, "fickmichblick", "\'Fick mich\' Blick"),
			new Quote(Category.harrypotter, "hellemal", "Das helle Mal"),
			new Quote(Category.random, "hi1", "Hiii 1"),
			new Quote(Category.avengers, "ichhabnstander", "Ich hab n Ständer"),
			new Quote(Category.harrypotter, "irischeiren", "Irische Iren"),
			new Quote(Category.harrypotter, "journalist1", "Ich als Journalist"),
			new Quote(Category.harrypotter, "journalist2", "Ich als Journalist 2"),
			new Quote(Category.harrypotter, "keinetraenen", "Keine Tränen"),
			new Quote(Category.avengers, "nebanane", "Ne Banane"),
			new Quote(Category.avengers, "nice", "Nice"),
			new Quote(Category.random, "sodummkanndiedochnichtsein", "So dumm kann die doch nicht sein"),
			new Quote(Category.harrypotter, "sogebildet", "So Gebildet"),
			new Quote(Category.random, "sotalentiert", "So talentiert"),
			new Quote(Category.harrypotter, "topbesetzung", "Top Besetzung"),
			new Quote(Category.random, "unzufrieden", "Unzufriedenes Volk"),
			new Quote(Category.harrypotter, "volldumm", "Ah, voll dumm"),
			new Quote(Category.harrypotter, "geilekarten", "Geile Karten"),
			new Quote(Category.harrypotter, "jetztsindsiealletot", "Jetzt sind sie alle tot."),
			new Quote(Category.harrypotter, "normalersatzbau", "Normaler lateinischer Satzbau"),
			new Quote(Category.harrypotter, "schlechterfilm", "Schlechter Film"),
			new Quote(Category.harrypotter, "toastrack", "Toast Rack"),
			new Quote(Category.harrypotter, "yaytot", "Yay, Tot :)"),
			new Quote(Category.avengers, "wasistdeinlieblingstrinken", "Was ist dein Lieblingsgetränk"),
			new Quote(Category.harrypotter, "istderhaesslich", "Ist der hässlich"),
			new Quote(Category.harrypotter, "jogurth", "Jogurth"),
			new Quote(Category.random, "kindheitgeloescht", "Kindheitserinnerung gelöscht"),
			new Quote(Category.random, "krankenwagen", "Krankenwagen"),
			new Quote(Category.random, "lache1", "Lache 1"),
			new Quote(Category.harrypotter, "lutschen", "Lutschen!"),
			new Quote(Category.harrypotter, "purezauberei", "Pure Zauberei"),
			new Quote(Category.harrypotter, "schwulbullshit", "Schwul, Bullshit"),
			new Quote(Category.random, "istdasnichtgenial", "Ist das nicht genial?"),
			new Quote(Category.harrypotter, "tannenzapfen", "Tannenzapfen."),
			new Quote(Category.harrypotter, "nicerdumbledore", "Netter Dumbledore"),
			new Quote(Category.harrypotter, "blitzaufderstirn", "Blitzchen auf der Stirn")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button stop = (Button) (findViewById(R.id.stop));
		Button stop2 = (Button) (findViewById(R.id.stop2));
		searchField = (SearchView) findViewById(R.id.searchField);
		quoteList = (ListView) findViewById(R.id.quotelist);

		vf = (ViewFlipper) findViewById(R.id.flipper);
		vf.setInAnimation(this, android.R.anim.slide_in_left);
		vf.setOutAnimation(this, android.R.anim.slide_out_right);

		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stop();
			}
		});

		stop2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stop();
			}
		});

		addQuotesToList(null);

		searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				addQuotesToList(searchField.getQuery().toString());
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				addQuotesToList(searchField.getQuery().toString());
				return false;
			}
		});
	}

	public void onClick(View v) {
		play(v);
	}

	public void play(View v) {
		try {
			int resId = getResources().getIdentifier(v.getTag() + "", "raw", getPackageName());
			mediaPlayer = MediaPlayer.create(this, resId);
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
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
		} catch (Exception e) {
			//e Print Stack Trace <-- Fachbegriff für eingefleischte Informatiker
			e.printStackTrace();
		}
	}

	public void play(Quote q) {
		try {
			int resId = getResources().getIdentifier(q.getId(), "raw", getPackageName());
			mediaPlayer = MediaPlayer.create(this, resId);
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}

			});
			mediaPlayer.start();

		} catch (Exception e) {
			Toast.makeText(this, R.string.notworking, Toast.LENGTH_SHORT).show();
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

		quoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				QuoteAdapter qa = (QuoteAdapter) parent.getAdapter();
				play(qa.getItem(position));
			}
		});
	}

	//Layout-Stuff

	public void showSearch() {
		vf.setDisplayedChild(1);
		menu.findItem(R.id.search).setIcon(android.R.drawable.ic_menu_revert);
		searchField.setIconified(false);

	}

	public void hideSearch() {
		vf.setDisplayedChild(0);
		menu.findItem(R.id.search).setIcon(android.R.drawable.ic_menu_search);
		searchField.setIconified(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.search:
				if (vf.getDisplayedChild() == 0) {
					showSearch();
				} else {
					hideSearch();
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (vf.getDisplayedChild() == 0) {
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
		} else {
			hideSearch();
		}
	}
}
