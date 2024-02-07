package Adapter.InHouse;

public class PhonePay {
    private BankAdapterAPI bankAdapterAPI;
    public PhonePay(BankAdapterAPI bankAdapterAPI){
        this.bankAdapterAPI = bankAdapterAPI;
    }

    public void showBalanceForAccount(String accountNumber){
        bankAdapterAPI.getBalance(accountNumber);
    }
}
