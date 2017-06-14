package org.jumao.bi.utis.exceptions;

/**
 * @author chen qian
 */
public class WrapEx extends Exception {

    private StringBuilder errSb;

    public WrapEx(StringBuilder errSb) {
        this.errSb = errSb;
    }

    public StringBuilder getErrSb() {
        return errSb;
    }

    public void setErrSb(StringBuilder errSb) {
        this.errSb = errSb;
    }

    @Override
    public String toString() {
        return errSb.toString();
    }

}
