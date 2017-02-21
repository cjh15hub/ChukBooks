package com.grouptwosoftworks.chukbooks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.grouptwosoftworks.chukbooks.R;

public class ChildFragment extends Fragment {

    private static final String favoritedBookNamesKey = "favoritedBookNamesKey";

    private MainActivity parentActivity;

    private Book[] books;

    BooksAdapter booksAdapter;

    public ChildFragment() {
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

//        @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        final ArrayList<Integer> favoritedBookNames =
//                savedInstanceState.getIntegerArrayList(favoritedBookNamesKey);
//
//        for(Book book: books){
//            if(favoritedBookNames.contains(book.getName())){
//                book.setIsFavorite(true);
//                parentActivity.favoritedBooks.add(book);
//            }
//        }
//
//        for (int bookName : favoritedBookNames) {
//            for (Book book : books) {
//                if (book.getName() == bookName) {
//                    book.setIsFavorite(true);
//                    break;
//                }
//            }
//        }
//
//    }


}
