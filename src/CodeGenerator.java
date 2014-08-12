import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.atteo.evo.inflector.English;

import com.google.common.base.Joiner;
import com.mart.annotation.POSFieldAnnotation;
import com.mart.annotation.POSModelAnnotation;
import com.mart.model.POSModel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CodeGenerator {
	
	private String basePackage;
	private String srcFolderPath;

	private String modelPackage;
	private String servicePackage;
	private String serviceImplPackage;
	private String daoPackage;
	private String daoImplPackage;
	


	/**
	 * @param basePackage
	 * @param srcFolderPath
	 */
	public CodeGenerator(String basePackage, String srcFolderPath) {
		super();
		this.basePackage = basePackage;
		this.srcFolderPath = srcFolderPath;

		this.modelPackage = this.basePackage + ".model";
		this.servicePackage = this.basePackage + ".service";
		this.serviceImplPackage = this.basePackage + ".service.impl";
		this.daoPackage = this.basePackage + ".dao";
		this.daoImplPackage = this.basePackage + ".dao.impl";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CodeGenerator generator = new CodeGenerator("com.mart",
				"/Users/kbethalam/Documents/workspaces/hbase/Test/src/");

		List<String> models = new ArrayList<String>();
		models.add("Category");
		models.add("Manufacturer");
		models.add("Brand");
		models.add("InventoryItem");
		models.add("Product");
		models.add("ProductMaster");

		generator.generateCode(models);
		// TODO Auto-generated method stub

	}

	public void generateCode(List<String> modelClassesNames) {

		// Build the data-model
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("servicePackage", this.servicePackage);
		data.put("serviceImplPackage", this.serviceImplPackage);
		data.put("modelPackage", this.modelPackage);
		data.put("daoPackage", this.daoPackage);
		data.put("daoImplPackage", this.daoImplPackage);

		QueryGenerator.startGeneration();
		
		for (String modelClass : modelClassesNames) {

			data.put("variableName", getVariableName(modelClass));
			data.put("className", modelClass);
			data.put("classNamePlural", English.plural(modelClass));

			generateCRUDQueries(modelClass, data);

			generateServiceInterface(modelClass, data);
			generateServiceImpl(modelClass, data);
			generateDAOInterface(modelClass, data);
			generateDAOImpl(modelClass, data);

		}
		
		QueryGenerator.completeGeneration();
	}

	private String getVariableName(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	private void generateServiceInterface(String modelClass,
			Map<String, Object> data) {

		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("ServiceInterface.ftl");

			generateFile(modelClass + "Service", this.servicePackage, template,
					data);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	private void generateServiceImpl(String modelClass, Map<String, Object> data) {

		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("ServiceImpl.ftl");

			generateFile(modelClass + "ServiceImpl", this.serviceImplPackage,
					template, data);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	private void generateDAOInterface(String modelClass,
			Map<String, Object> data) {

		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("DAOInterface.ftl");

			generateFile(modelClass + "DAO", this.daoPackage, template, data);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	private void generateDAOImpl(String modelClass, Map<String, Object> data) {

		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("DAOImpl.ftl");

			generateFile(modelClass + "DAOImpl", this.daoImplPackage, template,
					data);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public void generateCRUDQueries(String className, Map<String, Object> data) {

		try {

			Class<? extends POSModel> modelClass = (Class<? extends POSModel>) Class
					.forName(this.modelPackage + "." + className);

			String dbTableName = getTableName(modelClass);
			List<Object[]> fieldsInfo = getFieldsInfo(modelClass);
			List<String> fieldNames = new ArrayList<String>();
			for (Object[] fieldInfo : fieldsInfo) {
				fieldNames.add(((String) fieldInfo[2]).toUpperCase());
			}
			
			QueryGenerator.generateInsertQuery(dbTableName, fieldNames);
			QueryGenerator.generateUpdateQuery(dbTableName, fieldNames);
			QueryGenerator.generateDeleteQuery(dbTableName, fieldNames);
			QueryGenerator.generateGetByIdQuery(dbTableName, fieldNames);
			QueryGenerator.generateGetAllQuery(dbTableName, fieldNames);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private List<Object[]> getFieldsInfo(Class<? extends POSModel> modelClass) {
		List<Object[]> fieldsArray = new ArrayList<Object[]>();

		try {

			Object entity = modelClass.newInstance();

			for (Field field : CodeGenerator.getAllFields(modelClass)) {
				if (field.isAnnotationPresent(POSFieldAnnotation.class)) {
					try {
						POSFieldAnnotation fieldAnnotation = field
								.getAnnotation(POSFieldAnnotation.class);
						String jsonKey = fieldAnnotation.jsonColumnName();
						String dbKey = fieldAnnotation.dbColumnName();
						Object[] fieldInfo = new Object[3];
						fieldInfo[0] = field.getName();
						fieldInfo[1] = field.getType();
						fieldInfo[2] = dbKey;

						fieldsArray.add(fieldInfo);
					} catch (Exception exp) {
						exp.printStackTrace();
					}
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return fieldsArray;
	}

	private String getTableName(Class<? extends POSModel> modelClass)
			throws ClassNotFoundException {

		String dbTableName = "";

		if (modelClass.isAnnotationPresent(POSModelAnnotation.class)) {
			try {
				POSModelAnnotation modelAnnotation = modelClass
						.getAnnotation(POSModelAnnotation.class);
				dbTableName = modelAnnotation.dbTableName();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		return dbTableName;
	}

	public void generateFile(String className, String packageName,
			Template template, Map<String, Object> data) throws IOException,
			TemplateException {

		String filePath = this.srcFolderPath + packageName.replace(".", "/");

		File parentDirectory = new File(filePath); // will create a sub folder
													// for each user (currently
													// does not work, below
													// hopefully is a solution)

		if (!parentDirectory.exists()) {
			parentDirectory.mkdirs();
		}

		Writer fileWriter = new FileWriter(filePath + "/" + className + ".java");
		template.process(data, fileWriter);
		fileWriter.flush();
		fileWriter.close();
	}

	public static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}
}
