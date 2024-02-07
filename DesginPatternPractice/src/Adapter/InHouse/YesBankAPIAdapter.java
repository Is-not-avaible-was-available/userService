package Adapter.InHouse;

import Adapter.ThirdParty.YesBankAPI;

public class YesBankAPIAdapter implements BankAdapterAPI{
    private YesBankAPI yesBankAPI;
    public YesBankAPIAdapter(YesBankAPI yesBankAPI){
        this.yesBankAPI = yesBankAPI;
    }
    @Override
    public int getBalance(String accountNumber) {
        return yesBankAPI.displayBalance(accountNumber);
    }

    @Override
    public void transferFunds(String fromAccount, String toAccount, int amount) {
        yesBankAPI.sendMoney(fromAccount, toAccount, amount);
    }
}
