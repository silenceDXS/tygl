package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.Notice;

import com.thxy.tygl.entity.NoticeJson;
import com.thxy.tygl.entity.Type;
import com.thxy.tygl.entity.User;
import com.thxy.tygl.mapper.NoticeMapper;

import com.thxy.tygl.service.NoticeService;
import com.thxy.tygl.untils.FileSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    /**
     *
     * @param nowPage
     * @param pageSize
     * @return resultMap 存入所以user
     */

    public Map<String,Object> findAllNotice(int nowPage, int pageSize) {
        //获取所有用户
        List<Notice> notices=noticeMapper.getAllNoticeForPage((nowPage-1)*pageSize,pageSize);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("notice",notices);
        return resultMap;
    }

    /**
     *
     * @return int 返回总记录数
     */
    public  int getTotal(){

        return noticeMapper.getCountNotice();
    }


    public List<Notice> findNoticeByTitle(int nowPage, int pageSize, String param) {
        int currPage=(nowPage-1)*pageSize;
        Map<String,Object> map=new HashMap<>();
        map.put("currPage",currPage);
        map.put("pageSize",pageSize);
        map.put("title",param);
        List<Notice> result=noticeMapper.findNoticeByTitle(map);
        return result;
    }


    public int countFindNotice(String title) {
        int result=noticeMapper.countFindNotice(title);
        return result;
    }

    public int delNoticeById(int param) {
        int result=noticeMapper.delNoticeById(param);
        return result;
    }

    @Override
    public int insertNotice(NoticeJson noticeJson) {
        //获取时间
        int param=0;
        Notice notice=new Notice();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date dadate = new Date();//获得系统时间
        String nowDaDate = sdf.format(dadate);
        Timestamp daTs = Timestamp.valueOf(nowDaDate);
        MultipartFile[] pic=noticeJson.getPic();
        for(MultipartFile f:pic){
            param=param+1;
            if (param==1){
            notice.setAuthorUrl(FileSave.upload(f,"E:\\java\\tygl\\src\\main\\resources\\static\\images\\tyglFilePlan\\"));
            }else if(param==2){
               notice.setCoverUrl(FileSave.upload(f,"E:\\java\\tygl\\src\\main\\resources\\static\\images\\tyglFilePlan\\"));
            }

        }

        notice.setActive(1);
        notice.setAuthor(noticeJson.getAuthor());
        notice.setContent(noticeJson.getContent());
        notice.setTitle(noticeJson.getTitle());
        notice.setNoticeType(noticeJson.getNoticeType());
        notice.setCreateTime(daTs);
        int result=noticeMapper.insertNotice(notice);
        return result;
    }


    public Notice findNoticeById(int noticeId) {
        Notice notice=noticeMapper.findNoticeById(noticeId);
        return notice;
    }

    @Override
    public List<Type> getType() {
        List<Type> list=noticeMapper.getType();
        return list;
    }


}
