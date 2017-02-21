package com.grouptwosoftworks.chukbooks;

/**
 * Created by MSI DRAGON on 2/13/2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class BooksAdapter extends BaseAdapter{


        private final Context mContext;
        private Book[] books;


        public BooksAdapter(Context context, Book[] books) {
            this.mContext = context;
            this.books = books;
        }

        @Override
        public int getCount() {
            return books.length;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final Book book = books[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_book, null);

            // 3
            final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
            final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_author);
            final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

            final ViewHolder viewHolder = new ViewHolder(nameTextView,authorTextView,imageView,imageViewFavorite);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.V_coverart.setImageResource(book.getImageResource());
        viewHolder.V_name.setText(book.getName());
        viewHolder.V_author.setText(book.getAuthor());
        viewHolder.V_star.setImageResource(book.getIsFavorite() ? R.drawable.ic_remove_shopping_cart_black : R.drawable.ic_add_shopping_cart_black);

        return convertView;
    }

    private class ViewHolder{
        private final TextView V_name;
        private final TextView V_author;
        private final ImageView V_coverart;
        private final ImageView V_star;

        public ViewHolder(TextView v_name, TextView v_author, ImageView v_coverart, ImageView v_star){
            V_name = v_name;
            V_author = v_author;
            V_coverart = v_coverart;
            V_star = v_star;
        }
    }

}
