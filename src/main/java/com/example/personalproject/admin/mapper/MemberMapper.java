package com.example.personalproject.admin.mapper;


import com.example.personalproject.admin.model.MemberParam;
import com.example.personalproject.model.dto.MemberDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	long selectListCount(MemberParam parameter);

	List<MemberDto> selectList(MemberParam parameter);
}
