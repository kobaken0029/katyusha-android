package soramitsu.io.katyusha.domain.repository;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import soramitsu.io.katyusha.domain.entity.request.TransferRequest;
import soramitsu.io.katyusha.domain.entity.response.ResponseObject;
import soramitsu.io.katyusha.domain.entity.Right;

public interface RightRepository {
    Observable<ResponseObject> sendRight(TransferRequest body);

    Observable<List<Right>> getRights(String uuid);

    interface RightService {
        @POST(value = "/asset/operation")
        Observable<ResponseObject> transfer(@Body TransferRequest body);

        @GET(value = "/asset/list/{DOMAIN}")
        Observable<List<Right>> assets(@Body TransferRequest body);
    }
}
