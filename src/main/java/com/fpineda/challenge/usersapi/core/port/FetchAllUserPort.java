package com.fpineda.challenge.usersapi.core.port;

import java.util.List;
import com.fpineda.challenge.usersapi.core.model.User;

public interface FetchAllUserPort {

    List<User> fetchAll();

}
