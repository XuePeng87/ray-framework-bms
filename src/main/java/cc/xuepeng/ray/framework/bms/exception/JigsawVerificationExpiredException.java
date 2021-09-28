package cc.xuepeng.ray.framework.bms.exception;

import cc.xuepeng.ray.framework.core.util.exception.BaseException;

public class JigsawVerificationExpiredException extends BaseException {

    /**
     * 构造函数。
     */
    public JigsawVerificationExpiredException() {
    }

    /**
     * 构造函数。
     *
     * @param msg 异常信息。
     */
    public JigsawVerificationExpiredException(String msg) {
        super(msg);
    }

    /**
     * 构造函数。
     *
     * @param cause 异常原因。
     */
    public JigsawVerificationExpiredException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数。
     *
     * @param msg   异常信息。
     * @param cause 异常原因。
     */
    public JigsawVerificationExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
