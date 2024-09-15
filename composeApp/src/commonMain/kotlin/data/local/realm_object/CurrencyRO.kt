package data.local.realm_object

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

@Serializable
open class CurrencyRO: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var code: String = ""
    var value: Double = 0.0
}