package exp.weather.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_CURRENT = "CURRENT_WEATHER_TABLE";
    public static final String TABLE_NAME_FORECAST = "FORECAST_WEATHER_TABLE";

    public static final String TEMP = "TEMP";
    public static final String SKY = "SKY";
    public static final String CITY = "CITY";
    public static final String WIND = "WIND";
    public static final String HUMIDITY = "HUMIDITY";
    public static final String A_PRESS = "A_PRESS";

    public static final String F_TABLE_DATE = "date";
    public static final String F_TEMP = "temp";
    public static final String F_SKY = "sky";

    public DBHelper(Context context)
    {
        super(context, "weather", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_CURRENT +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CITY + " TEXT," +
                TEMP + " TEXT," +
                SKY + " TEXT," +
                WIND + " REAL," +
                A_PRESS + " REAL," +
                HUMIDITY + " NUMBER );");

        sqLiteDatabase.execSQL("create table " + TABLE_NAME_FORECAST +
                "(_id integer primary key autoincrement, " +
                F_TABLE_DATE + " text, " +
                F_TEMP + " text, " +
                F_SKY + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
