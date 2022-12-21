package by.starchavy.clevertec.shop.dao;

import by.starchavy.clevertec.shop.discount.DiscountCard;

import java.util.List;

public interface DiscountCardDaoInterface {
    List<DiscountCard> getDiscountCards();

    DiscountCard getDiscountCardById(int id);
}
