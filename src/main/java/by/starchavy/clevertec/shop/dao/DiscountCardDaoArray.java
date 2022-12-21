package by.starchavy.clevertec.shop.dao;

import by.starchavy.clevertec.shop.discount.DiscountCard;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardDaoArray implements DiscountCardDaoInterface {
    private List<DiscountCard> discountCards;
    {
        if (discountCards == null) {
            discountCards = new ArrayList<>();

            discountCards.add(new DiscountCard(1, 3));
            discountCards.add(new DiscountCard(2, 15));
            discountCards.add(new DiscountCard(3, 10));
            discountCards.add(new DiscountCard(4, 5));
            discountCards.add(new DiscountCard(5, 25));
            discountCards.add(new DiscountCard(6, 10));
            discountCards.add(new DiscountCard(7, 15));
        }
    }

    @Override
    public List<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    @Override
    public DiscountCard getDiscountCardById(int id) {
        for (int i =0; i<discountCards.size(); i++){
            if (id == discountCards.get(i).getId())
                return discountCards.get(i);
        }
        return null;
    }
}
