package soramitsu.io.katyusha;

public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Override
    public UserInfo getUserInfo(String uuid) {
        // mock
        UserInfo userInfo = new UserInfo();
        userInfo.alias = "makoto";
        userInfo.amount = 1000;
        userInfo.email = "makoto@soramitsu.co.jp";
        userInfo.age = 24;
        userInfo.gender = "male";
        return userInfo;
    }
}
