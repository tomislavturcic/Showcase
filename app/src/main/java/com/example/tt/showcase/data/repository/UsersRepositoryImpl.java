package com.example.tt.showcase.data.repository;

import com.example.tt.showcase.data.network.ApiService;
import com.example.tt.showcase.data.network.models.UserDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by TT on 11.3.2018..
 * Showcase
 */

public class UsersRepositoryImpl implements UsersRepository {

    private static final int PAGE_SIZE = 30;

    private final ApiService apiService;

    private boolean canLoadMore = true;
    private int nextSince = 0;

    @Inject
    public UsersRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<UserDTO>> getFirstPage() {
        canLoadMore = true;
        nextSince = 0;
        return getNextPage();
    }

    @Override
    public Single<List<UserDTO>> getNextPage() {
        if(!canLoadMore || nextSince == -1){
            return Single.just(new ArrayList<>());
        }

        return apiService.getUsers(PAGE_SIZE, nextSince)
                .doOnSuccess(response -> {
                    // Get pagination data from response headers
                    List<String> linkHeaders = response.headers().values("Link");
                    if(linkHeaders != null && !linkHeaders.isEmpty()){
                        createPaginationInfoFromLinkHeader(linkHeaders.get(0));
                    } else{
                        nextSince = -1;
                        canLoadMore = false;
                    }
                })
                .map(Response::body);      // Simply return body
    }

    @Override
    public boolean canLoadMore() {
        return canLoadMore;
    }

    /**
     * Grabs paging information from the link header.
     */
    private void createPaginationInfoFromLinkHeader(String linkHeader){
        // Link header example: <https://api.github.com/users?per_page=100&since=135>; rel="next", <https://api.github.com/users{?since}>; rel="first"
        String[] links = linkHeader.split(",");
        String link1 = links[0].trim();
        //String link2 = links[1].trim();

        int sinceStartIndex = link1.indexOf("since=") + "since=".length();
        int sinceEndIndex = link1.indexOf(">");
        String sinceValue = link1.substring(sinceStartIndex, sinceEndIndex);
        nextSince = Integer.parseInt(sinceValue);

        int relStartIndex = link1.indexOf("rel=") + "rel=".length();
        String relValue = link1.substring(relStartIndex, link1.length());
        canLoadMore = !relValue.equals("last");
    }
}
