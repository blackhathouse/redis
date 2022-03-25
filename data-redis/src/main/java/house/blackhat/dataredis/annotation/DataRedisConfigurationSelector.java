package house.blackhat.dataredis.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

public class DataRedisConfigurationSelector extends AdviceModeImportSelector<EnableDataRedis> {

    private static final String[] CLASSES = {};

    protected String[] selectImports(AdviceMode adviceMode) {
        return CLASSES;
    }

}
