package soramitsu.io.katyusha.repository;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import soramitsu.io.katyusha.entity.UserInfo;

public interface UserInfoRepository {
    Observable<UserInfo> getUserInfo(String uuid);

    interface UserInfoService {
        @GET(value = "/account")
        Observable<UserInfo> userInfo(@Query("uuid") String uuid);
    }
}
