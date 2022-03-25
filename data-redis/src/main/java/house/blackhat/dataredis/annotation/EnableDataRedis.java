package house.blackhat.dataredis.annotation;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@EnableCaching
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DataRedisConfigurationSelector.class})
public @interface EnableDataRedis {

    AdviceMode mode() default AdviceMode.PROXY;

}
