package soramitsu.io.katyusha.data.repository;

import java.util.List;

import rx.Observable;
import soramitsu.io.katyusha.domain.entity.request.TransferRequest;
import soramitsu.io.katyusha.domain.entity.response.ResponseObject;
import soramitsu.io.katyusha.domain.repository.RightRepository;
import soramitsu.io.katyusha.entity.Right;

public class RightRepositoryImpl extends RetrofitRepository implements RightRepository {

    private RightService rightService;

    public RightRepositoryImpl() {
        super(buildClient());
        rightService = retrofit.create(RightService.class);
    }

    @Override
    public Observable<ResponseObject> sendRight(TransferRequest body) {
        // mock
        ResponseObject responseObject = new ResponseObject();
        responseObject.status = 200;
        responseObject.message = "Successful send a payment!";
        return Observable.just(responseObject);
    }

    @Override
    public Observable<List<Right>> getRights(String uuid) {
        // mock
        Right right = new Right();
        right.initializeData();
        return Observable.just(right.getRights());
    }
}
