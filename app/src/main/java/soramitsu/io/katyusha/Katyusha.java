package soramitsu.io.katyusha;

import android.app.Application;

import io.soramitsu.irohaandroid.Iroha;
import io.soramitsu.irohaandroid.KeyPair;
import soramitsu.io.katyusha.data.repository.UserInfoRepositoryImpl;
import soramitsu.io.katyusha.domain.entity.UserInfo;
import soramitsu.io.katyusha.domain.repository.UserInfoRepository;

public class Katyusha extends Application {
    private KeyPair keyPair;
    private UserInfo userInfo;

    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        keyPair = Iroha.createKeyPair();
        userInfo = userInfoRepository.getUserInfo("hoge").single().toBlocking().single();
    }

    public String getPublicKey() {
        return keyPair.getPublicKey();
    }

    public String getPrivateKey() {
        return keyPair.getPrivateKey();
    }

    public UserInfo getUserInfo() {
        if (userInfo.alias.equals("makoto")) {
            userInfo.image = getDrawable(R.drawable.takemiya);
        } else {
            userInfo.image = getDrawable(R.drawable.tony);
        }
        return userInfo;
    }
}
