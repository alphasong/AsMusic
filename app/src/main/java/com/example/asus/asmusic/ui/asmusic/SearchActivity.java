package com.example.asus.asmusic.ui.asmusic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.asus.asmusic.BaseActivity;
import com.example.asus.asmusic.R;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;


/**
 * Created by ASUS on 2017/7/17.
 */

public class SearchActivity extends BaseActivity {

    protected SearchHistoryTable historyTable;
    protected SearchView searchView;

    protected void setSearchView(){

        historyTable = new SearchHistoryTable(this);

        searchView = (SearchView) findViewById(R.id.search_view_item);
        if(searchView != null){
            searchView.setHint("歌手/专辑/名称");
            searchView.setVoiceText("");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    getData(newText);
                    searchView.close(true);
                    return false;
                }
            });
            if (searchView.getAdapter() == null){
                List<SearchItem> suggertionList = new ArrayList<>();
                SearchAdapter searchAdapter = new SearchAdapter(this, suggertionList);
                searchAdapter.addOnItemClickListener(new SearchAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
            }
        }

    }
    private void getData(String newText) {
    }

    protected void customSearchView(boolean menuItem){
        if (searchView != null ){
            if (menuItem) {
                searchView.setVersion(SearchView.VERSION_MENU_ITEM);
                searchView.setVersionMargins(SearchView.VERSION_MARGINS_MENU_ITEM);
                searchView.setTheme(SearchView.THEME_LIGHT);
            }
            else {
                searchView.setVersion(SearchView.VERSION_TOOLBAR);
                searchView.setVersionMargins(SearchView.VERSION_MARGINS_TOOLBAR_SMALL);
                searchView.setTheme(SearchView.THEME_LIGHT);
            }
        }
    }
}
