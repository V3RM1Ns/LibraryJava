package Interfaces;

import Models.User;

public interface ILendable {

    void borrow(User user);
    void returnItem();
}
