package gbcorp.c362.gemvault.pro.di

import androidx.room.Room
import gbcorp.c362.gemvault.pro.data.database.GMVTDatabase
import org.koin.dsl.module

private const val DB_NAME = "gmvt_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = GMVTDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<GMVTDatabase>().cartItemDao() }

    single { get<GMVTDatabase>().orderDao() }
}