package model;

/**
 * Created by ��ΰ on 2017/4/30.
 */
public class Balance {

    public String ID;
    public float balance;
    public Balance(String ID,float balance){
        this.ID = ID;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "ID='" + ID + '\'' +
                ", balance=" + balance +
                '}';
    }
}
