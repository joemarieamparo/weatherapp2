package com.example.weatherapp.data

import android.content.Context
import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/**
 * This class handles all the db functions
 */

@Parcelize
@Entity
data class City(@PrimaryKey(autoGenerate = true) var id: Int = 0) : Parcelable {
    var area: String = ""
    var region: String = ""
    var country: String = ""
    var lat: String = ""
    var lon: String = ""
    @Ignore var weatherIconUrl: String = ""
    @Ignore var weatherDesc: String = ""
    @Ignore var humidity: String = ""
    @Ignore var temp: String = ""
}

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @get:Query("SELECT * FROM city")
    val cities: List<City>

    @Query("SELECT * FROM city WHERE lat= :lat AND lon= :lon")
    fun getCity(lat: String, lon: String) : City
}

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}

private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "city_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
