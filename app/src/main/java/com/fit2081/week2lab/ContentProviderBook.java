package com.fit2081.week2lab;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProviderBook extends ContentProvider {
    public static final String CONTENT_AUTHORITY = "fit2093.app.oscar";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final int MULTIPLE_ROWS_TASKS = 1;
    private static final int SINGLE_ROW_TASKS = 2;
    BookDB db;
    private static final UriMatcher sUriMatcher = createUriMatcher();
    public ContentProviderBook() {
    }
    private static UriMatcher createUriMatcher(){
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, BookDB.BOOK_DB_NAME, MULTIPLE_ROWS_TASKS);
        uriMatcher.addURI(authority, BookDB.BOOK_DB_NAME + "/#", SINGLE_ROW_TASKS);
        return uriMatcher;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int deletionCount;
        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(BookDB.BOOK_DB_NAME, selection, selectionArgs);
        return deletionCount;
    }
    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(BookDB.BOOK_DB_NAME, 0, values);
        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db = BookDB.getDatabase(getContext());
        return true;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(BookDB.BOOK_DB_NAME);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);
        final Cursor cursor = db.getOpenHelper().getReadableDatabase().query(query, selectionArgs);
        return cursor;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(BookDB.BOOK_DB_NAME, 0, values, selection, selectionArgs);
        return updateCount;
    }
}