package com.mongodb.pagonxtandroiddemo


import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

class Customer : RealmObject {

    @PrimaryKey
    var _id: ObjectId? = null

    var country: String? = ""
    var documentNumber: String? = ""
    var accountsList: RealmList<Accounts> = realmListOf<Accounts>()
    var addressList: RealmList<Addresses> = realmListOf<Addresses>()

    var agent: String? = ""
    var cmc: String? = ""
    var companyAdditionalDetail: Double? = 0.0
    var companyEndDate: Date? = Date()
    var companyId: String? = ""
    var companyName: String? = ""
    var companyStartDate: Date? = Date()
    var contactDataMOBI: String? = ""
    var contactDataMail: String? = ""
    var contactList: RealmList<Contacts> = realmListOf<Contacts>()
    var countryDocument: String? = ""
    var countryIncorporation: String? = ""
    var documentList: RealmList<Documents> = realmListOf<Documents>()



    var documentType: String? = "";
    var entity: String? = "";
    var firstName: String? = "";
    var internalRepresentUser: String? = "";
    var lastName: String? = "";
    var relationshipType: String? = "";
    var segmentLocal: String? = "";
    var segmentTypeGlobal: String? = "";
    var segmentTypeLocal: String? = "";
    var tradeName: String? = "";
    var typeDisposition: String? = "";

    //Used by the adapter for the list in the SecondFragment!
    override fun toString(): String {
        return "_id: $_id \nTrade Name: $tradeName\nCompany Name: $companyName";
    }
}
