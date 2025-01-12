package sample;

import util.NetworkUtil;

public class WriteThreadMain implements Runnable{
    private Thread thr;
    private NetworkUtil networkUtil;
    LoginInformation loginInfo;

    public WriteThreadMain(NetworkUtil networkUtil, LoginInformation loginInfo) {
        this.networkUtil = networkUtil;
        this.loginInfo = loginInfo;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        /*try{
            while(true) {
                networkUtil.write(loginInfo);
            }
        } catch (Exception e) {
            System.out.println("WriteThreadMainLogin ERROR:" + e);
        }*/
    }
}
