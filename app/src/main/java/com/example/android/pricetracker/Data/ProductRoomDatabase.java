package com.example.android.pricetracker.Data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by e.delcampo on 04/04/2018.
 */


//Annotate the class to be a Room database, declare the entities that belong in the database and
// set the version number. Listing the entities will create tables in the database.

@Database(entities = {Products.class}, version = 1)
public abstract class ProductRoomDatabase extends RoomDatabase{

    // Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao.
    public abstract ProductDao productDao();


    //Make the ProductRoomDatabase a singleton to prevent having multiple instances of the database opened at the same time.

    private static ProductRoomDatabase INSTANCE;

    public static ProductRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ProductRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class, "products_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProductDao mDao;

        PopulateDbAsync(ProductRoomDatabase db) {
            mDao = db.productDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Products products = new Products("prova", "product","5 stars", "www.image.url", "EUR 10,00");
            mDao.insert(products);
            return null;
        }
    }
}
