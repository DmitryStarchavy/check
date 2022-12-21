package by.starchavy.clevertec.shop.product;

import java.util.Objects;

public class Product implements ProductInterface {

    private final int id;
    private final String name;
    private final double prise;
    private final String manufacturer;
    private final String certificate;
    private final String dateOfManufacture;

    public Product(int id, String name, double prise, String manufacturer, String certificate, String dateOfManufacture) {
        this.id = id;
        this.name = name;
        this.prise = prise;
        this.manufacturer = manufacturer;
        this.certificate = certificate;
        this.dateOfManufacture = dateOfManufacture;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrise() {
        return prise;
    }

    public static ProductBuilder bilder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private int id;
        private String name;
        private double prise;
        private String manufacturer;
        private String certificate;
        private String dateOfManufacture;

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder prise(double prise) {
            this.prise = prise;
            return this;
        }

        public ProductBuilder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public ProductBuilder certificate(String certificate) {
            this.certificate = certificate;
            return this;
        }

        public ProductBuilder dateOfManufacture(String dateOfManufacture) {
            this.dateOfManufacture = dateOfManufacture;
            return this;
        }

        public Product bild() {
            return new Product(id, name, prise, manufacturer, certificate, dateOfManufacture);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prise=" + prise +
                '}';
    }
}
