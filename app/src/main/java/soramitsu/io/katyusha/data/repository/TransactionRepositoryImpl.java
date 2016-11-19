package soramitsu.io.katyusha.data.repository;

import rx.Observable;
import soramitsu.io.katyusha.domain.entity.request.TransferRequest;
import soramitsu.io.katyusha.domain.entity.response.ResponseObject;
import soramitsu.io.katyusha.domain.repository.TransactionRepository;

public class TransactionRepositoryImpl extends RetrofitRepository implements TransactionRepository {
    private TransactionService transactionService;

    public TransactionRepositoryImpl() {
        super(buildClient());
        this.transactionService = retrofit.create(TransactionService.class);
    }

    @Override
    public Observable<ResponseObject> sendPayment(TransferRequest body) {
        // mock
        ResponseObject responseObject = new ResponseObject();
        responseObject.status = 200;
        responseObject.message = "Successful send a payment!";
        return Observable.just(responseObject);
    }
}
