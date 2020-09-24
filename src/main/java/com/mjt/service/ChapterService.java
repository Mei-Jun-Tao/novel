package com.mjt.service;

import com.mjt.dao.ChapterDAO;
import com.mjt.pojo.Chapter;
import com.mjt.pojo.Chapters;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ChapterService {

    @Autowired
    ChapterDAO chapterDAO;

    @Autowired
    ChaptersService chaptersService;

    public void add(Chapter chapter){
        chapterDAO.save(chapter);
    }

    public void delete(int id){
        chapterDAO.delete(id);
    }

    public Chapter get(int id){
        return chapterDAO.getOne(id);
    }

    public Page4Navigator<Chapter> list(int csid, int strat, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Chapters chapters = chaptersService.get(csid);
        Pageable pageable = new PageRequest(strat, size, sort);
        Page page = chapterDAO.findByChapters(chapters, pageable);

        return new Page4Navigator<Chapter>(page, navigatePages);
    }

}
