package gbcorp.c362.gemvault.pro.di

import androidx.room.Room
import gbcorp.c362.gemvault.pro.data.database.GVLTDatabase
import org.koin.dsl.module

private const val DB_NAME = "gvlt_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = GVLTDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<GVLTDatabase>().cartItemDao() }

    single { get<GVLTDatabase>().orderDao() }
}