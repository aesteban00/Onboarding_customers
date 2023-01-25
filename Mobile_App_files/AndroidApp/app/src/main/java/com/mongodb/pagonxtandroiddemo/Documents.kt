package com.mongodb.pagonxtandroiddemo


import io.realm.annotations.RealmClass
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

@RealmClass(embedded = true)
class Documents : EmbeddedRealmObject,RealmObject {

    var documentNumber: String? = ""
    var documentType: String? = ""


}
