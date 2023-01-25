package com.mongodb.pagonxtandroiddemo


import io.realm.annotations.RealmClass
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

@RealmClass(embedded = true)
class Contacts : EmbeddedRealmObject,RealmObject {

    var contactData: String? = ""
    var contactType: String? = ""


}
