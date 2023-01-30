package com.example.l_post_tracking.di


import com.example.l_post_tracking.repository.OrderRepository
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideFindByOrderOrTrackNumUseCase(repository: OrderRepository): FindByOrderOrTrackNumUseCase {
        return FindByOrderOrTrackNumUseCase(orderRepository = repository)
    }

    @Provides
    fun provideFindByPhoneNumUseCase(repository: OrderRepository): FindByPhoneNumUseCase {
        return FindByPhoneNumUseCase(orderRepository = repository)
    }
}