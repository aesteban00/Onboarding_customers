package com.mongodb.pagonxtandroiddemo


import io.realm.annotations.RealmClass
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

@RealmClass(embedded = true)
class Addresses : EmbeddedRealmObject,RealmObject {

    var country: String? = ""
    var postCodeId: String? = ""
    var province: String? = ""
    var regionId: String? = ""
    var state: String? = ""
    var streetBuildingId: String? = ""
    var streetName: String? = ""
    var townName: String? = ""
}
