package com.person.service.impl;

import com.person.bean.*;
import com.person.mapper.AdminMapper;
import com.person.service.AdminService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public LayuiData<UserInfo> getUserByAdmin(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<UserInfo> pageBean = new LayuiData<>();
        Integer conut = adminMapper.getUserByAdminOfNum(condition);
        Integer curPage = limit * (page - 1);
        List<UserInfo> list = adminMapper.getUserByAdmin(condition, limit, curPage);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }

//    @Override
//    public List getBankListByExcel(InputStream in, String fileName) throws Exception {
//        List list = new ArrayList<>();
//        //创建Excel工作薄
//        Workbook work = this.getWorkbook(in, fileName);
//        if (null == work) {
//            throw new Exception("创建Excel工作薄为空！");
//        }
//        Sheet sheet = null;
//        Row row = null;
//        Cell cell = null;
//
//        for (int i = 0; i < work.getNumberOfSheets(); i++) {
//            sheet = work.getSheetAt(i);
//            if (sheet == null) {
//                continue;
//            }
//
//            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
//                row = sheet.getRow(j);
//                if (row == null || row.getFirstCellNum() == j) {
//                    continue;
//                }
//
//                List<Object> li = new ArrayList<>();
//                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
//                    cell = row.getCell(y);
//                    li.add(cell);
//                }
//                list.add(li);
//            }
//        }
//        work.close();
//        return list;
//    }

//    /**
//     * 判断文件格式
//     *
//     * @param inStr
//     * @param fileName
//     * @return
//     * @throws Exception
//     */
//    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
//        Workbook workbook = null;
//        String fileType = fileName.substring(fileName.lastIndexOf("."));
//        if (".xls".equals(fileType)) {
//            workbook = new HSSFWorkbook(inStr);
//        } else if (".xlsx".equals(fileType)) {
//            workbook = new XSSFWorkbook(inStr);
//        } else {
//            throw new Exception("请上传excel文件！");
//        }
//        return workbook;
//    }

    @Override
    public LayuiData getParamList(Integer page, Integer pageSize, String name, String type) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<Params> list = adminMapper.getParamList(start, pageSize, name, type);
        int count = adminMapper.getParamListCount(name, type);
        if (list.size() > 0) {
            layuiData.setCode(0);
            layuiData.setMsg("");
            layuiData.setCount(count);
            layuiData.setData(list);
        } else {
            layuiData.setCode(1);
            layuiData.setMsg("查询失败");
        }
        return layuiData;
    }

    @Override
    public List<String> paramsTypeList() {
        List<String> list = adminMapper.paramsTypeList();
        return list;
    }

    @Override
    public Boolean addParams(String name, String type, String value) {
        Boolean flag = false;
        int num = adminMapper.addParams(name, type, value);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean delParams(String state, int id) {
        Boolean flag = false;
        int num = adminMapper.delParams(state, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean editParams(String name, int id) {
        Boolean flag = false;
        int num = adminMapper.editParams(name, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public LayuiData roleList() {

        LayuiData layuiData = new LayuiData();
        List<Role> roleList = adminMapper.roleList();
        int count = adminMapper.roleListCount();
        if (roleList.size() > 0) {
            layuiData.setCode(0);
            layuiData.setMsg("");
            layuiData.setCount(count);
            layuiData.setData(roleList);
        } else {
            layuiData.setCode(1);
            layuiData.setMsg("查询失败");
        }
        return layuiData;
    }

    //批量导入学生信息===============================================
    @Override
    public Integer uploadExcel(List<User> userInfoList) {
        return adminMapper.uploadExcel(userInfoList);
    }


}
