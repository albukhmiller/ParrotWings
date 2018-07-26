package alex.parrotwings.models

import com.google.gson.annotations.SerializedName

data class AuthInfo(var email: String,
                    var password: String,
                    @SerializedName("username")
                    var name: String = "",
                    @SerializedName("id_token")
                    var idToken: String = "")