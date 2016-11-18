package soramitsu.io.katyusha;

import android.app.Application;

import soramitsu.io.katyusha.entity.UserInfo;
import soramitsu.io.katyusha.repository.UserInfoRepository;
import soramitsu.io.katyusha.repository.impl.UserInfoRepositoryImpl;

public class Katyusha extends Application {
    private UserInfo userInfo;

    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        userInfo = userInfoRepository.getUserInfo("hoge").single().toBlocking().single();
    }

    public UserInfo getUserInfo() {
        userInfo.image = getDrawable(R.drawable.takemiya);
        return userInfo;
    }
}
