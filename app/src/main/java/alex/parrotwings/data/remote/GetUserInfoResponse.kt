package alex.parrotwings.data.remote

import alex.parrotwings.data.remote.dtos.UserInfoDto
import com.google.gson.annotations.SerializedName

data class GetUserInfoResponse(@SerializedName("user_info_token") var user: UserInfoDto)