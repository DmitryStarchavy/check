package by.starchavy.clevertec.shop.discount;

public class DiscountCard {
    private int id;
    private int Discount;

    public DiscountCard(int id, int discount) {
        this.id = id;
        this.Discount = discount;
    }

    public int getId() {
        return id;
    }

    public int getDiscount() {
        return Discount;
    }

}