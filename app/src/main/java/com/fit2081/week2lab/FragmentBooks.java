package com.fit2081.week2lab;
import static android.content.Context.MODE_PRIVATE;

//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class FragmentBooks extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerAdaptBooks recyclerAdaptBooks;
    EditText userID, bookName, bookISBN, bookAuthor, bookDescription, bookPrice, bookPriceTabulated;
    Button tabulateButton;
    private static final String BOOKNAME_KEY = "bookName";
    private static final String BOOKPRICE_KEY = "bookPrice";
    private static final String USERID_KEY = "userID";
    private static final String BOOKISBN_KEY = "bookISBN";
    private static final String BOOKAUTHOR_KEY = "bookAuthor";
    private static final String BOOKDESCRIPTION_KEY = "bookDescription";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private BookViewModel bookViewModel;
    public FragmentBooks() {
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //initialise MessageBroadcastReceiver
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
//        BroadcastHandler messageBroadcastHandler = new BroadcastHandler(); //initialise MessageBroadcastReceiver
//        registerReceiver(messageBroadcastHandler, new IntentFilter(ListenerSMS.SMS_FILTER));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate layout of FragmentBooks (this)
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        bookName = view.findViewById(R.id.bookName);
        bookPrice = view.findViewById(R.id.bookPrice);
        userID = view.findViewById(R.id.userID);
        bookISBN = view.findViewById(R.id.bookISBN);
        bookAuthor = view.findViewById(R.id.bookAuthor);
        bookDescription = view.findViewById(R.id.bookDescription);
        bookPriceTabulated = view.findViewById(R.id.bookPriceTabulated);

        tabulateButton = view.findViewById(R.id.bookButtonTabulate);
        tabulateButton.setOnClickListener(view1 -> bookReturnPrice()); //init taxes button
        sharedPreferences = getActivity().getSharedPreferences("books", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        if (savedInstanceState == null) {
            bookFieldLoad(); //if nothing in SaveInstanceState (temp cache), pull from SharedPreferences
        }
        recyclerView = view.findViewById(R.id.bookList); //find <RecyclerView /> in fragment_books.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //basically ListView() but recycles
        recyclerAdaptBooks = new RecyclerAdaptBooks(); //connect with RecyclerViewMaker.java class file
        recyclerView.setAdapter(recyclerAdaptBooks); //pair <RecyclerView /> and RecyclerViewMaker.java

        //testing everything works with Recycler Adapter (NOT VIA DAO)
        Book newBook = new Book("33979375", "Writings 1997-2003", "9780995455061", "CCRU", "Scribings", "49.99");
        recyclerAdaptBooks.addBook(newBook);

        //init BookViewModel to connect FragmentBooks to BookDB (I don't know how all this shit works, just following the lectures)
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(getViewLifecycleOwner(), newData -> { //monitor database for changes
            recyclerAdaptBooks.removeBooks("all"); //clear ArrayList of RecyclerView
            for(int i = 0; i < newData.size(); i++){ //iterate through whole database
                recyclerAdaptBooks.addBook(newData.get(i)); //add each db entry to Recycler ArrayList
            }
        });
        return view;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.books_appbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int drawerOptionAdd = R.id.drawerAdd;
        final int drawerOptionRemoveLast = R.id.drawerRemoveLast;
        final int drawerOptionRemoveAll = R.id.drawerRemoveAll;
        final int clear = R.id.booksTopBarOptionsClear;
        final int load = R.id.booksTopBarOptionsLoad;
        final int reverse = R.id.booksTopBarOptionsReverse;
        switch (item.getItemId()) {
            case drawerOptionAdd:
                bookFieldSave();
                return true;
            case drawerOptionRemoveLast:
                recyclerAdaptBooks.removeBooks("last");
                return true;
            case drawerOptionRemoveAll:
                recyclerAdaptBooks.removeBooks("all");
                return true;
            case clear:
                bookFieldClear();
                return true;
            case load:
                bookFieldLoad();
                return true;
//            case reverse:
//                Collections.reverse(bookListArray);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void bookReturnPrice() { //calculate "tax" on button press
        Double price = Double.parseDouble(bookPrice.getText().toString());
        price = price * 1.1;
        String priceAsString = String.format("%.2f", price);
        bookPriceTabulated.setText(priceAsString);
    }
    public void bookFieldSave() { //save to DISK
        EditText[] fields = {userID, bookName, bookISBN, bookAuthor, bookDescription, bookPrice};
        Book newBook = new Book("","","","","","");
        for (int i=0; i < fields.length; i++){
            EditText field = fields[i];
            String text = field.getText().toString();
            switch (i){
                case 0:
                    newBook.setUserID(text);
                case 1:
                    newBook.setBookName(text);
                case 2:
                    newBook.setBookISBN(text);
                case 3:
                    newBook.setBookAuthor(text);
                case 4:
                    newBook.setBookDescription(text);
                case 5:
                    newBook.setBookPrice(text);
            }
        }
        recyclerAdaptBooks.addBook(newBook); //save to RECYCLER ARRAYLIST
        bookViewModel.addBook(newBook); //save to DATABASE
        sharedPreferencesEditor.putString(USERID_KEY, newBook.getUserID());
        sharedPreferencesEditor.putString(BOOKNAME_KEY, newBook.getBookName());
        sharedPreferencesEditor.putString(BOOKISBN_KEY, newBook.getBookISBN());
        sharedPreferencesEditor.putString(BOOKAUTHOR_KEY, newBook.getBookAuthor());
        sharedPreferencesEditor.putString(BOOKDESCRIPTION_KEY, newBook.getBookDescription());
        sharedPreferencesEditor.putString(BOOKPRICE_KEY, newBook.getBookPrice());
        sharedPreferencesEditor.apply(); //save to SHARED PREFERENCES
    }

    public void bookFieldLoad() { //load from DISK
        bookName.setText(sharedPreferences.getString(BOOKNAME_KEY, ""));
        bookPrice.setText(sharedPreferences.getString(BOOKPRICE_KEY, ""));
        userID.setText(sharedPreferences.getString(USERID_KEY, ""));
        bookISBN.setText(sharedPreferences.getString(BOOKISBN_KEY, ""));
        bookAuthor.setText(sharedPreferences.getString(BOOKAUTHOR_KEY, ""));
        bookDescription.setText(sharedPreferences.getString(BOOKDESCRIPTION_KEY, ""));
    }

    public void bookFieldClear() { //clear all fields
        userID.getText().clear();
        bookName.getText().clear();
        bookISBN.getText().clear();
        bookAuthor.getText().clear();
        bookDescription.getText().clear();
        bookPrice.getText().clear();
    }
}
//class BroadcastHandler extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent){
//        String message = intent.getStringExtra(ListenerSMS.SMS_MSG_KEY);
//        String[] messageSegmented = message.split("|");
//        if (messageSegmented[6].equals("true")) {
//            int rangeLow = 1;
//            int rangeHigh = 100; //nextInt() is "rangeHigh + 1" to include 100
//            messageSegmented[0] = String.valueOf(ThreadLocalRandom.current().nextInt(rangeLow, rangeHigh + 1));
//        } else if (messageSegmented[6].equals("false")) {
//            messageSegmented[0] = String.valueOf(101);
//        }
//        userID.setText(messageSegmented[0]);
//        bookName.setText(messageSegmented[1]);
//        bookISBN.setText(messageSegmented[2]);
//        bookAuthor.setText(messageSegmented[3]);
//        bookDescription.setText(messageSegmented[4]);
//        bookPrice.setText(String.format("%.2f", Double.parseDouble(messageSegmented[5]))); //2d.p. to look like currency
//        //etc
//        SharedPreferences preferences = getActivity().getSharedPreferences("books", MODE_PRIVATE);
//        String toastUserID = preferences.getString(USERID_KEY, "");
//        //Toast.makeText(MainActivity.this, "Message received: "+toastUserID, Toast.LENGTH_LONG).show();
//    }
//}