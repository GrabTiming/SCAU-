package com.Lnn.controller;

import com.Lnn.DTO.ClubPageQueryDTO;
import com.Lnn.DTO.UserClubQueryDTO;
import com.Lnn.entity.Club;
import com.Lnn.mapper.ClubMapper;
import com.Lnn.result.PageResult;
import com.Lnn.result.RestBean;
import com.Lnn.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/club")
@Slf4j
public class ClubController {

    @Autowired
    private ClubService clubService;

    /**
     * 分页查询所有的社团
     * @return
     */
    @PostMapping("/all")
    public RestBean<PageResult> getAllClub(@RequestBody ClubPageQueryDTO clubPageQueryDTO){
        PageResult pageResult = clubService.pageQuery(clubPageQueryDTO);
        System.out.println(pageResult);
        return RestBean.success(pageResult,"社团查询成功");
    }


    /**
     * 查询 未申请过的社团
     * @param userClubQueryDTO
     * @return
     */
    @PostMapping("/search/unIncluded")
    public RestBean<PageResult> getAllClubAbsent(@RequestBody UserClubQueryDTO userClubQueryDTO)
    {
        PageResult pageResult = clubService.getAllClubAbsent(userClubQueryDTO);
        System.out.println(pageResult);
        return RestBean.success(pageResult,"社团查询成功");
    }


    /**
     * 查询 申请过的社团
     * @param userClubQueryDTO
     * @return
     */
    @PostMapping("/search/included")
    public RestBean<PageResult> getAllClubIncluded(@RequestBody UserClubQueryDTO userClubQueryDTO)
    {
        PageResult pageResult = clubService.getAllClubIncluded(userClubQueryDTO);
        System.out.println(pageResult);
        return RestBean.success(pageResult,"社团查询成功");
    }


    /**
     * 新增社团
     * @param club
     * @return
     */
    @PostMapping("/add")
    public RestBean addNewClub(@RequestBody Club club){

        if(clubService.getClubName(club)>0)
        {
            return RestBean.failure(400,"社团名重复");
        }
        log.info("新增社团:{}",club);

        clubService.addNewClub(club);
        return RestBean.success(club,"已添加社团："+club.getName());
    }


    /**
     * 删除社团
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public RestBean delete(@PathVariable("id") Integer id){
        log.info("删除社团：{}",id);
        clubService.delete(id);
        return RestBean.success(null,"删除成功");
    }

    /**
     * 修改社团信息
     * @param club
     * @return
     */
    @PutMapping
    public RestBean update(@RequestBody Club club){
        log.info("修改社团信息：{}",club);
        clubService.update(club);
        return RestBean.success(null,"修改成功");
    }


}
