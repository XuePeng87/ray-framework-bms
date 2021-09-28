package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.core.util.entity.page.OrderInfo;
import cc.xuepeng.ray.framework.core.util.entity.page.PageParam;
import cc.xuepeng.ray.framework.core.util.entity.page.PageResult;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有业务Controller的父类。
 *
 * @author xuepeng
 */
@RestController
public class BaseController {

    /**
     * HttpServletRequest。
     */
    private HttpServletRequest httpServletRequest;

    /**
     * @return 获取HttpServletRequest。
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * 设置HttpServletRequest。
     *
     * @param httpServletRequest HttpServletRequest。
     */
    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * 将PageParam转换为Page。
     *
     * @param pageParam 请求分页参数。
     * @param <T>       Page对象的泛型类型。
     * @return Page对象。
     */
    public <T> Page<T> pageParamToPage(final PageParam pageParam) {
        final Page<T> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        if (CollectionUtils.isNotEmpty(pageParam.getOrders())) {
            for (OrderInfo order : pageParam.getOrders()) {
                page.getOrders().add(new OrderItem(order.getColumn(), order.getAsc()));
            }
        }
        return page;
    }

    /**
     * 将Page对象转换为PageResult对象。
     *
     * @param t1  Page对象。
     * @param k1  返回值Response对象。
     * @param <T> Page中结果的泛型对象类型。
     * @param <K> PageResult中结果的泛型对象类型。
     * @return PageResult对象。
     */
    public <T, K> PageResult<K> pageToPageResult(final Page<T> t1, Class<K> k1) {
        try {
            final ObjectMapper mapper = getObjectMapper();
            if (t1 == null) {
                return null;
            }
            final List<T> list = t1.getRecords();
            final List<K> record = new ArrayList<>();
            for (T t : list) {
                final String json = mapper.writeValueAsString(t);
                record.add(mapper.readValue(json, k1));
            }
            return new PageResult<>(t1.getCurrent(), t1.getSize(), t1.getTotal(), record);
        } catch (IOException e) {
            throw new IllegalArgumentException("Page对象转换异常", e);
        }
    }

    /**
     * 创建mapper
     *
     * @return ObjectMapper
     */
    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeNumber(localDateTime.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
            }
        });
        timeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                final long timestamp = jsonParser.getLongValue();
                return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, OffsetDateTime.now().getOffset());
            }
        });
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(timeModule);
        return mapper;
    }

}
