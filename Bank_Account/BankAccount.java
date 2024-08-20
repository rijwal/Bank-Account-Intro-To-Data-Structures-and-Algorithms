import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccount {
  //Create your BankAccount object here and run your unit tests!
  private static final DecimalFormat df = new DecimalFormat("0.00");
  // instance variable
  String accountNumber;
  int balance;
  int withdrawalFee;
  int annualInterestRate;

  private ArrayList <Transaction> transactionList = new ArrayList<Transaction>();

  // conctructor functions
  public BankAccount (String accountNum) {
    accountNumber = accountNum;
  }

  public BankAccount (String accountNum, int initialBalance) {
    accountNumber = accountNum;
    balance = initialBalance;
  }

  public BankAccount (String accountNum, int initialBalance, int fee, int annualInterest) {
    accountNumber = accountNum;
    balance = initialBalance;
    withdrawalFee = fee;
    annualInterestRate = annualInterest;
  }

  // accessors
  public String getAccountNumber () {
    return accountNumber;
  }

  public int getBalance () {
    return balance;
  }
  
  public int getWithdrawalFee () {
    return withdrawalFee;
  }

  public int getAnnualInterestRate () {
    return annualInterestRate;
  }

  //mutators
  public void setAnnualInterestRate (int rate) {
    annualInterestRate = rate;
  }

  public void setWithdrawalFee (int fee) {
    withdrawalFee = fee;
  }

  //deposit methods
  public void deposit (int amount) {
    balance += amount;

    int index = -1;

    Transaction deposit = new Transaction (amount, null);
    if (transactionList.size() == 0) {
      transactionList.add(deposit);
    } else {
      for (int i = 0; i < transactionList.size(); i++) {

        Transaction currentElement = transactionList.get(i);
        if (deposit.transactionTime.isBefore(currentElement.transactionTime) == true) {
          index = i;
          break;
        }
      }

      if (index == -1){
        transactionList.add(deposit);
      } else {
        transactionList.add(index, deposit);
      }
    }
  }

  public void deposit (LocalDateTime transactionTime, int amount, String description) {
    balance += amount;

    int index = -1;

    Transaction deposit = new Transaction (transactionTime, amount, description);
    if (transactionList.size() == 0) {
      transactionList.add(deposit);
    } else {
      for (int i = 0; i < transactionList.size(); i++) {

        Transaction currentElement = transactionList.get(i);
        if (deposit.transactionTime.isBefore(currentElement.transactionTime) == true) {
          index = i;
          break;
        }
      }

      if (index == -1){
        transactionList.add(deposit);
      } else {
        transactionList.add(index, deposit);
      }
    }
    
  }

  public void deposit (int amount, String description) {
    balance += amount;

    int index = -1;

    Transaction deposit = new Transaction (amount, description);
    if (transactionList.size() == 0) {
      transactionList.add(deposit);
    } else {
      for (int i = 0; i < transactionList.size(); i++) {

        Transaction currentElement = transactionList.get(i);
        if (deposit.transactionTime.isBefore(currentElement.transactionTime) == true) {
          index = i;
          break;
        }
      }

      if (index == -1){
        transactionList.add(deposit);
      } else {
        transactionList.add(index, deposit);
      }
    }
  }

  //withdraw methods
  public void withdraw (int amount) {
    balance -= (amount + withdrawalFee);

    int index = -1;

    Transaction withdrawal = new Transaction (amount, null);
    if (transactionList.size() == 0) {
      transactionList.add(withdrawal);
    } else {
      for (int i = 0; i < transactionList.size(); i++) {

        Transaction currentElement = transactionList.get(i);
        if (withdrawal.transactionTime.isBefore(currentElement.transactionTime) == true) {
          index = i;
          break;
        }
      }

      if (index == -1){
        transactionList.add(withdrawal);
      } else {
        transactionList.add(index, withdrawal);
      }
    }
    
  }

  public void withdraw (LocalDateTime transactionTime, int amount, String description) {
    balance -= (amount + withdrawalFee);

    int index = -1;

    Transaction withdrawal = new Transaction (transactionTime, amount, description);
    if (transactionList.size() == 0) {
      transactionList.add(withdrawal);
    } else {
      for (int i = 0; i < transactionList.size(); i++) {

        Transaction currentElement = transactionList.get(i);
        if (withdrawal.transactionTime.isBefore(currentElement.transactionTime) == true) {
          index = i;
          break;
        }
      }

      if (index == -1){
        transactionList.add(withdrawal);
      } else {
        transactionList.add(index, withdrawal);
      }
    }
  }

  public void withdraw (int amount, String description) {
    balance -= (amount + withdrawalFee);

    int index = -1;

    Transaction withdrawal = new Transaction (amount, description);
    if (transactionList.size() == 0) {
      transactionList.add(withdrawal);
    } else {
      for (int i = 0; i < transactionList.size(); i++) {

        Transaction currentElement = transactionList.get(i);
        if (withdrawal.transactionTime.isBefore(currentElement.transactionTime) == true) {
          index = i;
          break;
        }
      }

      if (index == -1){
        transactionList.add(withdrawal);
      } else {
        transactionList.add(index, withdrawal);
      }
    }
  }

  // cheing if bank is in the negatives
  public boolean isOverDrawn () {
    if (balance < 0) {
      return true;
    } else {
      return false;
    }
  }

  public String toString () {
    String result;
    if (balance < 0) {
      result = "BankAccount " + accountNumber + ": ($" + df.format(-1*balance) + ")";
    } else {
      result = "BankAccount " + accountNumber + ": $" + df.format(balance);
    }
    return result;
  }



  public ArrayList <Transaction> getTransactions(LocalDateTime startTime, LocalDateTime endTime) {

    int counter = 0;

    ArrayList<Transaction> returnList = new ArrayList<Transaction>();

    for (int i = 0; i < transactionList.size(); i++) {
      returnList.add(transactionList.get(i));
    }

    ArrayList<Transaction> list = new ArrayList<Transaction>();

    for (int i = 0; i < transactionList.size(); i++) {
      Transaction currentElement = transactionList.get(i);
      //System.out.println(currentElement.description + " iterated");
      
      if (startTime == null && endTime != null) {
        if (currentElement.transactionTime.isAfter(endTime) == true) {
          list.add(transactionList.get(i));
        }
      } else if (startTime != null && endTime == null) {
        if (currentElement.transactionTime.isBefore(startTime) == true) {
          list.add(transactionList.get(i));
        }
      } else if (startTime != null && endTime != null) {
        if (currentElement.transactionTime.isBefore(startTime) == true || currentElement.transactionTime.isAfter(endTime) == true) {
          list.add(transactionList.get(i));
        }
      }

      //counter++;

    }

    //System.out.println(counter);

    for (int i = 0; i < list.size(); i++) {
      returnList.remove(list.get(i));
    }
    
    return returnList;
    
  }
}
   
