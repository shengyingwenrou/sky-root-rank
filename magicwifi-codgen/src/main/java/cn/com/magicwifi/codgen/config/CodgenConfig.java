/*******************************************************************************
 * Copyright (c) 2015 magicwifi.com.cn
 *******************************************************************************/
package cn.com.magicwifi.codgen.config;

import java.util.Properties;

import cn.com.magicwifi.framework.core.utils.spring.PropertiesLoader;

/**
 * 鼹鼠配置.
 *
 * User: Cosmo<87292008@qq.com> Date: 15-12-21 Time: 上午9:15
 */
public final class CodgenConfig {

	private static PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:/config/codgen.properties");

	private static Properties codgen;

	public static Properties getCodgen() {
		return codgen;
	}

	/**
	 * @param key key
	 * @return 根据key获取属性
	 */
	public static String getProperty(String key, String defaultValue) {
		return codgen.getProperty(key, defaultValue);
	}

	/**
	 * @return 获取JDBC驱动
	 */
	public static String getJdbcDriver() {
		return codgen.getProperty("jdbc.driver", "com.mysql.jdbc.Driver");
	}

	/**
	 * @return 获取jdbc url
	 */
	public static String getJdbcUrl() {
		return codgen.getProperty("jdbc.url");
	}

	/**
	 * @return 获取数据库用户名
	 */
	public static String getJdbcUsername() {
		return codgen.getProperty("jdbc.username");
	}

	/**
	 * @return 获取数据库密码
	 */
	public static String getJdbcPassword() {
		return codgen.getProperty("jdbc.password");
	}

	/**
	 * @return 获取要移除的前缀
	 */
	public static String getTableRemovePrefixes() {
		return codgen.getProperty("table_remove_prefixes", "");
	}

	/**
	 * @return 忽略的表
	 */
	public static String getIgnoreTables() {
		return codgen.getProperty("table_ignore", "");
	}

	/**
	 * @return 获取代码生成输出根目录
	 */
	public static String getOutputRootDir() {
		return codgen.getProperty("output_root");
	}

	/**
	 * @return 数据库名
	 */
	public static String getDatabaseName() {
		return codgen.getProperty("database");
	}

	public static String getSchema() {
		return codgen.getProperty("jdbc.schema", null);
	}

	public static String getCatalog() {
		return codgen.getProperty("jdbc.catalog", null);
	}

	/**
	 * @return 排除的模板
	 */
	public static String getTemplateExcludes() {
		return codgen.getProperty("template_excludes", null);
	}

	static {
		codgen = propertiesLoader.getProperties();
		setProperties();
	}

	private static void setProperties() {
		Properties dirProperties = autoReplacePropertiesValue2DirValue(codgen);
		codgen.putAll(dirProperties);
	}

	private static Properties autoReplacePropertiesValue2DirValue(Properties props) {
		Properties autoReplaceProperties = new Properties();
		for (Object key : props.keySet()) {
			String dirKey = key.toString() + "_dir";
			String value = props.getProperty(key.toString());
			String dirValue = value.replace('.', '/');
			autoReplaceProperties.put(dirKey, dirValue);
		}
		return autoReplaceProperties;
	}

	private CodgenConfig() {
	}
}
