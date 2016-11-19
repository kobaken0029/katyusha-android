package soramitsu.io.katyusha.data.repository;

import rx.Observable;
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
        userInfo.alias = "makoto";
        userInfo.amount = 1000;
        userInfo.email = "makoto@soramitsu.co.jp";
        userInfo.age = 24;
        userInfo.gender = "male";
        return Observable.just(userInfo);
    }
}
