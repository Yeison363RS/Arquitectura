package models;

public enum PrecisionEnum {

    SIMPLE(1,8,23),
    DOUBLE(1,11,52);

    final int bitSign;
    final int bitsExponent;
    final int bitsMantisa;

    PrecisionEnum(int bitSign, int bitsExponent, int bitsMantisa) {
        this.bitSign = bitSign;
        this.bitsExponent = bitsExponent;
        this.bitsMantisa = bitsMantisa;
    }
}
