package com.learn.mynewsapp.domain.usecases.app_entry

import com.learn.mynewsapp.domain.repository.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}