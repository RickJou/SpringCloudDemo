package zrk.test.demo.util;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

public class MyBatisGeneratorCommons extends DefaultCommentGenerator {
	// 定义一个是否使用修改后的模式的标识
	private boolean suppressAllComments = true;

	/**
	 * 设置实体类 属性注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

		if (suppressAllComments) {
			if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
				field.addJavaDocLine("//" + introspectedColumn.getRemarks());
			}
		} else {
			super.addFieldComment(field, introspectedTable, introspectedColumn);
		}
	}

	/**
	 * 设置实体类 getter注释
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
				method.addJavaDocLine("/**");
				method.addJavaDocLine(" * 获取:" + introspectedColumn.getRemarks());
				method.addJavaDocLine(" */");
			}
		} else {
			super.addGetterComment(method, introspectedTable, introspectedColumn);
		}
	}

	/**
	 * 设置实体类 setter注释
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
				method.addJavaDocLine("/**");
				method.addJavaDocLine(" * 设置:" + introspectedColumn.getRemarks());
				method.addJavaDocLine(" */");
			}
		} else {
			super.addSetterComment(method, introspectedTable, introspectedColumn);
		}
	}
	
	/**
	 * 去掉mapper原始注释
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		} else {
			super.addGeneralMethodComment(method, introspectedTable);
		}
	}

	/**
	 * 去掉mapping原始注释
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		if (suppressAllComments) {
			return;
		} else {
			super.addComment(xmlElement);
		}
	}
}
