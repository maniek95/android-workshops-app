package co.netguru.android.androidworkshopsapp.data.search;

import co.netguru.android.androidworkshopsapp.data.network.NetworkProvider;
import co.netguru.android.androidworkshopsapp.data.network.model.Order;
import co.netguru.android.androidworkshopsapp.data.network.model.Sort;
import co.netguru.android.androidworkshopsapp.data.search.model.QuestionList;
import retrofit2.Call;

public class SearchController {

    private final SearchApi searchApi;

    public SearchController() {
        searchApi = NetworkProvider.provideSearchApi();
    }

    public Call<QuestionList> getQuestionsFromServer(String searchQuery) {
        return searchApi.getQuestions(Order.DESC.getValue(), Sort.ACTIVITY.getValue(), searchQuery);
    }
}
