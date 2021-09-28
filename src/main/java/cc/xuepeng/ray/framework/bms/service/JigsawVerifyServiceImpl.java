package cc.xuepeng.ray.framework.bms.service;

import cc.xuepeng.ray.framework.bms.exception.JigsawVerificationExpiredException;
import cc.xuepeng.ray.framework.bms.exception.JigsawVerificationMismatchException;
import cc.xuepeng.ray.framework.core.util.jigsaw.Jigsaw;
import cc.xuepeng.ray.framework.core.util.jigsaw.JigsawGenerator;
import cc.xuepeng.ray.framework.core.util.pk.PKUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务实现类。
 *
 * @author xuepeng
 */
@Service
public class JigsawVerifyServiceImpl implements JigsawVerifyService {

    /**
     * 随机数对象。
     */
    private static final Random RANDOM = new Random();
    /**
     * 拼图验证码的缓存KEY。
     */
    private static final String JIGSAW_KEY = "YC:VERIFY:JIGSAW:";
    /**
     * Redis客户端。
     */
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 拼图生成器。
     */
    private JigsawGenerator jigsawGenerator;

    /**
     * Redis客户端。
     *
     * @param redisTemplate Redis客户端。
     */
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 自动装配拼图生成器。
     *
     * @param jigsawGenerator 拼图生成器。
     */
    @Autowired
    public void setJigsawGenerator(JigsawGenerator jigsawGenerator) {
        this.jigsawGenerator = jigsawGenerator;
    }

    /**
     * 创建拼图信息。
     *
     * @return 拼图信息。
     */
    @SneakyThrows
    @Override
    public Jigsaw create() {
        final Jigsaw jigsaw = jigsawGenerator.generate(findTarget());
        jigsaw.setByImageBase64(Base64Utils.encodeToString(jigsaw.getBgImageBytes()));
        jigsaw.setCkImageBase64(Base64Utils.encodeToString(jigsaw.getCkImageBytes()));
        jigsaw.setToken(PKUtil.getRandomUUID());
        save(jigsaw);
        return jigsaw;
    }

    /**
     * 验证拼图信息是否正确。
     *
     * @param jigsaw 拼图实体类。
     * @return 拼图信息是否正确。
     */
    @Override
    public boolean validate(Jigsaw jigsaw) {
        if (!exists(jigsaw.getToken())) {
            throw new JigsawVerificationExpiredException("验证码过期。");
        }
        int result = get(jigsaw.getToken());
        if (result == 0) {
            throw new JigsawVerificationMismatchException("验证码不匹配。");
        }
        if (Math.abs(result - jigsaw.getX()) <= 5) {
            clear(jigsaw.getToken());
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * @return 查找背景模板。
     * @throws IOException 查找背景模板异常。
     */
    private File findTarget() throws IOException {
        final int targetNo = RANDOM.nextInt(20) + 1;
        final InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("static/targets/" + targetNo + ".png");
        File targetFile = new File(targetNo + ".png");
        targetFile.deleteOnExit();
        assert input != null;
        FileUtils.copyInputStreamToFile(input, targetFile);
        return targetFile;
    }

    /**
     * 保存拼图信息到缓存。
     *
     * @param jigsaw 拼图信息。
     */
    private void save(final Jigsaw jigsaw) {
        redisTemplate.boundValueOps(JIGSAW_KEY + jigsaw.getToken())
                .set(jigsaw, 2, TimeUnit.MINUTES);
    }

    /**
     * 判断缓存中是否存在拼图信息。
     *
     * @param token 令牌。
     * @return 是否存在拼图信息。
     */
    private boolean exists(final String token) {
        Boolean result = redisTemplate.hasKey(JIGSAW_KEY + token);
        return BooleanUtils.isTrue(result);
    }

    /**
     * 从缓存中读取拼图信息。
     *
     * @param token 令牌。
     * @return 拼图信息。
     */
    private int get(final String token) {
        Jigsaw jigsaw = (Jigsaw) redisTemplate.boundValueOps(JIGSAW_KEY + token).get();
        assert jigsaw != null;
        return jigsaw.getX();
    }

    /**
     * 删除缓存中的拼图信息。
     *
     * @param token 令牌。
     */
    private void clear(final String token) {
        redisTemplate.delete(JIGSAW_KEY + token);
    }

}
