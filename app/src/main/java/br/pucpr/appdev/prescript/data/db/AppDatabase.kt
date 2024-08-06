package br.pucpr.appdev.prescript.data.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.pucpr.appdev.prescript.data.db.dao.MedicineDao
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity

@Database(entities = [MedicineEntity::class], version =2)
abstract class AppDatabase: RoomDatabase() {

    abstract val medicineDao: MedicineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if(instance == null)
                {
                    instance = Room.databaseBuilder(context,AppDatabase::class.java, "app_database").fallbackToDestructiveMigration().build()


                }
                return instance
            }
        }
    }
}