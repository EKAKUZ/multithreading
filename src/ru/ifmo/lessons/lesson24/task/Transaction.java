package ru.ifmo.lessons.lesson24.task;

public class Transaction implements Runnable{
    private Account src; // с какого аккаунта осуществлять перевод
    private Account dst; // на какой аккаунт осуществлять перевод
    private int money; // сколько переводить

    public Transaction(Account src, Account dst, int money) {
        this.src = src;
        this.dst = dst;
        this.money = money;
    }

    @Override
    public void run() {
        // TODO перевод денежных средств со счета src на счет dst в количестве money
        /*
        if (src.getId() < dst.getId()) {
            synchronized (src) {
                synchronized (dst) {
                    if (src.getBalance() < money) {
                        dst.setBalance(dst.getBalance() + money);
                        src.setBalance(src.getBalance() - money);
                    }
                }
            }
        }
        else {
            synchronized (dst) {
                synchronized (src) {
                    if (src.getBalance() < money) {
                        dst.setBalance(dst.getBalance() + money);
                        src.setBalance(src.getBalance() - money);
                    }
                }
            }
        }*/
        int srcmoney;
        synchronized (src) {
            if (src.getBalance() < money) {
                srcmoney = src.getBalance();
                src.setBalance(src.getBalance() - money);
            }
        }
        synchronized (dst) {
            dst.setBalance(dst.getBalance() + money);
        }

    }
}