package br.pucpr.appdev.prescript.model

 data class Medicine(
     var identificador: String,
     /*val imageMedicine: String,*/
     var nameMedicine: String,
     var labName: String,
     var activePrinciple: String,
     var quantity: String
 )