import android.app.Application
import com.moviles.vinilos.database.VinylsRoomDatabase

class VinylsApplication: Application()  {
    val database by lazy { VinylsRoomDatabase.getDatabase(this) }
}