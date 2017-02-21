package com.grouptwosoftworks.chukbooks;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Book[] currentBookList;

    public Book[] books = {
            new Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/abc.jpg"),
            new Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/areyoumymother.jpg"),
            new Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/whereisbabysbellybutton.jpg"),
            new Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onthenightyouwereborn.jpg"),
            new Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/handhandfingersthumb.jpg"),
            new Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/theveryhungrycaterpillar.jpg"),
            new Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thegoingtobedbook.jpg"),
            new Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/ohbabygobaby.jpg"),
            new Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thetoothbook.jpg"),
            new Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish, Book.CATEGORY.Child,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onefish.jpg")

//            new Book(R.string.PlayersHandbook_2E, R.string.Mike_Mearls, R.drawable.ph5e,  Book.CATEGORY.DnD,""),
//            new Book(R.string.MosterManual_5E, R.string.Christopher_Perkins, R.drawable.mm5e,  Book.CATEGORY.DnD,""),
//            new Book(R.string.Tome_of_Beasts_5E, R.string.Dan_Dillon, R.drawable.tob5e,  Book.CATEGORY.DnD,""),
//            new Book(R.string.Fifth_Edition_Options, R.string.Brian_Berg, R.drawable.options5e,  Book.CATEGORY.DnD,""),
//            new Book(R.string.Dungeon_Masters_Guide, R.string.Jeremy_Crawford, R.drawable.dmg5e,  Book.CATEGORY.DnD,""),
//
//            new Book(R.string.Database, R.string.David_Kroenke, R.drawable.database,  Book.CATEGORY.TEXT_BOOK,""),
//            new Book(R.string.Java_EO, R.string.Tony_Gaddis, R.drawable.java,  Book.CATEGORY.TEXT_BOOK,""),
//            new Book(R.string.Networking, R.string.Gregory_Tomsho, R.drawable.networking,  Book.CATEGORY.TEXT_BOOK,""),
//            new Book(R.string.Precalculus, R.string.Sullivan, R.drawable.precalculus,  Book.CATEGORY.TEXT_BOOK,""),
//            new Book(R.string.Psychology, R.string.Karen_Huffman, R.drawable.psychology,  Book.CATEGORY.TEXT_BOOK,""),
//            new Book(R.string.Technical_Drawing, R.string.Frederic_Giesecke, R.drawable.technical_drawing,  Book.CATEGORY.TEXT_BOOK,"")

    };




//    BooksAdapter booksAdapter;

    private static final String favoritedBookNamesKey = "favoritedBookNamesKey";
    public static ArrayList<Book> favoritedBooks;
    private Spinner category_chooser;
    private int list_fragment;

    private Fragment[] category_frags;

    private void spliceBooks(Book.CATEGORY category){

        ArrayList<Book> tempList = new ArrayList<>();
        for(Book book: books){
            if(category == book.getCategory() || category== Book.CATEGORY.All){
                tempList.add(book);
            }
        }
        currentBookList = tempList.toArray(new Book[tempList.size()]);
        System.out.println("-- " + currentBookList.length);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GridView gridView = (GridView)findViewById(R.id.gridview);
        //booksAdapter = new BooksAdapter(this,books);
        //gridView.setAdapter(booksAdapter);

        favoritedBooks = new ArrayList();

        list_fragment = R.id.list_fragment;
        category_frags = new Fragment[]{new AllFragment(),new ChildFragment(), new DndFragment(), new TextBookFragment()};
        category_chooser = (Spinner)findViewById(R.id.category_chooser);

        spliceBooks(Book.CATEGORY.All);
        getFragmentManager().beginTransaction().replace(list_fragment,category_frags[0]).commit();

        category_chooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Book.CATEGORY c = Book.CATEGORY.All;
                switch(position){
                    case 1: c = Book.CATEGORY.Child;
                        break;
                    case 2: c = Book.CATEGORY.DnD;
                        break;
                    case 3: c = Book.CATEGORY.TEXT_BOOK;
                        break;
                }
                spliceBooks(c);
                getFragmentManager().beginTransaction().replace(list_fragment,category_frags[position]).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView parent, View view, int position, long id){
//                Book book = books[position];
//                book.toggleFavorite();
//                if(book.getIsFavorite()) favoritedBooks.add(book);
//                else favoritedBooks.remove(book);
//                booksAdapter.notifyDataSetChanged();
//            }
//        });

    }//create

     @Override
     protected void onSaveInstanceState(Bundle outState){
         super.onSaveInstanceState(outState);

         final ArrayList<Integer> favoritedBookNames = new ArrayList<>();
         for (Book book : favoritedBooks) {
             favoritedBookNames.add(book.getName());
         }

         outState.putIntegerArrayList(favoritedBookNamesKey, favoritedBookNames);
     }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final ArrayList<Integer> favoritedBookNames =
                savedInstanceState.getIntegerArrayList(favoritedBookNamesKey);

        for(Book book: books){
            if(favoritedBookNames.contains(book.getName())){
                book.setIsFavorite(true);
                favoritedBooks.add(book);
            }
        }

        for (int bookName : favoritedBookNames) {
            for (Book book : books) {
                if (book.getName() == bookName) {
                    book.setIsFavorite(true);
                    break;
                }
            }
        }

    }

}
