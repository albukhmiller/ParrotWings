package alex.parrotwings.data.remote

import alex.parrotwings.data.remote.dtos.UserInfoDto

/**
 * Created by alex on 23.07.2018.
 */
data class GetReceiversResponse(var foundUsers: ArrayList<UserInfoDto>) {
}