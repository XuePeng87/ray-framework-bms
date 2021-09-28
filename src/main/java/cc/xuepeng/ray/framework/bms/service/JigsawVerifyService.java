package cc.xuepeng.ray.framework.bms.service;

import cc.xuepeng.ray.framework.core.util.jigsaw.Jigsaw;

/**
 * 验证码服务。
 *
 * @author xuepeng
 */
public interface JigsawVerifyService {

    /**
     * 创建拼图信息。
     *
     * @return 拼图信息。
     */
    Jigsaw create();

    /**
     * 验证拼图信息是否正确。
     *
     * @param jigsaw 拼图实体类。
     * @return 拼图信息是否正确。
     */
    boolean validate(Jigsaw jigsaw);

}
