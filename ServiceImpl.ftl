package ${serviceImplPackage};


import java.util.List;
import ${modelPackage}.${className};
import ${servicePackage}.${className}Service;
import ${daoPackage}.${className}DAO;
import ${daoImplPackage}.${className}DAOImpl;

public class ${className}ServiceImpl implements ${className}Service {

	protected ${className}DAO ${variableName}DAO ;
	public ${className}ServiceImpl() {
		this.${variableName}DAO = new ${className}DAOImpl();
	}
	
	public int add${className}(${className} ${variableName}){
		return this.${variableName}DAO.add${className}(${variableName});
	}
	public void update${className}(${className} ${variableName}){
		this.${variableName}DAO.update${className}(${variableName});
	}
	public void delete${className}(int id){
		this.${variableName}DAO.delete${className}(id);
	}
	public ${className} get${className}ById(int id){
		return this.${variableName}DAO.get${className}ById(id);
	}
	public List<${className}> getAll${classNamePlural}(){
		return this.${variableName}DAO.getAll${classNamePlural}();
	}
	public List<${className}> get${classNamePlural}ByCriteria(Object criterion){
		return this.${variableName}DAO.get${classNamePlural}ByCriteria(criterion);
	}
}

