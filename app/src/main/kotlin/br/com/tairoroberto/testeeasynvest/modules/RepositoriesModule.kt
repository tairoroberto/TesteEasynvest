package br.com.tairoroberto.testeeasynvest.modules

import android.content.res.AssetManager

import com.google.gson.Gson

import javax.inject.Singleton


import br.com.tairoroberto.testeeasynvest.repositories.CellRepository
import br.com.tairoroberto.testeeasynvest.repositories.CellRepositoryImpl
import br.com.tairoroberto.testeeasynvest.repositories.FundRepository
import br.com.tairoroberto.testeeasynvest.repositories.FundRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun providesCellRepository(assetManager: AssetManager, gson: Gson): CellRepository {
        return CellRepositoryImpl(assetManager, gson)
    }

    @Provides
    @Singleton
    fun providesFundReopsitory(assetManager: AssetManager, gson: Gson): FundRepository {
        return FundRepositoryImpl(assetManager, gson)
    }

}
