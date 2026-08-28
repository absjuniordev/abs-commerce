package com.absjrdev.abscommerce.payment.domain.paymentMethod;

public enum PaymentMethod {

    PIX(1),
    DEBIT_CARD(2),
    CREDIT_CARD(3);

    private int code;

    private PaymentMethod(int code) {
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public static PaymentMethod valueOf(int code){
        for(PaymentMethod value  : PaymentMethod.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentMethod code ");
    }

}
