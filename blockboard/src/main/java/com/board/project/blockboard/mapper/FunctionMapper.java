/**
 * @author Dongwook Kim <dongwook.kim1211@worksmobile.com>
 * @file FunctionMapper.java
 */
package com.board.project.blockboard.mapper;

import com.board.project.blockboard.dto.FunctionDTO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FunctionMapper {

  List<FunctionDTO> selectFunctionCheckByCompanyId(int companyId);

  boolean selectFunctionCheckByCompanyIdAndFunctionId(Map<String, Object> functionPrimaryKey);

  void insertFunctionCheckData(Map<String, Object> functionPrimaryKey);

  void deleteFunctionCheckData(Map<String, Object> functionPrimaryKey);
}
