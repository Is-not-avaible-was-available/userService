package Adapter.InHouse;

public interface BankAdapterAPI {

    public int getBalance(String accountNumber);

    public void transferFunds(String fromAccount, String toAccount, int amount);
}
