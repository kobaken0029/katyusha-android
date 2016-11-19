package soramitsu.io.katyusha.domain.repository;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import soramitsu.io.katyusha.domain.entity.request.TransferRequest;
import soramitsu.io.katyusha.domain.entity.response.ResponseObject;

public interface TransactionRepository {
    Observable<ResponseObject> sendPayment(TransferRequest body);

    interface TransactionService {
        @POST(value = "/asset/operation")
        Observable<ResponseObject> transfer(@Body TransferRequest body);
    }
}
