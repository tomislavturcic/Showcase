package com.example.tt.showcase.ui.users.models;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 *
 * UI Model for list of users. Contains only UI-relevant data.
 */

public class UserItem {

    private final int id;
    private final String loginName;
    private final String avatarUrl;

    public UserItem(int id, String login, String avatarUrl) {
        this.id = id;
        this.loginName = login;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserItem userItem = (UserItem) o;

        if (id != userItem.id) return false;
        if (loginName != null ? !loginName.equals(userItem.loginName) : userItem.loginName != null) return false;
        return avatarUrl != null ? avatarUrl.equals(userItem.avatarUrl) : userItem.avatarUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        return result;
    }
}
