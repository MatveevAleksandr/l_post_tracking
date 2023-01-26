package com.example.l_post_tracking.di

import com.example.l_post_tracking.repository.OrderRepository
import com.example.l_post_tracking.repository.OrderRepositoryImpl
import com.example.l_post_tracking.storage.APIOrderStorageImpl
import com.example.l_post_tracking.storage.OrderStorage
import org.koin.dsl.module

val dataModule = module {
    single<OrderStorage> { APIOrderStorageImpl() }
    single<OrderRepository> { OrderRepositoryImpl(storage = get()) }
}