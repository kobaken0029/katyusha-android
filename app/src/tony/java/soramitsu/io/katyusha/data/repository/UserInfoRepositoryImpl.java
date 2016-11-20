package soramitsu.io.katyusha.data.repository;

import rx.Observable;
import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.domain.entity.UserInfo;
import soramitsu.io.katyusha.domain.repository.UserInfoRepository;

public class UserInfoRepositoryImpl extends RetrofitRepository implements UserInfoRepository {

    private UserInfoService userInfoService;

    public UserInfoRepositoryImpl() {
        super(buildClient());
        this.userInfoService = retrofit.create(UserInfoService.class);
    }

    @Override
    public Observable<UserInfo> getUserInfo(String uuid) {
        // mock
        UserInfo userInfo = new UserInfo();
        userInfo.alias = "tony";
        userInfo.amount = 3000;
        userInfo.email = "tony@soramitsu.co.jp";
        userInfo.age = 26;
        userInfo.gender = "male";
        return Observable.just(userInfo);
    }
}
