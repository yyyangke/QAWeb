package com.yangke.model;

import org.springframework.stereotype.Component;

/**
 * Created by yangke on 16/8/19.
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public HostHolder() {

    }
    public User getUser() {
        return users.get();
    }

    public  void setUser(User user) {
        users.set(user);
    }
    public void clear() {
        users.remove();
    }
}
