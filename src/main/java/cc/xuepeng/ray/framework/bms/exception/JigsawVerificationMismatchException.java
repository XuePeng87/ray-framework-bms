package cc.xuepeng.ray.framework.bms.exception;

import cc.xuepeng.ray.framework.core.util.exception.BaseException;

public class JigsawVerificationMismatchException extends BaseException {

    /**
     * 构造函数。
     */
    public JigsawVerificationMismatchException() {
    }

    /**
     * 构造函数。
     *
     * @param msg 异常信息。
     */
    public JigsawVerificationMismatchException(String msg) {
        super(msg);
    }

    /**
     * 构造函数。
     *
     * @param cause 异常原因。
     */
    public JigsawVerificationMismatchException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数。
     *
     * @param msg   异常信息。
     * @param cause 异常原因。
     */
    public JigsawVerificationMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
