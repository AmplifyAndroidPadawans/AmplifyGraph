package com.example.amplifygraph

import android.app.Application
import android.util.Log

import com.amplifyframework.AmplifyException

import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify


class AmplifyGraphApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)
            Log.i("AmplifyGraph", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("AmplifyGraph", "Could not initialize Amplify", error)
        }
    }
}