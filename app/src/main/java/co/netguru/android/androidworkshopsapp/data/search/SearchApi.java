package co.netguru.android.androidworkshopsapp.data.search;

import co.netguru.android.androidworkshopsapp.data.search.model.QuestionList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("search")
    Call<QuestionList> getQuestions(@Query("order")String order, @Query("sort")String sort,
                                    @Query("intitle") String searchQuery);
}
