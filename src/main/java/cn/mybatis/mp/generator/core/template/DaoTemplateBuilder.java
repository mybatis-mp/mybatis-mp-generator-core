package cn.mybatis.mp.generator.core.template;

import cn.mybatis.mp.generator.core.config.GeneratorConfig;
import cn.mybatis.mp.generator.core.database.meta.EntityInfo;
import cn.mybatis.mp.generator.core.util.GeneratorUtil;
import cn.mybatis.mp.generator.core.util.PathUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class DaoTemplateBuilder extends AbstractTemplateBuilder {

    public DaoTemplateBuilder(GeneratorConfig generatorConfig, EntityInfo entityInfo) {
        super(generatorConfig, entityInfo);
    }

    @Override
    public boolean enable() {
        return generatorConfig.getDaoConfig().isEnable();
    }

    @Override
    public String targetFilePath() {
        return PathUtils.buildFilePath(
                generatorConfig.getBaseFilePath(),
                generatorConfig.getJavaPath(),
                entityInfo.getDaoPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)),
                entityInfo.getDaoName())
                + ".java";
    }

    @Override
    public String templateFilePath() {
        return generatorConfig.getTemplateRootPath() + "/dao";
    }

    @Override
    public Map<String, Object> contextData() {
        Map<String, Object> data = new HashMap<>();
        data.put("imports", GeneratorUtil.buildDaoImports(generatorConfig, entityInfo));
        if (generatorConfig.getDaoConfig().getSuperClass() != null) {
            int dotIndex = generatorConfig.getDaoConfig().getSuperClass().lastIndexOf(".");
            String superName;
            if (dotIndex > 0) {
                superName = generatorConfig.getDaoConfig().getSuperClass().substring(dotIndex + 1);
            } else {
                superName = generatorConfig.getDaoConfig().getSuperClass();
            }
            data.put("superExtend", " extends " + superName);
        } else {
            data.put("superExtend", "");
        }
        data.put("date", LocalDate.now().toString());
        data.put("author", generatorConfig.getAuthor());
        data.put("entityInfo", entityInfo);
        data.put("entityConfig", generatorConfig.getEntityConfig());
        data.put("mapperConfig", generatorConfig.getMapperConfig());
        data.put("daoConfig", generatorConfig.getDaoConfig());
        data.put("daoImplConfig", generatorConfig.getDaoImplConfig());
        data.put("serviceConfig", generatorConfig.getServiceConfig());
        data.put("serviceImplConfig", generatorConfig.getServiceImplConfig());
        data.put("generatorConfig", generatorConfig);
        data.put("containerType", generatorConfig.getContainerType());
        return data;
    }
}