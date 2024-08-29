package br.pucpr.appdev.prescript

import com.google.firebase.messaging.FirebaseMessagingService

class PushNotficationService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    
}