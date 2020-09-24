package com.mjt.web;

import com.mjt.pojo.Chapter;
import com.mjt.service.ChapterService;
import com.mjt.service.ChaptersService;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @Autowired
    ChaptersService chaptersService;

    @PostMapping(value = "/chapters/{csid}")
    public Object add(@PathVariable("csid") int csid, Chapter bena, MultipartFile file, HttpServletRequest request){
        Chapter chapter = bena;
        System.out.println(csid);
        chapter.setChapters(chaptersService.get(csid));
        chapterService.add(chapter);

        try {
            //创建文件目录
            File fileFolder = new File(request.getServletContext().getRealPath("text"));
            File destFile = new File(fileFolder, bena.getId() + ".txt");

            //如果父目录不存在就创建
            if(!destFile.getParentFile().exists())
                destFile.getParentFile().mkdirs();
            System.out.println(destFile.getAbsolutePath());

            //把上传的文件传给所建的文件
            file.transferTo(destFile);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return "上传失败，" + e.getMessage();
        }catch(IOException e){
            e.printStackTrace();
            return "上传失败，" + e.getMessage();
        }

        return bena;
    }

    @DeleteMapping(value = "/chapters/{id}")
    public Object delete(@PathVariable("id") int id, HttpServletRequest request){
        Chapter bena = chapterService.get(id);
        //获取那个文件并删除
        File fileFolder = new File(request.getServletContext().getRealPath("text"));
        File destFile = new File(fileFolder, bena.getId() + ".txt");
        destFile.delete();

        chapterService.delete(id);
        return null;
    }

    @GetMapping(value = "/chapteries/{csid}/chapters")
    public Page4Navigator<Chapter> list(@PathVariable("csid") int csid, @RequestParam(value = "stary", defaultValue = "0") int stary, @RequestParam(value = "size", defaultValue = "5") int size){
        stary = stary<0?0:stary;

        return chapterService.list(csid, stary, size, 5);
    }
}
