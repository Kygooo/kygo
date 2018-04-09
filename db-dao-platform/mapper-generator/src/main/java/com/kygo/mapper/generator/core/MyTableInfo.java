package com.kygo.mapper.generator.core;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.util.List;


public class MyTableInfo extends TableInfo{
	
	
	public MyTableInfo(StrategyConfig strategyConfig, TableInfo tableInfo) {
		super();
		setName(tableInfo.getName());
		setXmlName(tableInfo.getXmlName());
		setEntityName(strategyConfig, tableInfo.getEntityName());
		setConvert(tableInfo.isConvert());
		setFields(tableInfo.getFields());
		setComment(tableInfo.getComment());
		setCommonFields(tableInfo.getCommonFields());
		setMapperName(tableInfo.getMapperName());
		if(tableInfo.getImportPackages() != null){
			for(String pkg : tableInfo.getImportPackages()){
				setImportPackages(pkg);
			}		
		}
		
	}

	public String getFieldNames() {
		StringBuilder names = new StringBuilder();
		List<TableField> fields = getFields();
        for (int i = 0; i < fields.size(); i++) {
            TableField fd = fields.get(i);
            if (i == fields.size() - 1) {
                names.append(fd.getName());
            } else {
                names.append(fd.getName()).append(", ");
            }
        }
        String fieldNames = names.toString();
        return fieldNames;
    }
	
	@Override
	public void setFields(List<TableField> fields) {
		super.setFields(fields);
		for(TableField tableField : fields){
			//System.err.println("-------getName----"+tableField.getName());
			//System.err.println("-------getPropertyName----"+tableField.getPropertyName());
			if(tableField.getName().startsWith("is_")){
				tableField.setColumnType(DbColumnType.BOOLEAN);
			}
			if(tableField.getName().endsWith("_time")){
				tableField.setColumnType(DbColumnType.TIMESTAMP);
			}
		}
	}

}
