package com.ksw.recipeclone.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Created by KSW on 2021-02-01
 */

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
){
    val remoteDataSource = remoteDataSource
    val localDataSource = localDataSource
}