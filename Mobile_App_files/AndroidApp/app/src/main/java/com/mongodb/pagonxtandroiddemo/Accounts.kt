package com.mongodb.pagonxtandroiddemo


import io.realm.annotations.RealmClass
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

@RealmClass(embedded = true)
class Accounts : EmbeddedRealmObject, RealmObject {

    var status: String? = ""
    var agentAccount: String? = ""
    var accountTypeBba: String? = ""
    var accountIdBba: String? = ""
    var currencyCode: String? = ""
    var balanceAllowed: Boolean? = false
    var transactionsAllowed: Boolean? = false
    var internationalPaymentsAllowed: Boolean? = false


}
