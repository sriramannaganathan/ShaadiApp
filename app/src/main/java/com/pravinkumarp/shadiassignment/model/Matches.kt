package com.pravinkumarp.shadiassignment.model


import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.pravinkumarp.shadiassignment.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Matches(
    @SerializedName("info")
    var info: Info?,
    @SerializedName("results")
    var invitations: List<Invitation>
) : Parcelable {
    @Parcelize
    data class Info(
        @SerializedName("page")
        var page: Int,
        @SerializedName("results")
        var results: Int,
        @SerializedName("seed")
        var seed: String,
        @SerializedName("version")
        var version: String
    ) : Parcelable

    @Parcelize
    data class Invitation(
        @SerializedName("cell")
        var cell: String,
        @SerializedName("dob")
        var dob: Dob,
        @SerializedName("email")
        var email: String,
        @SerializedName("gender")
        var gender: String,
        @SerializedName("id")
        var id: Id,
        @SerializedName("location")
        var location: Location,
        @SerializedName("login")
        var login: Login,
        @SerializedName("name")
        var name: Name,
        @SerializedName("nat")
        var nat: String,
        @SerializedName("phone")
        var phone: String,
        @SerializedName("picture")
        var picture: Picture,
        @SerializedName("registered")
        var registered: Registered
    ) : Parcelable, BaseObservable() {

        companion object {
            fun convertFromLocal(data: String): Invitation {
                return Gson().fromJson(data, Invitation::class.java)
            }
        }

        @Bindable
        @SerializedName("status")
        var status: Int = 0
            set(value) {
                field = value
                notifyPropertyChanged(BR.invitation)
            }

        @Bindable
        var statusText: String = ""
            get() {
                return when (status) {
                    1 -> "Member Accepted"
                    2 -> "Member Declined"
                    else -> ""
                }
            }

        @Parcelize
        data class Dob(
            @SerializedName("age")
            var age: Int,
            @SerializedName("date")
            var date: String
        ) : Parcelable

        @Parcelize
        data class Id(
            @SerializedName("name")
            var name: String,
            @SerializedName("value")
            var value: String
        ) : Parcelable

        @Parcelize
        data class Location(
            @SerializedName("city")
            var city: String,
            @SerializedName("coordinates")
            var coordinates: Coordinates,
            @SerializedName("country")
            var country: String,
            @SerializedName("postcode")
            var postcode: String,
            @SerializedName("state")
            var state: String,
            @SerializedName("street")
            var street: Street,
            @SerializedName("timezone")
            var timezone: Timezone
        ) : Parcelable {
            @Parcelize
            data class Coordinates(
                @SerializedName("latitude")
                var latitude: String,
                @SerializedName("longitude")
                var longitude: String
            ) : Parcelable

            @Parcelize
            data class Street(
                @SerializedName("name")
                var name: String,
                @SerializedName("number")
                var number: Int
            ) : Parcelable

            @Parcelize
            data class Timezone(
                @SerializedName("description")
                var description: String,
                @SerializedName("offset")
                var offset: String
            ) : Parcelable
        }

        @Parcelize
        data class Login(
            @SerializedName("md5")
            var md5: String,
            @SerializedName("password")
            var password: String,
            @SerializedName("salt")
            var salt: String,
            @SerializedName("sha1")
            var sha1: String,
            @SerializedName("sha256")
            var sha256: String,
            @SerializedName("username")
            var username: String,
            @SerializedName("uuid")
            var uuid: String
        ) : Parcelable

        @Parcelize
        data class Name(
            @SerializedName("first")
            var first: String,
            @SerializedName("last")
            var last: String,
            @SerializedName("title")
            var title: String
        ) : Parcelable

        @Parcelize
        data class Picture(
            @SerializedName("large")
            var large: String,
            @SerializedName("medium")
            var medium: String,
            @SerializedName("thumbnail")
            var thumbnail: String
        ) : Parcelable

        @Parcelize
        data class Registered(
            @SerializedName("age")
            var age: Int,
            @SerializedName("date")
            var date: String
        ) : Parcelable
    }
}