package net.lab1024.sa.base.module.support.codegenerator.service.variable.front;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.base.CaseFormat;
import net.lab1024.sa.base.common.util.SmartStringUtil;
import net.lab1024.sa.base.module.support.codegenerator.constant.CodeFrontComponentEnum;
import net.lab1024.sa.base.module.support.codegenerator.constant.CodeQueryFieldQueryTypeEnum;
import net.lab1024.sa.base.module.support.codegenerator.domain.form.CodeGeneratorConfigForm;
import net.lab1024.sa.base.module.support.codegenerator.domain.model.CodeField;
import net.lab1024.sa.base.module.support.codegenerator.domain.model.CodeInsertAndUpdateField;
import net.lab1024.sa.base.module.support.codegenerator.domain.model.CodeQueryField;
import net.lab1024.sa.base.module.support.codegenerator.service.variable.CodeGenerateBaseVariableService;

import java.util.*;

/**
 * @Author 1024创新实验室-主任:卓大
 * @Date 2022/9/29 17:20:41
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright  <a href="https://1024lab.net">1024创新实验室</a>
 */

public class ListVariableService extends CodeGenerateBaseVariableService {

    @Override
    public boolean isSupport(CodeGeneratorConfigForm form) {
        return true;
    }

    @Override
    public Map<String, Object> getInjectVariablesMap(CodeGeneratorConfigForm form) {
        Map<String, Object> variablesMap = new HashMap<>();

        List<Map<String, Object>> queryVariable = new ArrayList<>();
        List<CodeQueryField> queryFields = form.getQueryFields();
        HashSet<String> frontImportSet = new HashSet<>();
        frontImportSet.add("import " + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, form.getBasic().getModuleName()) + "Form from './" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, form.getBasic().getModuleName()) + "-form.vue';");

        for (CodeQueryField queryField : queryFields) {
            Map<String, Object> objectMap = BeanUtil.beanToMap(queryField);

            CodeField codeField = getCodeFieldByColumnName(queryField.getColumnNameList().get(0), form);
            objectMap.put("frontEnumName", codeField.getEnumName());
            objectMap.put("dict", codeField.getDict());

            if(CodeQueryFieldQueryTypeEnum.ENUM.equalsValue(queryField.getQueryTypeEnum())){
                frontImportSet.add("import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';");
            }

            if(CodeQueryFieldQueryTypeEnum.DICT.equalsValue(queryField.getQueryTypeEnum())){
                frontImportSet.add("import DictSelect from '/@/components/support/dict-select/index.vue';");
            }

            if(CodeQueryFieldQueryTypeEnum.DATE_RANGE.equalsValue(queryField.getQueryTypeEnum())){
                frontImportSet.add("import { defaultTimeRanges } from '/@/lib/default-time-ranges';");
            }
            queryVariable.add(objectMap);
        }

        List<Map<String, Object>> listVariable = new ArrayList<>();
        for (CodeInsertAndUpdateField field : form.getInsertAndUpdate().getFieldList()) {
            Map<String, Object> objectMap = BeanUtil.beanToMap(field);
            CodeField codeField = getCodeFieldByColumnName(field.getColumnName(), form);
            if (codeField == null) {
                continue;
            }
            objectMap.put("fieldName", codeField.getFieldName());
            objectMap.put("dict", codeField.getDict());

            if(SmartStringUtil.isNotBlank(codeField.getDict())) {
                frontImportSet.add("import DictPreview from '/@/components/dict-preview/index.vue';");
                frontImportSet.add("import { useDict } from '/@/utils/dict';");
            }

            if (CodeFrontComponentEnum.FILE_UPLOAD.equalsValue(field.getFrontComponent())) {
                frontImportSet.add("import FilePreview from '/@/components/support/file-preview/index.vue';");
            }

            listVariable.add(objectMap);
        }

        variablesMap.put("queryFields", queryVariable);
        variablesMap.put("listFields", listVariable);
        variablesMap.put("frontImportList", new ArrayList<>(frontImportSet));
        return variablesMap;
    }
}
