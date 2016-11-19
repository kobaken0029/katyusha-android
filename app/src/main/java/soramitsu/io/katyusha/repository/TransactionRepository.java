package soramitsu.io.katyusha.repository;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import soramitsu.io.katyusha.entity.request.TransferRequest;
import soramitsu.io.katyusha.entity.response.ResponseObject;

public interface TransactionRepository {
    Observable<ResponseObject> sendPayment(TransferRequest body);

    interface TransactionService {
        @POST(value = "/asset/operation")
        Observable<ResponseObject> transfer(@Body TransferRequest body);
    }
}
