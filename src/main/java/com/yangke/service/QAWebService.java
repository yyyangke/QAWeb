package com.yangke.service;

import org.springframework.stereotype.Service;

/**
 * Created by nowcoder on 2016/7/10.
 */
@Service
public class QAWebService {
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }
}
