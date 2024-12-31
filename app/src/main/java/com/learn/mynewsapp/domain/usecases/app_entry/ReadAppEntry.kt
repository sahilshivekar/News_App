package com.learn.mynewsapp.domain.usecases.app_entry

import com.learn.mynewsapp.domain.repository.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}