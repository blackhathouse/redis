package house.blackhat.dataredis.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

public class DataRedisConfigurationSelector extends AdviceModeImportSelector<EnableDataRedis> {

    private static final String[] CLASSES = {
            "house.blackhat.dataredis.cachemanager.DefaultCacheManager",
            "house.blackhat.dataredis.connectionfactory.LettuceConnectionFactoryConfig",
            "house.blackhat.dataredis.configuration.DataRedisConfiguration"
    };

    protected String[] selectImports(AdviceMode adviceMode) {
        return CLASSES;
    }

}
