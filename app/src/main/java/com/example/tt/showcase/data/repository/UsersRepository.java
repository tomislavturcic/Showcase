package com.example.tt.showcase.data.repository;

import com.example.tt.showcase.data.network.models.UserDTO;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by TT on 11.3.2018..
 * Showcase
 *
 * Repository that handles user requests and paging.
 */

public interface UsersRepository {

    /**
     * Gets the first page, resetting any previous paging in the process.
     */
    Single<List<UserDTO>> getFirstPage();

    /**
     * Gets the next page if available.
     */
    Single<List<UserDTO>> getNextPage();

    /**
     * Returns true if there are additional pages remaining.
     */
    boolean canLoadMore();

}
