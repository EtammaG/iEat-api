package com.etammag.ieat.mbg;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.DefaultCommentGenerator;

public class CommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // 为mapper添加@Mapper注解
        if (compilationUnit instanceof Interface mapperInterface) {
            mapperInterface.addAnnotation("@Mapper");
            mapperInterface.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        }
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 为model添加@ApiModel注解
        topLevelClass.addAnnotation("@ApiModel(value=\"" + topLevelClass.getType().getShortName() + "\")");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModel"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 为属性添加@ApiModelProperty注解
        field.addAnnotation("@ApiModelProperty(value=\"" + introspectedColumn.getRemarks() + "\")");
    }
}