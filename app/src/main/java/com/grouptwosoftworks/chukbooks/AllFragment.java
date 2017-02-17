package com.grouptwosoftworks.chukbooks;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.grouptwosoftworks.chukbooks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {


    private static final String favoritedBookNamesKey = "favoritedBookNamesKey";

    private MainActivity parentActivity;

    private Book[] books;

    BooksAdapter booksAdapter;

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_child, container, false);

        parentActivity = ((MainActivity)getActivity());
        books = parentActivity.currentBookList;

        booksAdapter = new BooksAdapter(getActivity(),books);
        GridView gridView = (GridView) v.findViewById(R.id.gridview);
        gridView.setAdapter(booksAdapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id){
                Book book = books[position];
                book.toggleFavorite();
                if(book.getIsFavorite()) parentActivity.favoritedBooks.add(book);
                else parentActivity.favoritedBooks.remove(book);
                booksAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }

}
