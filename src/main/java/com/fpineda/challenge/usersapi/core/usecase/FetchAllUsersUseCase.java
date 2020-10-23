package com.fpineda.challenge.usersapi.core.usecase;

import java.util.List;
import com.fpineda.challenge.usersapi.core.model.User;

public interface FetchAllUsersUseCase {

    List<User> fetchAll();

}
