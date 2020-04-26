package com.laptrinhjavaweb.repository.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends SimpleJpaRepository<BuildingEntity> implements IBuildingRepository{

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, Pageable pageable, BuildingSearchBuilder fieldSearch) {	
	
		StringBuilder sqlSearch = new StringBuilder(" SELECT * from building A ");
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())){
			sqlSearch.append(" INNER JOIN assignmentstaff assignmentstaff ON A.id = assignmentstaff.buildingid ");
		}
		sqlSearch.append(" WHERE 1=1 ");
		sqlSearch = this.createSQLfindAll(sqlSearch, params);
		String sqlSpecial = buildSqlSpecial(fieldSearch);	
		sqlSearch.append(sqlSpecial);
		return this.findAll(sqlSearch.toString(), pageable);
	}
	private String buildSqlSpecial(BuildingSearchBuilder fieldSearch) {
		StringBuilder result = new StringBuilder();
		if(StringUtils.isNotBlank(fieldSearch.getCostRentFrom())){
			result.append(" AND A.costrent >= "+fieldSearch.getCostRentFrom()+"");
		}
		if(StringUtils.isNotBlank(fieldSearch.getCostRentTo())){
			result.append(" AND A.costrent <= "+fieldSearch.getCostRentTo()+"");
		}
		if(fieldSearch.getBuildingTypes().length > 0){
			result.append(" AND ( ");
			//java7
//			int i=0;
//			for(String item: fieldSearch.getBuildingTypes()) {
//				if(i==0) {
//					result.append(" A.type like '%"+item+"%' ");
//					
//				}else {
//					result.append(" OR A.type like '%"+item+"%' ");
//				}
//				i++;
//			}
			//java8
//			result.append(" A.type like '%"+fieldSearch.getBuildingTypes()[0]+"%' ");
//			Arrays.stream(fieldSearch.getBuildingTypes()).filter(item -> !item.equals(fieldSearch.getBuildingTypes()[0]))
//						.forEach(item -> result.append(" OR A.type like '%"+item+"%' "));		
			String buildingTypeSql = Arrays.stream(fieldSearch.getBuildingTypes()).map(item -> " A.type like '%"+item+"%' ").collect(Collectors.joining(" OR "));
						
			result.append(buildingTypeSql + ")");
		}
		if(StringUtils.isNotBlank(fieldSearch.getRentAreaFrom()) || StringUtils.isNotBlank(fieldSearch.getRentAreaTo())){
			result.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id ");
			
			if(StringUtils.isNotBlank(fieldSearch.getRentAreaFrom())) {
				result.append(" AND ra.value >= "+fieldSearch.getRentAreaFrom()+" ");
			}
			if(StringUtils.isNotBlank(fieldSearch.getRentAreaTo())) {
				result.append(" AND ra.value <= "+fieldSearch.getRentAreaTo()+" ");
			}
			result.append("))");			
		}
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())){
			result.append(" AND assignmentstaff.staffid = "+fieldSearch.getStaffId()+" ");
		}
		return result.toString();
	}
//	@Override
//	public BuildingEntity save(BuildingEntity buildingEntity) {
//		String sql = "INSERT INTO building(name, street, district, ward) VALUES (?,?,?,?)";
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			Long id = null;
//			connection = EntityManagerFactory.getConnection();
//			connection.setAutoCommit(false);
//			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			//setParameter(statement, parameters);
//			statement.setString(1, buildingEntity.getName());
//			statement.setString(2, buildingEntity.getStreet());
//			statement.setString(3, buildingEntity.getDistrict());
//			statement.setString(4, buildingEntity.getWard());
//			statement.executeUpdate();
//			resultSet = statement.getGeneratedKeys();
//			if (resultSet.next()) {
//				id = resultSet.getLong(1);
//			}
//			connection.commit();		
//			return this.findById(id);
//		} catch (SQLException e) {
//			if (connection != null) {
//				try {
//					connection.rollback();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		} finally {
//			try {
//				if (connection != null) {
//					connection.close();
//				}
//				if (statement != null) {
//					statement.close();
//				}
//				if (resultSet != null) {
//					resultSet.close();
//				}
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//		}
//		return new BuildingEntity();
//	}
	@Override
	public int getTotalItem(Map<String, Object> params, BuildingSearchBuilder fieldSearch) {
		StringBuilder sqlSearch = new StringBuilder(" SELECT count(*) from building A ");
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())){
			sqlSearch.append(" INNER JOIN assignmentstaff assignmentstaff ON A.id = assignmentstaff.buildingid ");
		}
		sqlSearch.append(" WHERE 1=1 ");
		sqlSearch = this.createSQLfindAll(sqlSearch, params);
		String sqlSpecial = buildSqlSpecial(fieldSearch);	
		sqlSearch.append(sqlSpecial);
		return this.count(sqlSearch.toString());
	}

}
