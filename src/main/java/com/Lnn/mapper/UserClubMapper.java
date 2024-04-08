package com.Lnn.mapper;

import com.Lnn.vo.responseVO.ClubAuthorityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserClubMapper {


    List<ClubAuthorityVO> getClubAuthorityByUserId(Integer userId);
}
