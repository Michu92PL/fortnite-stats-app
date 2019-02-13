package fortnitestatsapp.service;

import fortnitestatsapp.exceptions.PlayerNotFoundException;
import fortnitestatsapp.model.UserData;

import java.util.List;

public interface UserDataService {

    UserData getUser(String platform, String name) throws PlayerNotFoundException;
    void checkBothUsers(String platform1, String name1, String platform2, String name2) throws PlayerNotFoundException;
    UserData getUser1();
    UserData getUser2();

}
