package models;

public enum PrecisionEnum {

    SIMPLE(1,8,23,127),
    DOUBLE(1,11,52,1023);

    final int bitSign;
    final int bitsExponent;
    final int bitsMantisa;
    final int exponentValue;

    PrecisionEnum(int bitSign, int bitsExponent, int bitsMantisa, int exponentValue) {
        this.bitSign = bitSign;
        this.bitsExponent = bitsExponent;
        this.bitsMantisa = bitsMantisa;
        this.exponentValue = exponentValue;
    }
}
