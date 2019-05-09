package com.baibaoxiang.service;

        import com.baibaoxiang.po.School;

        import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-15:45
 */
public interface SchoolService {


    /** 添加学校
     * @param record 学校
     * @return
     * @throws Exception
     */
    int insertSchool(School record) throws Exception;

    /** 删除学校
     * @param no
     * @return
     * @throws Exception
     */
    int deleteSchool(Integer no) throws Exception;

    /** 批量删除学校
     * @param no
     * @return
     * @throws Exception
     */
    void deleteSchoolBatch(Integer no[]) throws Exception;

    /** 查询学校信息 通过校区的编号
     * @param no
     * @return
     * @throws Exception
     */
    School selectSchoolByNo(Integer no) throws Exception;

    /** 获取所以学校的信息 （超级管理员干的）
     * @return
     * @throws Exception
     */
    List<School> selectAllSchool() throws Exception;

}
