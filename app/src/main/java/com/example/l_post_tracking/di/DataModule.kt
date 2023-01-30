package com.example.l_post_tracking.di

import com.example.l_post_tracking.repository.OrderRepository
import com.example.l_post_tracking.repository.OrderRepositoryImpl
import com.example.l_post_tracking.storage.APIOrderStorageImpl
import com.example.l_post_tracking.storage.OrderStorage
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideOrderStorage(): OrderStorage {
        return APIOrderStorageImpl()
    }

    @Provides
    fun provideOrderRepository(storage: OrderStorage): OrderRepository {
        return OrderRepositoryImpl(storage = storage)
    }
}