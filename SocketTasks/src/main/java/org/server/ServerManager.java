package org.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ServerManager {

    private List<UserTCPManager> userTCPManagerList = new ArrayList<>();
    private List<Thread> userThreads = new ArrayList<>();
    private UserUDPManager userUDPManager;
    private Semaphore semaphore = new Semaphore(1,true);

    public ServerManager() {

    }

    public void addUserToChat(UserTCPManager userTCPManager, Thread thread){
        try {
            this.semaphore.acquire();
            this.userTCPManagerList.add(userTCPManager);
            this.userThreads.add(thread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            this.semaphore.release();
        }
    }

    public void removeUserFromChat(UserTCPManager userTCPManager, Thread thread){
        try {
            this.semaphore.acquire();
            this.userTCPManagerList.remove(userTCPManager);
            this.userThreads.remove(thread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            this.semaphore.release();
        }
    }

    public boolean isUserExist(String nickname){

        boolean value = false;
        try {
            this.semaphore.acquire();

            for(UserTCPManager user: this.getUserManagerList()){

                if(nickname.equals(user.getNickname())){
                    value = true;
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            this.semaphore.release();
        }

        return value;
    }


    public void closeConnectedClients(){

        try {
            this.semaphore.acquire();

            for(UserTCPManager user: this.getUserManagerList()){

                user.endConnection();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
        finally {
            this.semaphore.release();
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public List<UserTCPManager> getUserManagerList() {
        return userTCPManagerList;
    }

    public List<Thread> getUserThreads() {
        return userThreads;
    }

    public void setUserUDPManager(UserUDPManager userUDPManager) {
        this.userUDPManager = userUDPManager;
    }

    public UserUDPManager getUserUDPManager() {
        return userUDPManager;
    }
}
