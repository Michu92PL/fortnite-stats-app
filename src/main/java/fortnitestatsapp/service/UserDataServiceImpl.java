package fortnitestatsapp.service;

import fortnitestatsapp.exceptions.BothPlayersNotFoundException;
import fortnitestatsapp.exceptions.FirstPlayerNotFoundException;
import fortnitestatsapp.exceptions.PlayerNotFoundException;
import fortnitestatsapp.exceptions.SecondPlayerNotFoundException;
import fortnitestatsapp.model.UserData;

public class UserDataServiceImpl implements UserDataService {

    private RestService restService;
    private UserData user1;
    private UserData user2;

    public UserDataServiceImpl() {
        this.restService = new RestService();
    }

    @Override
    public UserData getUser(String platform, String name) throws PlayerNotFoundException {
        return restService.getUserData(platform, name);
    }

    @Override
    public void checkBothUsers(String platform1, String name1, String platform2, String name2) throws PlayerNotFoundException {
        int errorCode = 0;
        try {
            UserData user1 = getUser(platform1, name1);
            this.user1 = user1;
        }
        catch (PlayerNotFoundException e) {
            errorCode += 1;
        }

        wait2Seconds();

        try {
            UserData user2 = getUser(platform2, name2);
            this.user2 = user2;
        }
        catch (PlayerNotFoundException e) {
            errorCode += 2;
        }
        generatePlayerException(errorCode);
    }

    private void generatePlayerException(int errorCode) {
        switch (errorCode) {
            case 3:
                throw new BothPlayersNotFoundException("Players not found!");
            case 2:
                throw new SecondPlayerNotFoundException("Player 2 not found!");
            case 1:
                throw new FirstPlayerNotFoundException("Player 1 not found!");
            default:
        }
    }

    private void wait2Seconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public UserData getUser1() {
        return user1;
    }

    public UserData getUser2() {
        return user2;
    }
}


