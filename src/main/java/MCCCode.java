public enum MCCCode {
    //https://mcc-codes.ru

    //        MCC6536("MasterCard MoneySend (Card2Card) ALFA MOBILE - Income"),
    MCC6538("MasterCard MoneySend (Card2Card) ALFA MOBILE - Outcome"),
    MCC5814("Fastfood"),
    MCC6011("Withdrawal"),
    MCC5411("Supermarket"),
    MCC4121("Limousines and Taxicabs (Yandex taxi)"),
    MCC5812("Restaurant, public catering"),
    MCC5818("Digital Goods – Multi-Category"),
    MCC7399("Business Services (Amazon)"),
    MCC7372("Programming, data processing"),
    MCC5968("Direct marketing (GOOGLE)"),
    MCC5995("Pet Shops"),
    MCC5977("Cosmetics Shops"),
    MCC5691("Shops of men's and women's clothing");


    private String category;

    MCCCode(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static MCCCode getMCC(String mcc) {
        return valueOf(mcc);
    }
}
