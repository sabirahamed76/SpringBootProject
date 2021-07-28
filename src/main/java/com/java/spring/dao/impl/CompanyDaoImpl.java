package com.java.spring.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.java.spring.dao.CompanyDao;
import com.java.spring.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int saveCompany(Company c){
		String query="insert into admin_sys_company values('"+c.getId()+"','"+c.getName()+"')";
		return jdbcTemplate.update(query);
	}
	public int updateCompany(Company c){
		String query="update admin_sys_company set name='"+c.getName()+"' where id='"+c.getId()+"' ";
		return jdbcTemplate.update(query);
	}
	public int deleteCompany(Company c){
		String query="delete from admin_sys_company where id='"+c.getId()+"' ";
		return jdbcTemplate.update(query);
	}
	
	public Boolean saveCompanyByPreparedStatement(Company c){  
	    String query="insert into admin_sys_company (id,name) values(?,?)";  
	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
	    @Override  
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException {  
	              
	        ps.setLong(1,c.getId());  
	        ps.setString(2,c.getName());  	              
	        return ps.execute();  
	              
	    }  
	    });  
	}  
	
	
	public  void saveCompanyByNamedParameterJdbcTemplate (Company c){  
		String query="insert into admin_sys_company values (:id,:name)";  
		  
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id",c.getId());  
		map.put("name",c.getName());   
		  
		namedParameterJdbcTemplate.execute(query,map,new PreparedStatementCallback() {  
		    @Override  
		    public Object doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		        return ps.executeUpdate();  
		    }  
		});  
	}  
	
	public List<Company> getAllCompaniesByResultSetExtractor(){  
		 return jdbcTemplate.query("select * from admin_sys_company",new ResultSetExtractor<List<Company>>(){  
		    @Override  
		     public List<Company> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Company> list=new ArrayList<Company>();  
		        while(rs.next()){  
		        	Company e=new Company();  
			        e.setId(rs.getInt(1));  
			        e.setName(rs.getString(2));  
			        list.add(e);  
		        }  
		        return list;  
		        }  
		    });  
	} 
	
	public List<Company> getAllCompaniesByRowMapper(){  
		 return jdbcTemplate.query("select * from admin_sys_company",new RowMapper<Company>(){  
		    @Override  
		    public Company mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	Company e=new Company();  
		        e.setId(rs.getInt(1));  
		        e.setName(rs.getString(2));  
		        return e;  
		    }  
		    });  
	} 
	
	
	
	
}