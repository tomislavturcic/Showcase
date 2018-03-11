package com.example.tt.showcase.mapper;

import android.support.annotation.NonNull;

import com.example.tt.showcase.data.network.models.UserDTO;
import com.example.tt.showcase.data.network.models.UserDetailsDTO;
import com.example.tt.showcase.ui.user_details.models.UserDetailsItem;
import com.example.tt.showcase.ui.users.models.UserItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 *
 * Maps from network objects to UI or domain objects.
 */

public class UsersMapper {

    @NonNull
    public static List<UserItem> fromUsersDTO(List<UserDTO> users){
        List<UserItem> items = new ArrayList<>();
        for (UserDTO user : users) {
            items.add(new UserItem(user.getId(), user.getLogin(), user.getAvatarUrl()));
        }
        return items;
    }

    @NonNull
    public static UserDetailsItem fromUserDetailsDTO(UserDetailsDTO userDetails){
        // Map to user details..

        return new UserDetailsItem();
    }
}
